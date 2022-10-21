package com.miam.kmmMiamCore.component.preferences

import com.miam.kmmMiamCore.base.mvi.BaseViewModel
import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.miam_core.data.repository.RecipeRepositoryImp
import com.miam.kmmMiamCore.miam_core.data.repository.TagsRepositoryImp
import com.miam.kmmMiamCore.miam_core.model.CheckableTag
import com.miam.kmmMiamCore.miam_core.model.Tag
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.koin.core.component.inject

open class PreferencesViewModel : BaseViewModel<PreferencesContract.Event, PreferencesContract.State, PreferencesContract.Effect>() {

    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        println(" [ERROR][Miam][Preference] $exception")
    }

    private val tagsRepositoryImp: TagsRepositoryImp by inject()
    private val recipeRepositoryImp: RecipeRepositoryImp by inject()

    init {
        initDietTag()
        initEquipmentsTag()
        initIngredientTag()
        //TODO  sync ?
        updateRecipesCount()
    }

    override fun createInitialState(): PreferencesContract.State = getInitialPref()

    override fun handleEvent(event: PreferencesContract.Event) {
        TODO("Not yet implemented")
    }

    fun togglePreference(checkableTag: CheckableTag) {
        val newTag = checkableTag.copy(isChecked = !checkableTag.isChecked)
        // TODO update pref
        updateRecipesCount()
    }

    fun resetPreferences() {
        setState { getInitialPref() }
        updateRecipesCount()
    }

    private fun updateRecipesCount() {
        // TODO get count from recipe repo
    }

    private fun initDietTag() {
        if (currentState.basicState != BasicUiState.Loading) setState { copy(basicState = BasicUiState.Loading) }
        launch(coroutineHandler) {
            val dietsTags = tagsRepositoryImp.fetchDietTags().map { it.toCheckableTag(false) }
            // TODO is check ?
            // TODO Local BDD ?
            setState { copy(basicState = BasicUiState.Success(true), diets = dietsTags) }
            // TODO check status of other calls
        }
    }

    private fun initEquipmentsTag() {
        if (currentState.basicState != BasicUiState.Loading) setState { copy(basicState = BasicUiState.Loading) }
        launch(coroutineHandler) {
            val equipmentTags = tagsRepositoryImp.fetchEquipmentTags().map { it.toCheckableTag(false) }
            setState { copy(basicState = BasicUiState.Success(true), equipments = equipmentTags) }
        }
    }

    private fun initIngredientTag() {
        if (currentState.basicState != BasicUiState.Loading) setState { copy(basicState = BasicUiState.Loading) }
        val ingredientTags = mutableListOf<Tag>()
        launch(coroutineHandler) {
            defaultIngredientTagIds.forEach { id ->
                ingredientTags.add(tagsRepositoryImp.getTagById(id))
            }
            setState { copy(basicState = BasicUiState.Success(true), ingredients = ingredientTags.map { it.toCheckableTag(true) }) }
        }
    }

    companion object {
        val defaultIngredientTagIds = listOf(
            "ingredient_category_lgumes",
            "ingredient_category_viandes-blanches",
            "ingredient_category_fromage",
            "ingredient_category_poissons",
            "ingredient_category_fruits",
            "ingredientsdefinition_131",
            "ingredientsdefinition_334",
            "ingredientsdefinition_64",
            "ingredient_category_alcool"
        )

        fun getInitialPref(): PreferencesContract.State {
            return PreferencesContract.State(
                basicState = BasicUiState.Loading,
                diets = emptyList(),
                ingredients = emptyList(),
                equipments = emptyList(),
                counterWasRearranged = false,
                recipesFound = 0,
                hasRecipesInList = false,
                guests = 4
            )
        }
    }
}