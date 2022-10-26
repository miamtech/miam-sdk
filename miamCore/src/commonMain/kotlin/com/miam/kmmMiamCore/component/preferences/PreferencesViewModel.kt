package com.miam.kmmMiamCore.component.preferences

import com.miam.kmmMiamCore.base.mvi.BaseViewModel
import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.miam_core.data.repository.RecipeRepositoryImp
import com.miam.kmmMiamCore.miam_core.data.repository.TagsRepositoryImp
import com.miam.kmmMiamCore.miam_core.model.CheckableTag
import com.miam.kmmMiamCore.miam_core.model.Tag
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import org.koin.core.component.inject

open class PreferencesViewModel : BaseViewModel<PreferencesContract.Event, PreferencesContract.State, PreferencesContract.Effect>() {

    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        println(" [ERROR][Miam][Preference] $exception")
    }

    private val tagsRepositoryImp: TagsRepositoryImp by inject()
    private val recipeRepositoryImp: RecipeRepositoryImp by inject()

    private val isDietPrefReady = MutableStateFlow(false)
    private val isEquipmentPrefReady = MutableStateFlow(false)
    private val isIngredientPrefReady = MutableStateFlow(false)

    init {
        initStatusWatcher()
        initDietTag()
        initEquipmentsTag()
        initIngredientTag()
    }

    override fun createInitialState(): PreferencesContract.State = getInitialPref()

    override fun handleEvent(event: PreferencesContract.Event) {
        TODO("Not yet implemented")
    }

    fun togglePreference(checkableTag: CheckableTag) {
        val newTag = checkableTag.copy(isChecked = !checkableTag.isChecked)
        if (newTag.without) {
            setState { copy(ingredients = updatedPrefList(currentState.ingredients.toMutableList(), newTag)) }
        } else {
            if (checkableTag.tag.attributes!!.tagTypeId == "diet") {
                setState { copy(diets = updatedPrefList(currentState.diets.toMutableList(), newTag)) }
            } else {
                setState { copy(equipments = updatedPrefList(currentState.equipments.toMutableList(), newTag)) }
            }
        }
        // TODO update pref
        updateRecipesCount()
    }


    fun resetPreferences() {
        setState { getInitialPref() }
        updateRecipesCount()
    }

    private fun updatedPrefList(checkablesTag: MutableList<CheckableTag>, tagToInject: CheckableTag): List<CheckableTag> {
        val tagIndex = checkablesTag.indexOfFirst { it.tag.id === tagToInject.tag.id }
        if (tagIndex == -1) return checkablesTag.toList()
        checkablesTag[tagIndex] = tagToInject
        return checkablesTag.toList()
    }

    private fun updateRecipesCount() {
        // TODO get count from recipe repo
    }

    private fun initStatusWatcher() {
        val dietCombineWithEquipmentReadyness = isDietPrefReady.combine(flow = isEquipmentPrefReady) { diet, equipment ->
            return@combine diet && equipment
        }

        launch(coroutineHandler) {
            // TODO check memory leak
            dietCombineWithEquipmentReadyness.combine(isIngredientPrefReady) { dietAndEquipment, ingredient ->
                return@combine dietAndEquipment && ingredient
            }.collect {
                setState { copy(basicState = if (it) BasicUiState.Success(true) else BasicUiState.Loading) }
                if (it) {
                    updateRecipesCount()
                }
            }
        }

    }

    private fun initDietTag() {
        launch(coroutineHandler) {
            val dietsTags = tagsRepositoryImp.fetchDietTags().map { it.toCheckableTag(false) }
            // TODO is check ?
            // TODO Local BDD ?
            setState { copy(basicState = BasicUiState.Success(true), diets = dietsTags) }
            isDietPrefReady.value = true
        }
    }

    private fun initEquipmentsTag() {
        if (currentState.basicState != BasicUiState.Loading) setState { copy(basicState = BasicUiState.Loading) }
        launch(coroutineHandler) {
            val equipmentTags = tagsRepositoryImp.fetchEquipmentTags().map { it.toCheckableTag(false) }
            setState { copy(basicState = BasicUiState.Success(true), equipments = equipmentTags) }
            isEquipmentPrefReady.value = true
        }
    }

    private fun initIngredientTag() {
        val ingredientTags = mutableListOf<Tag>()
        launch(coroutineHandler) {
            defaultIngredientTagIds.forEach { id ->
                ingredientTags.add(tagsRepositoryImp.getTagById(id))
            }
            setState { copy(basicState = BasicUiState.Success(true), ingredients = ingredientTags.map { it.toCheckableTag(true) }) }
            isIngredientPrefReady.value = true
        }
    }

    companion object {
        // TODO handle ingredient tag (that aren't tag ...)
        val defaultIngredientTagIds = listOf(
            "ingredient_category_lgumes",
            "ingredient_category_viandes-blanches",
            "ingredient_category_fromage",
            "ingredient_category_poissons",
            "ingredient_category_fruits",
//            "ingredientsdefinition_131",
//            "ingredientsdefinition_334",
//            "ingredientsdefinition_64",
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