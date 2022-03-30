package com.miam.kmm_miam_sdk.base.mvi

import com.miam.kmm_miam_sdk.miam_core.data.repository.GroceriesListRepositoryImp
import com.miam.kmm_miam_sdk.miam_core.model.GroceriesList
import com.miam.kmm_miam_sdk.miam_core.model.GroceriesListWithoutRelationship
import com.miam.kmm_miam_sdk.miam_core.model.RecipeInfos
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


data class GroceriesListState(
    val groceriesList : GroceriesList?
) : State

sealed class  GroceriesListAction : Action {
    object RefreshGroceriesList : GroceriesListAction()
    object ResetGroceriesList : GroceriesListAction()
    data class SetGroceriesList(val gl :GroceriesList) : GroceriesListAction()
    data class AlterRecipeList(val recipeId : Int, val guests: Int) : GroceriesListAction()
    data class RemoveRecipe(val recipeId: Int): GroceriesListAction()
    data class Error(val error: Exception) : GroceriesListAction()
}
sealed class  GroceriesListEffect : Effect {
    data class Error(val error: Exception) :  GroceriesListEffect()
    object GroceriesListLoaded :  GroceriesListEffect()
    data class RecipeAdded(val recipeId: Int,val guests: Int): GroceriesListEffect()
    data class RecipeRemoved(val recipeId: Int) :GroceriesListEffect()
}

class GroceriesListStore : Store<GroceriesListState, GroceriesListAction, GroceriesListEffect>, KoinComponent,
    CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private val coroutineHandler = CoroutineExceptionHandler {
            _, exception -> println("Miam error in GroceriesListStore $exception")
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

    private suspend fun appendRecipe(recipeId :Int, guest: Int, states :GroceriesListState)  {
        if(states.groceriesList == null) return
        val recipesInfos =  states.groceriesList.attributes.recipesInfos
        if(states.groceriesList.hasRecipe(recipeId)) {
            if(states.groceriesList.guestsForRecipe(recipeId) == guest) return
            recipesInfos.find { it.id == recipeId }?.guests = guest
        } else {
            recipesInfos.add(RecipeInfos(recipeId, guest))
        }
        // side Effect only to refresh UI of c
        sideEffect.emit(GroceriesListEffect.RecipeAdded(recipeId, guest))
        alterRecipeInfos(recipesInfos, states)
        dispatch(GroceriesListAction.RefreshGroceriesList)
    }

    private suspend fun removeRecipe(recipeId: Int, states: GroceriesListState) {
        if (states.groceriesList == null || !states.groceriesList.hasRecipe(recipeId)) return
        val recipesInfos = states.groceriesList.attributes.recipesInfos

        val newRecipeInfos = recipesInfos!!.filter { el -> el.id != recipeId }.toMutableList()

        sideEffect.emit(GroceriesListEffect.RecipeRemoved(recipeId))
        alterRecipeInfos(newRecipeInfos, states)
        dispatch(GroceriesListAction.RefreshGroceriesList)
    }

    private suspend fun alterRecipeInfos(
        recipesInfos: MutableList<RecipeInfos>,
        states: GroceriesListState
    ): GroceriesList {
        val gl = states.groceriesList!!.copy(
            attributes = states.groceriesList.attributes.copy(
                recipesInfos = recipesInfos,
                appendRecipes = false
            )
        )
        return groceriesListRepo.updateGroceriesList(
            GroceriesListWithoutRelationship(
                gl.id,
                gl.type,
                gl.attributes
            )
        )
    }
}