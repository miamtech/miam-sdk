package com.miam.kmmMiamCore.component.preferences

import com.miam.kmmMiamCore.base.mvi.BaseViewModel
import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.handler.LogHandler
import com.miam.kmmMiamCore.miam_core.data.repository.RecipeRepositoryImp
import com.miam.kmmMiamCore.miam_core.data.repository.TagsRepositoryImp
import com.miam.kmmMiamCore.miam_core.model.CheckableTag
import com.miam.kmmMiamCore.miam_core.model.Tag
import com.miam.kmmMiamCore.miam_core.model.TagTypes
import com.miam.kmmMiamCore.services.UserPreferences
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@Suppress("PreferencesViewModelInstance used by ios and component")
object PreferencesViewModelInstance: KoinComponent {
    val instance: SingletonPreferencesViewModel by inject()
}

open class SingletonPreferencesViewModel: BaseViewModel<PreferencesContract.Event, PreferencesContract.State, PreferencesContract.Effect>() {

    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        println(" [ERROR][Miam][Preference] $exception")
    }

    private val tagsRepositoryImp: TagsRepositoryImp by inject()
    private val recipeRepositoryImp: RecipeRepositoryImp by inject()
    private val userPreferences: UserPreferences by inject()

    override fun createInitialState(): PreferencesContract.State = getInitialPref()

    init {
        LogHandler.info("Init preferences-------------------------")
        launch(coroutineHandler) {
            // get tags, wait for all to complete and then set state
            listOf(
                async { initDietTag() },
                async { initIngredientTag() },
                async { initEquipmentsTag() }
            ).awaitAll()
            LogHandler.info("Loaded default tags ${currentState.diets} ${currentState.ingredients} ${currentState.equipments}")
            reloadFromLocal()
            LogHandler.info("Loaded local tags ${currentState.diets} ${currentState.ingredients} ${currentState.equipments}")
            val count = getRecipeCount()
            setState { copy(basicState = BasicUiState.Success(true), recipesFound = count) }
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

    private suspend fun reloadFromLocal() {
        val reloadedIngredients = reloadedIngredients(ingredientsOrEmptyFromLocal())
        setState {
            copy(
                ingredients = reloadedIngredients,
                diets = diets.map { it.resetWith(dietsOrEmptyFromLocal()) },
                equipments = equipments.map { it.resetWith(equipmentsOrEmptyFromLocal()) }
            )
        }
    }

    private suspend fun missingIngredientsTag(tagToLoad: List<String>?, alreadyPresentIds: List<String>): List<Tag> {
        if (tagToLoad.isNullOrEmpty()) return listOf()

        return tagToLoad.filter { !alreadyPresentIds.contains(it) }.map { tagsRepositoryImp.getTagById(it) }
    }

    private suspend fun reloadedIngredients(ingredientsOrEmptyFromLocal: List<String>): List<CheckableTag> {
        val existingTagsId = currentState.ingredients.map { it.tag.id }
        val missingIngredients = missingIngredientsTag(ingredientsOrEmptyFromLocal, existingTagsId)
        return listOf(
            *currentState.ingredients.map { it.resetWith(ingredientsOrEmptyFromLocal) }.toTypedArray(),
        *missingIngredients.map { CheckableTag(TagTypes.INGREDIENT, it) }.toTypedArray()
        )
    }

    private fun dietsOrEmptyFromLocal(): List<String> {
        return userPreferences.getListOrNull(LOCAL_DIET_KEY) ?: emptyList()
    }

    private fun ingredientsOrEmptyFromLocal(): List<String> {
        return userPreferences.getListOrNull(LOCAL_INGREDIENT_KEY) ?: emptyList()
    }

    private fun equipmentsOrEmptyFromLocal(): List<String> {
        return userPreferences.getListOrNull(LOCAL_EQUIPMENT_KEY) ?: emptyList()
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
        setState { copy(ingredients = listOf(*currentState.ingredients.toTypedArray(), CheckableTag(TagTypes.INGREDIENT, tag))) }
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
        userPreferences.putList(LOCAL_DIET_KEY, toSaveInStorageIds(currentState.diets))
        userPreferences.putList(LOCAL_INGREDIENT_KEY, toSaveInStorageIds(currentState.ingredients))
        userPreferences.putList(LOCAL_EQUIPMENT_KEY, toSaveInStorageIds(currentState.equipments))
        LogHandler.info("save in storage done")
    }

    private fun toSaveInStorageIds(checkableTagList: List<CheckableTag>): List<String> {
        return checkableTagList.filter { it.saveInStorage }.map { diet -> diet.tag.id }
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

    fun changeGlobaleGuest(numberOfGuest: Int) {
        //TODO Alex map with recipes card and detail
        if (numberOfGuest in 1..100) {
            setState { copy(guests = numberOfGuest) }
        }
    }

    val allTags: List<CheckableTag>
    get() = listOf(*currentState.diets.toTypedArray(), *currentState.ingredients.toTypedArray(), *currentState.equipments.toTypedArray())

    fun getPreferencesAsQueryString(): String {
        val toInclude = allTags.filter { tag -> tag.isIncludedInQUery }.filter { tag -> tag.isChecked }.map { it.tag.id }
        val toExclude = allTags.filter { tag -> !tag.isIncludedInQUery }.filter { tag -> tag.isChecked }.map { it.tag.id }
        val includedStr = if (toInclude.isNotEmpty()) "&filter[include-tags]=${toInclude.joinToString(",")}" else ""
        val excludedStr = if (toExclude.isNotEmpty()) "&filter[exclude-tags]=${toExclude.joinToString(",")}" else ""
        return includedStr + excludedStr
    }

    companion object {
        val LOCAL_DIET_KEY = "MIAM_${TagTypes.DIET.name}"
        val LOCAL_INGREDIENT_KEY = "MIAM_${TagTypes.INGREDIENT.name}"
        val LOCAL_EQUIPMENT_KEY = "MIAM_${TagTypes.EQUIPMENT.name}"

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
                guests = 4
            )
        }
    }
}