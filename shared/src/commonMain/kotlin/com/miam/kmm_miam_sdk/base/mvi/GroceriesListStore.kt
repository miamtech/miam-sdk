package com.miam.kmm_miam_sdk.base.mvi

import com.miam.kmm_miam_sdk.miam_core.data.repository.GroceriesListRepositoryImp
import com.miam.kmm_miam_sdk.miam_core.model.GroceriesList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


data class GroceriesListState(
    val groceriesList : GroceriesList?,
) : State

sealed class  GroceriesListAction : Action {
    object RefreshGroceriesList : GroceriesListAction()
    data class SetGroceriesList(val gl :GroceriesList) : GroceriesListAction()
    data class Error(val error: Exception) : GroceriesListAction()
}
sealed class  GroceriesListEffect : Effect {
    data class Error(val error: Exception) :  GroceriesListEffect()
    data class GroceriesListChanged(val glId: Int) :  GroceriesListEffect()
}

class GroceriesListStore : Store<GroceriesListState, GroceriesListAction, GroceriesListEffect>, KoinComponent,
    CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private val state = MutableStateFlow(GroceriesListState( null))
    private val sideEffect = MutableSharedFlow<GroceriesListEffect>()

    private val groceriesListRepo : GroceriesListRepositoryImp by inject()
    private val basketStore:  BasketStore by inject()

    override fun observeState(): StateFlow<GroceriesListState> = state

    override fun observeSideEffect(): Flow<GroceriesListEffect> = sideEffect

    override fun dispatch(action: GroceriesListAction) {
        val oldState = state.value

        val newState = when (action) {
            is GroceriesListAction.RefreshGroceriesList -> {
                launch { loadGroceriesList() }
                oldState
            }
            is GroceriesListAction.SetGroceriesList -> {
                basketStore.dispatch(BasketAction.SetIdGroceriesList(action.gl.id))
                oldState.copy(groceriesList = action.gl)
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
        try {
            launch {
                groceriesListRepo.getCurrent()
                    .collect {
                        dispatch(GroceriesListAction.SetGroceriesList(it))
                    }
            }
        } catch (e: Exception) {
            dispatch(GroceriesListAction.Error(e))
        }
    }
}