package com.miam.kmm_miam_sdk.base.mvi

import com.miam.kmm_miam_sdk.miam_core.data.repository.BasketRepositoryImp
import com.miam.kmm_miam_sdk.miam_core.model.Basket
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

data class BasketState(
    val idGroceriesList : Int?,
    val idPointOfSale : Int?,
    val basket :Basket?
) : State

sealed class  BasketAction : Action {
    data class RefreshBasket(val idGroceriesList: Int, val idPointOfSale: Int): BasketAction()
    data class SetBasket(val basket: Basket): BasketAction()
    data class SetIdGroceriesList(val groceriesListId: Int) : BasketAction()
    data class SetIdPointOfSale(val pointOfSaleId: Int) : BasketAction()
    data class Error(val error: Exception) : BasketAction()
}

sealed class  BasketEffect : Effect {
    data class Error(val error: Exception) :  BasketEffect()
    data class PointOfSaleChanged(val idPointOfSale: Int) :  BasketEffect()
}

class BasketStore : Store<BasketState, BasketAction, BasketEffect>, KoinComponent,
    CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private val state = MutableStateFlow(BasketState(null, null, null))
    private val sideEffect = MutableSharedFlow<BasketEffect>()
    private val basketRepo : BasketRepositoryImp by inject()

    override fun observeState(): StateFlow<BasketState> = state

    override fun observeSideEffect(): Flow<BasketEffect> = sideEffect

    override fun dispatch(action: BasketAction) {
        val oldState = state.value

        val newState = when (action) {
            is BasketAction.RefreshBasket -> {
                launch {
                        loadBasket(action.idGroceriesList, action.idPointOfSale)
                }
                oldState
            }
            is BasketAction.SetIdGroceriesList -> {
                val tmp =  oldState.copy(idGroceriesList = action.groceriesListId)
                shouldUpdateBasket(tmp)
                tmp
            }
            is BasketAction.SetIdPointOfSale -> {
                val tmp = oldState.copy(idPointOfSale = action.pointOfSaleId)
                shouldUpdateBasket(tmp)
                tmp
            }
            is BasketAction.SetBasket -> {
                oldState.copy(basket = action.basket)
            }
            is BasketAction.Error -> {
                oldState
            }
        }
        if (newState != oldState) {
            state.value = newState
        }
    }

    private fun shouldUpdateBasket(tmpState: BasketState){
        if(tmpState == state.value || tmpState.idGroceriesList  == null  || tmpState.idPointOfSale == null ) return
        dispatch(BasketAction.RefreshBasket(tmpState.idGroceriesList,tmpState.idPointOfSale))
    }

    private suspend fun loadBasket(idGroceriesList: Int,idPointOfSale :Int ) {
        try {
            launch {
                basketRepo.getFromListAndPos(idGroceriesList,idPointOfSale)
                    .collect {
                        dispatch(BasketAction.SetBasket(it))
                    }
            }
        } catch (e: Exception) {
            dispatch(BasketAction.Error(e))
        }
    }

}