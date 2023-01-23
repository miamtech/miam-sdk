package com.miam.kmmMiamCore.component.preferences

import com.miam.kmmMiamCore.base.mvi.BaseViewModel
import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.Effect
import com.miam.kmmMiamCore.handler.ContextHandlerInstance
import com.miam.kmmMiamCore.handler.LogHandler
import com.miam.kmmMiamCore.miam_core.data.repository.RecipeRepositoryImp
import com.miam.kmmMiamCore.miam_core.data.repository.TagsRepositoryImp
import com.miam.kmmMiamCore.miam_core.model.CheckableTag
import com.miam.kmmMiamCore.miam_core.model.Record
import com.miam.kmmMiamCore.miam_core.model.Tag
import com.miam.kmmMiamCore.miam_core.model.TagTypes
import com.miam.kmmMiamCore.services.DialogRoute
import com.miam.kmmMiamCore.services.RouteService
import com.miam.kmmMiamCore.services.RouteServiceAction
import com.miam.kmmMiamCore.services.UserPreferences
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@Suppress("PreferencesViewModelInstance used by ios and component")
object PreferencesViewModelInstance: KoinComponent {
    val instance: SingletonPreferencesViewModel by inject()
}

sealed class PreferencesEffect: Effect {
    object PreferencesLoaded: PreferencesEffect()
    object PreferencesChanged: PreferencesEffect()
}

open class SingletonPreferencesViewModel: BaseViewModel<PreferencesContract.Event, PreferencesContract.State, PreferencesContract.Effect>() {

    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        LogHandler.error(" [ERROR][Miam][Preference] $exception")
    }

    private val tagsRepositoryImp: TagsRepositoryImp by inject()
    private val recipeRepositoryImp: RecipeRepositoryImp by inject()
    private val userPreferences: UserPreferences by inject()
    private val routeService: RouteService by inject()

    private val sideEffect = MutableSharedFlow<PreferencesEffect>()
    fun observeSideEffect(): Flow<PreferencesEffect> = sideEffect
    val isInit: Boolean
        get() = currentState.basicState is BasicUiState.Success

    override fun createInitialState(): PreferencesContract.State = getInitialPref()

    init {
        launch(coroutineHandler) {
            // get tags, wait for all to complete and then set state
            listOf(
                async { initDietTag() },
                async { initIngredientTag() },
                async { initEquipmentsTag() }
            ).awaitAll()
            reloadFromLocal()
            val count = getRecipeCount()
            setState { copy(basicState = BasicUiState.Success(PreferencesContent.ALL_PREFRENCES), recipesFound = count) }
            sideEffect.emit(PreferencesEffect.PreferencesLoaded)
            ContextHandlerInstance.instance.emitReadiness()
        }
    }

    private suspend fun initDietTag() {
        val dietsTags = tagsRepositoryImp.fetchDietTags().map { CheckableTag(TagTypes.DIET, it) }
        setState { copy(diets = dietsTags) }
    }

    private suspend fun initIngredientTag() {
        val ingredientTags = defaultIngredientTagIds.map { id -> tagsRepositoryImp.getTagById(id) }.map { CheckableTag(TagTypes.INGREDIENT, it) }
        setState { copy(ingredients = ingredientTags) }
    }

    private suspend fun initEquipmentsTag() {
        val equipmentTags = tagsRepositoryImp.fetchEquipmentTags().map { CheckableTag(TagTypes.EQUIPMENT, it) }
        setState { copy(equipments = equipmentTags) }
    }

    private fun reloadFromLocal() {
        val reloadedIngredients = reloadedIngredients(ingredientsOrEmptyFromLocal())
        setState {
            copy(
                ingredients = reloadedIngredients,
                diets = diets.map { it.resetWith(dietsOrEmptyFromLocal().map { tag -> tag.id }) },
                equipments = equipments.map { it.resetWith(equipmentsOrEmptyFromLocal().map { tag -> tag.id }) },
                guests = guestOrNullFromLocal()
            )
        }
    }

    private fun reloadedIngredients(ingredientsOrEmptyFromLocal: List<Tag>): List<CheckableTag> {
        val existingTagsId = currentState.ingredients.map { it.tag.id }
        val missingIngredients = ingredientsOrEmptyFromLocal.filter { !existingTagsId.contains(it.id) }
        return listOf(
            *currentState.ingredients.map { it.resetWith(ingredientsOrEmptyFromLocal.map { tag -> tag.id }) }.toTypedArray(),
            *missingIngredients.map { CheckableTag(TagTypes.INGREDIENT, it, true) }.toTypedArray()
        )
    }

    private fun dietsOrEmptyFromLocal(): List<Tag> {
        return tagsFromLocal(LOCAL_DIET_KEY)
    }

    private fun ingredientsOrEmptyFromLocal(): List<Tag> {
        return tagsFromLocal(LOCAL_INGREDIENT_KEY)
    }

    private fun equipmentsOrEmptyFromLocal(): List<Tag> {
        return tagsFromLocal(LOCAL_EQUIPMENT_KEY)
    }

    fun guestOrNullFromLocal(): Int? {
        return userPreferences.getIntOrNull(LOCAL_GUEST_KEY)
    }

    private fun tagsFromLocal(preferenceKey: String): List<Tag> {
        return userPreferences.getListOrNull(preferenceKey)?.mapNotNull { Record.fromString(it) }?.map { it as Tag } ?: emptyList()
    }

    override fun handleEvent(event: PreferencesContract.Event) {
        TODO("Not yet implemented")
    }

    fun togglePreference(tagIdToToggle: String) {
        setState {
            copy(
                diets = diets.map { checkableTag -> checkableTag.toggleIfNeeded(tagIdToToggle) },
                ingredients = ingredients.map { checkableTag -> checkableTag.toggleIfNeeded(tagIdToToggle) },
                equipments = equipments.map { checkableTag -> checkableTag.toggleIfNeeded(tagIdToToggle) }
            )
        }
        updateRecipesCount()
    }

    fun addIngredientPreference(tag: Tag) {
        if (currentState.ingredients.any { cTag: CheckableTag -> cTag.tag.id == tag.id }) return
        setState { copy(ingredients = listOf(*currentState.ingredients.toTypedArray(), CheckableTag(TagTypes.INGREDIENT, tag, true))) }
        updateRecipesCount()
    }

    fun resetPreferences() {
        // TODO save conf in state not to reload ids ?
        launch(coroutineHandler) {
            reloadFromLocal()
            updateRecipesCount()
        }
    }

    fun applyPreferences() {
        userPreferences.putList(LOCAL_DIET_KEY, toSaveInStorageSerializedTags(currentState.diets))
        userPreferences.putList(LOCAL_INGREDIENT_KEY, toSaveInStorageSerializedTags(currentState.ingredients))
        userPreferences.putList(LOCAL_EQUIPMENT_KEY, toSaveInStorageSerializedTags(currentState.equipments))
        currentState.guests?.let { userPreferences.putInt(LOCAL_GUEST_KEY, it) }
        launch(coroutineHandler) { sideEffect.emit(PreferencesEffect.PreferencesChanged) }
    }

    private fun toSaveInStorageSerializedTags(checkableTagList: List<CheckableTag>): List<String> {
        return checkableTagList.filter { it.changedFromItsDefaultValue }.map { diet -> diet.tag.toJsonElement().toString() }
    }

    private fun updateRecipesCount() {
        launch(coroutineHandler) {
            val count = getRecipeCount()
            setState { copy(recipesFound = count) }
        }
    }

    private suspend fun getRecipeCount(): Int {
        return recipeRepositoryImp.getRecipeNumberOfResult(getPreferencesAsQueryString())
    }

    fun back() {
        routeService.previous()
    }

    fun goToSearchPrefAndPushRoute() {
        setState { copy(basicState = BasicUiState.Success(PreferencesContent.SEARCH_PREFRERENCES)) }
        routeService.dispatch(
            RouteServiceAction.SetDialogRoute(
                "",
                { setState { copy(basicState = BasicUiState.Success(PreferencesContent.SEARCH_PREFRERENCES)) } },
                (routeService.getCurrentRoute() as DialogRoute).closeDialog
            )
        )
    }

    fun goToAllPref() {
        setState { copy(basicState = BasicUiState.Success(PreferencesContent.ALL_PREFRENCES)) }
    }

    fun changeGlobalGuest(numberOfGuest: Int) {
        if (numberOfGuest in 1..100) {
            setState { copy(guests = numberOfGuest) }
        }
    }

    val allTags: List<CheckableTag>
        get() = listOf(*currentState.diets.toTypedArray(), *currentState.ingredients.toTypedArray(), *currentState.equipments.toTypedArray())

    fun getPreferencesAsQueryString(): String {
        val toInclude = allTags.filter { tag -> tag.isIncludedInQuery }.filter { tag -> tag.changedFromItsDefaultValue }.map { it.tag.id }
        val toExclude = allTags.filter { tag -> !tag.isIncludedInQuery }.filter { tag -> tag.changedFromItsDefaultValue }.map { it.tag.id }
        val includedStr = if (toInclude.isNotEmpty()) "&filter[include-tags]=${toInclude.joinToString(",")}" else ""
        val excludedStr = if (toExclude.isNotEmpty()) "&filter[exclude-tags]=${toExclude.joinToString(",")}" else ""
        return includedStr + excludedStr
    }

    fun globalGuestCountOrDefault(defaultValue: Int = 4): Int {
        return currentState.guests ?: defaultValue
    }

    companion object {
        val LOCAL_DIET_KEY = "MIAM_${TagTypes.DIET.name}"
        val LOCAL_INGREDIENT_KEY = "MIAM_${TagTypes.INGREDIENT.name}"
        val LOCAL_EQUIPMENT_KEY = "MIAM_${TagTypes.EQUIPMENT.name}"
        val LOCAL_GUEST_KEY = "MIAM_GUEST"

        val defaultIngredientTagIds = listOf(
            "ingredient_category_lgumes",
            "ingredient_category_viandes-blanches",
            "ingredient_category_fromage",
            "ingredient_category_poissons",
            "ingredient_category_fruits",
            "ingredient_category_alcool"
        )

        fun getInitialPref(): PreferencesContract.State {
            return PreferencesContract.State(
                basicState = BasicUiState.Loading,
                diets = emptyList(),
                ingredients = emptyList(),
                equipments = emptyList(),
                recipesFound = 0,
                guests = null
            )
        }
    }
}