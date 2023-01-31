package com.miam.kmmMiamCore.base.mvi

import com.miam.core.sdk.di.MiamDI
import com.miam.kmmMiamCore.handler.ToasterHandler
import com.miam.kmmMiamCore.miam_core.data.repository.GroceriesListRepositoryImp
import com.miam.kmmMiamCore.miam_core.model.GroceriesList
import com.miam.kmmMiamCore.miam_core.model.RecipeInfos
import com.miam.kmmMiamCore.services.Analytics
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

public data class GroceriesListState(val groceriesList: GroceriesList?): State {
    val recipeCount: Int
        get() = groceriesList?.attributes?.recipesInfos?.size ?: 0
}

public sealed class GroceriesListAction: Action {
    public object RefreshGroceriesList: GroceriesListAction()
    public object ResetGroceriesList: GroceriesListAction()
    public data class AlterRecipeList(val recipeId: String, val guests: Int): GroceriesListAction()
    public data class RemoveRecipe(val recipeId: String): GroceriesListAction()
}

public sealed class GroceriesListEffect: Effect {
    public object GroceriesListLoaded: GroceriesListEffect()
    public data class RecipeCountChanged(val newRecipeCount: Int): GroceriesListEffect()
    public data class RecipeAdded(val recipeId: String, val guests: Int): GroceriesListEffect()
    public data class RecipeRemoved(val recipeId: String): GroceriesListEffect()
}

public interface GroceriesListStore: Store<GroceriesListState, GroceriesListAction, GroceriesListEffect> {
    public fun getGroceriesList(): GroceriesList?
}

public class GroceriesListStoreImpl: GroceriesListStore, CoroutineScope by MainScope() {

    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        println("[ERROR][Miam][GroceriesListStore] $exception ${exception.stackTraceToString()}")
    }

    override val state: MutableStateFlow<GroceriesListState> = MutableStateFlow(GroceriesListState(null))
    private val sideEffect = MutableSharedFlow<GroceriesListEffect>()

    // TODO By lazy allows cyclic dependencies, even if it is bad design
    private val groceriesListRepo: GroceriesListRepositoryImp by lazy { MiamDI.groceriesListRepository }
    private val basketStore: BasketStore by lazy { MiamDI.basketStore }
    private val analyticsService: Analytics by lazy { MiamDI.analyticsService }

    override fun observeState(): StateFlow<GroceriesListState> = state

    override fun observeSideEffect(): Flow<GroceriesListEffect> = sideEffect

    public override fun getGroceriesList(): GroceriesList? {
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
                    analyticsService.sendEvent(Analytics.EVENT_RECIPE_RESET, "", Analytics.PlausibleProps())
                    setGroceriesListAndRefreshBasket(groceriesListRepo.reset())
                }
            }
            is GroceriesListAction.AlterRecipeList -> {
                return launch(coroutineHandler) {
                    appendRecipe(state.value.groceriesList, action.recipeId, action.guests)?.let { newGl ->
                        // side Effect only to refresh UI of c
                        sideEffect.emit(GroceriesListEffect.RecipeAdded(newGl.id, action.guests))
                        setGroceriesListAndRefreshBasket(newGl)
                    }
                }
            }
            is GroceriesListAction.RemoveRecipe -> {
                basketStore.fastRemoveRecipeFromBpl(action.recipeId)
                return launch(coroutineHandler) {
                    removeRecipe(state.value.groceriesList, action.recipeId)?.let { newGl ->
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

        if (groceriesList.hasRecipe(recipeId) && groceriesList.guestsForRecipe(recipeId) == guest) return null

        // val newRecipesInfos = mutableListOf(*groceriesList.attributes!!.recipesInfos.toTypedArray())
        groceriesList.attributes?.recipesInfos?.let { recipesInfos ->
            val newRecipesInfos = if (groceriesList.hasRecipe(recipeId)) {
                analyticsService.sendEvent(Analytics.EVENT_RECIPE_CHANGEGUESTS, "", Analytics.PlausibleProps(recipe_id = recipeId))
                recipesInfos.map { ri -> if (ri.id.toString() == recipeId) ri.copy(guests = guest) else ri }.toMutableList()
            } else {
                analyticsService.sendEvent(Analytics.EVENT_RECIPE_ADD, "", Analytics.PlausibleProps(recipe_id = recipeId))
                ToasterHandler.onAddRecipe()
                mutableListOf(*recipesInfos.toTypedArray(), RecipeInfos(recipeId.toInt(), guest))
            }
            return alterRecipeInfo(groceriesList, newRecipesInfos)
        } ?: return null
    }

    private suspend fun removeRecipe(groceriesList: GroceriesList?, recipeId: String): GroceriesList? {
        if (groceriesList == null || !groceriesList.hasRecipe(recipeId)) return null

        groceriesList.attributes?.recipesInfos?.let { recipesInfos ->
            val newRecipeInfo = recipesInfos.filter { el -> el.id.toString() != recipeId }.toMutableList()
            analyticsService.sendEvent(Analytics.EVENT_RECIPE_REMOVE, "", Analytics.PlausibleProps(recipe_id = recipeId))
            return alterRecipeInfo(groceriesList, newRecipeInfo)
        } ?: return null
    }

    private suspend fun alterRecipeInfo(groceriesList: GroceriesList, recipesInfo: MutableList<RecipeInfos>): GroceriesList {
        val gl = groceriesList.copy(attributes = groceriesList.attributes?.copy(recipesInfos = recipesInfo, appendRecipes = false))
        return groceriesListRepo.updateGroceriesList(gl)
    }

    override fun updateStateIfChanged(newState: GroceriesListState) {
        if (newState.recipeCount != state.value.recipeCount) {
            launch(coroutineHandler) { sideEffect.emit(GroceriesListEffect.RecipeCountChanged(newState.recipeCount)) }
        }
        super.updateStateIfChanged(newState)
    }
}