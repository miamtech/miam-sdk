package com.miam.kmm_miam_sdk.base.mvi

import com.miam.kmm_miam_sdk.miam_core.data.repository.GroceriesListRepositoryImp
import com.miam.kmm_miam_sdk.miam_core.model.GroceriesList
import com.miam.kmm_miam_sdk.miam_core.model.RecipeInfos
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import com.miam.kmm_miam_sdk.handler.LogHandler



data class GroceriesListState(
    val groceriesList : GroceriesList?
) : State

sealed class  GroceriesListAction : Action {
    object RefreshGroceriesList : GroceriesListAction()
    object ResetGroceriesList : GroceriesListAction()
    data class SetGroceriesList(val gl :GroceriesList) : GroceriesListAction()
    data class AlterRecipeList(val recipeId : String, val guests: Int) : GroceriesListAction()
    data class RemoveRecipe(val recipeId: String): GroceriesListAction()
    data class Error(val error: Exception) : GroceriesListAction()
}
sealed class  GroceriesListEffect : Effect {
    data class Error(val error: Exception) :  GroceriesListEffect()
    object GroceriesListLoaded :  GroceriesListEffect()
    data class RecipeAdded(val recipeId: String, val guests: Int): GroceriesListEffect()
    data class RecipeRemoved(val recipeId: String) :GroceriesListEffect()
}

class GroceriesListStore : Store<GroceriesListState, GroceriesListAction, GroceriesListEffect>, KoinComponent,
    CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private val coroutineHandler = CoroutineExceptionHandler {
            _, exception -> println("[ERROR][Miam][GroceriesListStore] $exception")
    }

    private val state = MutableStateFlow(GroceriesListState( null))
    private val sideEffect = MutableSharedFlow<GroceriesListEffect>()
    private val groceriesListRepo: GroceriesListRepositoryImp by inject()
    private val basketStore: BasketStore by inject()

    override fun observeState(): StateFlow<GroceriesListState> = state

    override fun observeSideEffect(): Flow<GroceriesListEffect> = sideEffect


    /**
     * save current flow if loading cancel and launch new flow
     **/

    override fun dispatch(action: GroceriesListAction) {
        // println("Miam gl dispatch $action")
        val oldState = state.value

        val newState = when (action) {
            is GroceriesListAction.RefreshGroceriesList -> {
                // println("Miam --> basket RefreshGroceriesList")
                launch(coroutineHandler) {
                    val gl = groceriesListRepo.getCurrent()
                    dispatch(GroceriesListAction.SetGroceriesList(gl))
                }
                oldState
            }
            is GroceriesListAction.ResetGroceriesList -> {
                println("MIAM --> basket reset GroceriesList")
                launch(coroutineHandler) {
                    dispatch(GroceriesListAction.SetGroceriesList(groceriesListRepo.reset()))
                }
                oldState
            }
            is GroceriesListAction.SetGroceriesList -> {
                // println("Miam -> GroceriesListAction.SetGroceriesList")
                basketStore.dispatch(BasketAction.SetGroceriesList(action.gl))
                launch(coroutineHandler) { sideEffect.emit(GroceriesListEffect.GroceriesListLoaded)}
                oldState.copy(groceriesList = action.gl)
            }
            is GroceriesListAction.AlterRecipeList -> {
                 LogHandler.debug("[Miam] add recipe")
                launch(coroutineHandler) { appendRecipe(action.recipeId, action.guests, oldState)}
                oldState
            }
            is GroceriesListAction.RemoveRecipe -> {
                basketStore.dispatch(BasketAction.RemoveBasketPreviewLine(action.recipeId))
                launch(coroutineHandler) {
                    removeRecipe(action.recipeId, oldState)
                }
                oldState
            }
            is GroceriesListAction.Error -> {
                TODO("handle errors")
                oldState
            }
        }

        if (newState != oldState) {
            state.value = newState
        }
    }

    private suspend fun appendRecipe(recipeId :String, guest: Int, states :GroceriesListState)  {
        LogHandler.debug("[Miam] appendRecipe $recipeId")
         LogHandler.debug("[Miam] states $states")
        if(states.groceriesList == null) return
        val recipesInfos = mutableListOf(*states.groceriesList.attributes!!.recipesInfos.toTypedArray())
        LogHandler.debug("[Miam] recipesInfos $recipesInfos")
        if(states.groceriesList.hasRecipe(recipeId)) {
            if(states.groceriesList.guestsForRecipe(recipeId) == guest) return
            recipesInfos.find { it.id.toString() == recipeId }?.guests = guest
        } else {
            LogHandler.debug("[Miam] recipesInfos $recipesInfos")
            recipesInfos.add(RecipeInfos(recipeId.toInt(), guest))
        }
          LogHandler.debug("[Miam] recipesInfos after alter $recipesInfos")
        // side Effect only to refresh UI of c
        sideEffect.emit(GroceriesListEffect.RecipeAdded(recipeId, guest))
        val newGl = alterRecipeInfos(recipesInfos, states.copy(
           groceriesList = states.groceriesList.copy(
               attributes = states.groceriesList.attributes!!.copy(
                   recipesInfos = recipesInfos
                )
            )
        ))
        dispatch(GroceriesListAction.SetGroceriesList(newGl))
    }

    private suspend fun removeRecipe(recipeId: String, states: GroceriesListState) {
        if (states.groceriesList == null || !states.groceriesList.hasRecipe(recipeId)) return
        val recipesInfos = states.groceriesList.attributes!!.recipesInfos

        val newRecipeInfos = recipesInfos!!.filter { el -> el.id.toString() != recipeId }.toMutableList()

        sideEffect.emit(GroceriesListEffect.RecipeRemoved(recipeId))
        val newGl = alterRecipeInfos(newRecipeInfos, states)
        dispatch(GroceriesListAction.SetGroceriesList(newGl))
    }

    private suspend fun alterRecipeInfos(recipesInfos: MutableList<RecipeInfos>, states: GroceriesListState): GroceriesList {
        val gl = states.groceriesList!!.copy(
            attributes = states.groceriesList.attributes!!.copy(
                recipesInfos = recipesInfos,
                appendRecipes = false
            )
        )
         LogHandler.debug("[Miam] gl $gl")
        return groceriesListRepo.updateGroceriesList(gl)
    }
}