package com.miam.kmm_miam_sdk.base.mvi

import com.miam.kmm_miam_sdk.miam_core.data.repository.GroceriesListRepositoryImp
import com.miam.kmm_miam_sdk.miam_core.model.GroceriesList
import com.miam.kmm_miam_sdk.miam_core.model.RecipeInfos
import com.miam.kmm_miam_sdk.miam_core.model.Record
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


data class GroceriesListState(
    val groceriesList : GroceriesList?,
) : State

sealed class  GroceriesListAction : Action {
    object RefreshGroceriesList : GroceriesListAction()
    object ResetGroceriesList : GroceriesListAction()
    data class SetGroceriesList(val gl :GroceriesList) : GroceriesListAction()
    data class AlterRecipeList(val recipeId: String, val guests: Int) : GroceriesListAction()
    data class RemoveRecipe(val recipeId: String): GroceriesListAction()
    object RemoveAllRecipe : GroceriesListAction()
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

    private val state = MutableStateFlow(GroceriesListState( null))
    private val sideEffect = MutableSharedFlow<GroceriesListEffect>()

    private val groceriesListRepo : GroceriesListRepositoryImp by inject()
    private val basketStore:  BasketStore by inject()

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
                launch { loadGroceriesList() }
                oldState
            }
            is GroceriesListAction.ResetGroceriesList -> {
                println("MIAM --> basket reset GroceriesList")
                launch { restGroceriesList() }
                oldState
            }
            is GroceriesListAction.SetGroceriesList -> {
                // println("Miam -> GroceriesListAction.SetGroceriesList")
                basketStore.dispatch(BasketAction.SetGroceriesList(action.gl))
                launch { sideEffect.emit(GroceriesListEffect.GroceriesListLoaded)}
                oldState.copy(groceriesList = action.gl)
            }
            is GroceriesListAction.AlterRecipeList -> {
               launch { appendRecipe(action.recipeId, action.guests,oldState)}
                oldState
            }
            is GroceriesListAction.RemoveRecipe -> {
                launch { removeRecipe(action.recipeId, oldState) }
                oldState
            }
            is GroceriesListAction.RemoveAllRecipe -> {
                launch { removeAllRecipe(oldState) }
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

    private suspend fun loadGroceriesList() {
        val currentList = groceriesListRepo.getCurrent()
        // println("Miam current list : " + Json.encodeToString(Record.serializer(), currentList))
        dispatch(GroceriesListAction.SetGroceriesList(currentList))
    }

    private fun appendRecipe(recipeId: String, guest: Int, states :GroceriesListState)  {
        if(states.groceriesList == null) return
        val recipesInfos = states.groceriesList.attributes!!.recipesInfos ?: mutableListOf()
        if(states.groceriesList.hasRecipe(recipeId)) {
            if(states.groceriesList.guestsForRecipe(recipeId) == guest) return
            recipesInfos.find { it.id.toString() == recipeId }?.guests = guest
        } else {
            recipesInfos.add(RecipeInfos(recipeId.toInt(), guest))
        }
        launch { sideEffect.emit(GroceriesListEffect.RecipeAdded(recipeId, guest))}
        alterRecipeInfos(recipesInfos,states)
    }

    private fun removeRecipe(recipeId: String,  states :GroceriesListState){
        if(states.groceriesList == null || !states.groceriesList.hasRecipe(recipeId)) return
        val recipesInfos =  states.groceriesList.attributes!!.recipesInfos
        launch { sideEffect.emit(GroceriesListEffect.RecipeRemoved(recipeId))}
        val newRecipeInfos = recipesInfos!!.filter { el -> el.id.toString() != recipeId }.toMutableList()
        alterRecipeInfos(newRecipeInfos, states)
    }

    private fun removeAllRecipe(states :GroceriesListState) {
        if(states.groceriesList == null || states.groceriesList.attributes!!.recipesInfos.isNullOrEmpty()) return
        alterRecipeInfos(mutableListOf(), states)
    }

    private fun alterRecipeInfos(recipesInfos : MutableList<RecipeInfos>, states :GroceriesListState){
        var gl = states.groceriesList!!.copy(
            attributes = states.groceriesList.attributes!!.copy(
                recipesInfos =  recipesInfos,
                appendRecipes = false))
        launch { alterList(gl) }
    }

    private suspend fun restGroceriesList(){
        try {
            launch {
                var newGl = groceriesListRepo.getNew()
                dispatch(GroceriesListAction.SetGroceriesList(newGl))
            }
        } catch (e: Exception) {
            dispatch(GroceriesListAction.Error(e))
        }
    }

    private suspend fun alterList(gl :GroceriesList){
        try {
            launch {
                val updatedGl = groceriesListRepo.updateGroceriesList(gl)
                dispatch(GroceriesListAction.SetGroceriesList(updatedGl))
            }
        }  catch (e: Exception) {
            dispatch(GroceriesListAction.Error(e))
        }
    }
}