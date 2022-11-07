package com.miam.kmmMiamCore.component.preferences

import com.miam.kmmMiamCore.base.mvi.BaseViewModel
import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.handler.ContextHandler
import com.miam.kmmMiamCore.handler.LogHandler
import com.miam.kmmMiamCore.miam_core.data.repository.RecipeRepositoryImp
import com.miam.kmmMiamCore.miam_core.data.repository.TagsRepositoryImp
import com.miam.kmmMiamCore.miam_core.model.CheckableTag
import com.miam.kmmMiamCore.miam_core.model.Tag
import com.miam.kmmMiamCore.services.KMMPreference
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonArray
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@Suppress("PreferencesViewModelInstance used by ios and component")
object PreferencesViewModelInstance : KoinComponent {
    val instance: SingletonPreferencesViewModel by inject()
}

open class SingletonPreferencesViewModel :
    BaseViewModel<PreferencesContract.Event, PreferencesContract.State, PreferencesContract.Effect>() {

    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        println(" [ERROR][Miam][Preference] $exception")
    }

    private val tagsRepositoryImp: TagsRepositoryImp by inject()
    private val recipeRepositoryImp: RecipeRepositoryImp by inject()
    private val contextHandler: ContextHandler by inject()

    init {
        LogHandler.info("Init preferences-------------------------")
        launch(coroutineHandler) {
            val jsonSelectedTags = loadFromLocal()
            // get tags, wait for all to complete and then set state
            listOf(
                async { initDietTag() },
                async { initEquipmentsTag(selectedTagOrNull(jsonSelectedTags, JSON_EQUIPMENT_KEY)) },
                async { initIngredientTag(selectedTagOrNull(jsonSelectedTags, JSON_INGREDIENT_KEY)) }
            ).awaitAll()
            loadFromLocal()
            val count = getRecipeCount()
            setState { copy(basicState = BasicUiState.Success(true), recipesFound = count, localSelectedTags = jsonSelectedTags) }
        }
    }

    private fun selectedTagOrNull(jsonSelectedTags: JsonObject, key: String): List<String>? {
        return jsonSelectedTags[key]?.jsonArray?.map { it -> it.toString() }
    }

    override fun createInitialState(): PreferencesContract.State = getInitialPref()

    private suspend fun initDietTag() {
        val loulou = KMMPreference(context = contextHandler.state.value.applicationContext!!)
        val selectedTagIds = loulou.getList(JSON_DIET_KEY)
        val dietsTags = tagsRepositoryImp.fetchDietTags().map { it.toCheckableTag(selectedTagIds) }
        setState { copy(diets = dietsTags) }
    }

    private suspend fun initEquipmentsTag(selectedTagIds: List<String>?) {
        val equipmentTags = tagsRepositoryImp.fetchEquipmentTags().map { it.toCheckableTag(selectedTagIds, true) }
        setState { copy(equipments = equipmentTags) }
    }

    private suspend fun initIngredientTag(selectedTagIds: List<String>?) {
        LogHandler.info("init initIngredientTag with $selectedTagIds -------------------------")
        val ingredientTags = defaultIngredientTagIds.map { id -> tagsRepositoryImp.getTagById(id) }.map { it.toCheckableTag(selectedTagIds) }
        setState { copy(ingredients = ingredientTags) }
    }

    private fun loadFromLocal(): JsonObject {
        return JsonObject(
            mapOf(
                JSON_INGREDIENT_KEY to JsonArray(listOf(JsonPrimitive("ingredient_category_lgumes")))
            )
        )
    }

    private fun saveToLocal() {
        KMMPreference(context = contextHandler.state.value.applicationContext!!).put(JSON_DIET_KEY, listOf("diet_sans-gluten"))

    }

    override fun handleEvent(event: PreferencesContract.Event) {
        TODO("Not yet implemented")
    }

    fun togglePreference(tagIdToToogle: String) {
        setState {
            copy(
                ingredients = ingredients.map { checkableTag -> checkableTag.toggleIfNeeded(tagIdToToogle) },
                diets = diets.map { checkableTag -> checkableTag.toggleIfNeeded(tagIdToToogle) },
                equipments = equipments.map { checkableTag -> checkableTag.toggleIfNeeded(tagIdToToogle) }
            )
        }
        updateRecipesCount()
    }

    fun addIngredientPreference(tag: Tag) {
        setState { copy(ingredients = listOf(*currentState.ingredients.toTypedArray(), CheckableTag(tag, isChecked = true))) }
        updateRecipesCount()
    }

    fun resetPreferences() {
        setState {
            copy(
                ingredients = ingredients.map { it.resetWith(selectedTagOrNull(currentState.localSelectedTags, JSON_INGREDIENT_KEY)) },
                diets = diets.map { it.resetWith(selectedTagOrNull(currentState.localSelectedTags, JSON_DIET_KEY)) },
                equipments = equipments.map { it.resetWith(selectedTagOrNull(currentState.localSelectedTags, JSON_EQUIPMENT_KEY), true) }
            )
        }
        updateRecipesCount()
    }

    fun applyPreferences() {
        // setState { copy(localSelectedTags = saveToLocal()) }
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

    private fun savePref(preferences: List<CheckableTag>) {
        // TODO ALEX save local pref
    }

    fun getPreferencesAsQueryString(): String {
        val toInclude = currentState.diets.filter { tag -> tag.isChecked }.map { it.tag.id }
        val toExclude = listOf(
            *currentState.ingredients.filter { tag -> tag.isChecked }.toTypedArray(),
            *currentState.equipments.filter { tag -> !tag.isChecked }.toTypedArray()
        ).map { it.tag.id }
        val includedStr = if (toInclude.isNotEmpty()) "&filter[include-tags]=${toInclude.joinToString(",")}" else ""
        val excludedStr = if (toExclude.isNotEmpty()) "&filter[exclude-tags]=${toExclude.joinToString(",")}" else ""
        return includedStr + excludedStr
    }

    companion object {
        const val JSON_DIET_KEY = "diet"
        const val JSON_EQUIPMENT_KEY = "equipment"
        const val JSON_INGREDIENT_KEY = "ingredient"

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
                localSelectedTags = JsonObject(mapOf()),
                recipesFound = 0,
                guests = 4
            )
        }
    }
}