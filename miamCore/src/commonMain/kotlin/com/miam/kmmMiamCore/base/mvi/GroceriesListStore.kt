package com.miam.kmmMiamCore.base.mvi

import com.miam.kmmMiamCore.handler.ToasterHandler
import com.miam.kmmMiamCore.miam_core.data.repository.GroceriesListRepositoryImp
import com.miam.kmmMiamCore.miam_core.model.GroceriesList
import com.miam.kmmMiamCore.miam_core.model.RecipeInfos
import com.miam.kmmMiamCore.services.Analytics
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

data class GroceriesListState(val groceriesList: GroceriesList?) : State {
    val recipeCount: Int
        get() = groceriesList?.attributes?.recipesInfos?.size ?: 0
}

sealed class GroceriesListAction : Action {
    object RefreshGroceriesList : GroceriesListAction()
    object ResetGroceriesList : GroceriesListAction()
    data class AlterRecipeList(val recipeId: String, val guests: Int) : GroceriesListAction()
    data class RemoveRecipe(val recipeId: String) : GroceriesListAction()
}

sealed class GroceriesListEffect : Effect {
    object GroceriesListLoaded : GroceriesListEffect()
    data class RecipeCountChanged(val newRecipeCount: Int) : GroceriesListEffect()
    data class RecipeAdded(val recipeId: String, val guests: Int) : GroceriesListEffect()
    data class RecipeRemoved(val recipeId: String) : GroceriesListEffect()
}

class GroceriesListStore : Store<GroceriesListState, GroceriesListAction, GroceriesListEffect>,
    KoinComponent,
    CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        println("[ERROR][Miam][GroceriesListStore] $exception ${exception.stackTraceToString()}")
    }

    override val state = MutableStateFlow(GroceriesListState(null))
    private val sideEffect = MutableSharedFlow<GroceriesListEffect>()
    private val groceriesListRepo: GroceriesListRepositoryImp by inject()
    private val basketStore: BasketStore by inject()
    private val analyticsService: Analytics by inject()

    override fun observeState(): StateFlow<GroceriesListState> = state

    override fun observeSideEffect(): Flow<GroceriesListEffect> = sideEffect

    fun getGroceriesList(): GroceriesList? {
        return state.value.groceriesList
    }

    /**
     * save current flow if loading cancel and launch new flow
     **/

    override fun dispatch(action: GroceriesListAction): Job {
        when (action) {
            is GroceriesListAction.RefreshGroceriesList -> {
                return launch(coroutineHandler) {
                    setGroceriesListAndRefreshBasket(groceriesListRepo.getCurrent())
                }
            }
            is GroceriesListAction.ResetGroceriesList -> {
                return launch(coroutineHandler) {
                    // TODO : path
                    analyticsService.sendEvent(
                        Analytics.EVENT_RECIPE_RESET,
                        "",
                        Analytics.PlausibleProps()
                    )
                    setGroceriesListAndRefreshBasket(groceriesListRepo.reset())
                }
            }
            is GroceriesListAction.AlterRecipeList -> {
                return launch(coroutineHandler) {
                    val newGl =
                        appendRecipe(state.value.groceriesList, action.recipeId, action.guests)
                    if (newGl != null) {
                        // side Effect only to refresh UI of c
                        sideEffect.emit(GroceriesListEffect.RecipeAdded(newGl.id, action.guests))
                        setGroceriesListAndRefreshBasket(newGl)
                    }
                }
            }
            is GroceriesListAction.RemoveRecipe -> {
                basketStore.fastRemoveRecipeFromBpl(action.recipeId)
                return launch(coroutineHandler) {
                    val newGl = removeRecipe(state.value.groceriesList, action.recipeId)
                    if (newGl != null) {
                        sideEffect.emit(GroceriesListEffect.RecipeRemoved(newGl.id))
                        setGroceriesListAndRefreshBasket(newGl)
                    }
                }
            }
        }
    }

    private suspend fun setGroceriesListAndRefreshBasket(groceriesList: GroceriesList) {
        updateStateIfChanged(state.value.copy(groceriesList = groceriesList))
        sideEffect.emit(GroceriesListEffect.GroceriesListLoaded)
        basketStore.dispatch(BasketAction.RefreshBasket)
    }

    private suspend fun appendRecipe(
        groceriesList: GroceriesList?,
        recipeId: String,
        guest: Int
    ): GroceriesList? {
        if (groceriesList == null) return null

        val newRecipesInfos = mutableListOf(*groceriesList.attributes!!.recipesInfos.toTypedArray())
        if (groceriesList.hasRecipe(recipeId)) {
            if (groceriesList.guestsForRecipe(recipeId) == guest) return null

            // TODO : path
            analyticsService.sendEvent(
                Analytics.EVENT_RECIPE_CHANGEGUESTS,
                "",
                Analytics.PlausibleProps(recipe_id = recipeId)
            )
            newRecipesInfos.find { it.id.toString() == recipeId }?.guests = guest
        } else {
            // TODO : path
            analyticsService.sendEvent(
                Analytics.EVENT_RECIPE_ADD,
                "",
                Analytics.PlausibleProps(recipe_id = recipeId)
            )
            newRecipesInfos.add(RecipeInfos(recipeId.toInt(), guest))
            ToasterHandler.onAddRecipe()
        }
        return alterRecipeInfo(groceriesList, newRecipesInfos)
    }

    private suspend fun removeRecipe(
        groceriesList: GroceriesList?,
        recipeId: String
    ): GroceriesList? {
        if (groceriesList == null || !groceriesList.hasRecipe(recipeId)) return null

        // TODO : path
        analyticsService.sendEvent(
            Analytics.EVENT_RECIPE_REMOVE,
            "",
            Analytics.PlausibleProps(recipe_id = recipeId)
        )
        val recipesInfo = groceriesList.attributes!!.recipesInfos
        val newRecipeInfo =
            recipesInfo.filter { el -> el.id.toString() != recipeId }.toMutableList()

        return alterRecipeInfo(groceriesList, newRecipeInfo)
    }

    private suspend fun alterRecipeInfo(
        groceriesList: GroceriesList,
        recipesInfo: MutableList<RecipeInfos>
    ): GroceriesList {
        val gl = groceriesList.copy(
            attributes = groceriesList.attributes!!.copy(
                recipesInfos = recipesInfo,
                appendRecipes = false
            )
        )
        return groceriesListRepo.updateGroceriesList(gl)
    }

    override fun updateStateIfChanged(newState: GroceriesListState) {
        if (newState.recipeCount != state.value.recipeCount) {
            launch(coroutineHandler) {
                sideEffect.emit(
                    GroceriesListEffect.RecipeCountChanged(
                        newState.recipeCount
                    )
                )
            }
        }
        super.updateStateIfChanged(newState)
    }
}