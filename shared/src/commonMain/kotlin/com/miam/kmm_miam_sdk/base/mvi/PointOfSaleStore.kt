package com.miam.kmm_miam_sdk.base.mvi


import com.miam.kmm_miam_sdk.miam_core.data.repository.PointOfSaleRepositoryImp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

data class PointOfSaleState(
    val idSupplier :Int?,
    val extIdPointOfSale: String?,
    val idPointOfSale: Int?,
) : State

sealed class  PointOfSaleAction : Action {
    data class RefreshPointOfSale(val idPointOfSale: Int) : PointOfSaleAction()
    data class SetExtId(val extId: String) :PointOfSaleAction()
    data class SetSupplierId(val supplierId: Int): PointOfSaleAction()
    data class Error(val error: Exception) : PointOfSaleAction()
}

sealed class  PointOfSaleEffect : Effect {
    data class Error(val error: Exception) :  PointOfSaleEffect()
    data class PointOfSaleChanged(val idPointOfSale: Int) :  PointOfSaleEffect()
}

class PointOfSaleStore : Store<PointOfSaleState, PointOfSaleAction, PointOfSaleEffect>, KoinComponent,
    CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private val state = MutableStateFlow(PointOfSaleState(null, null, null))
    private val sideEffect = MutableSharedFlow<PointOfSaleEffect>()

    private val basketStore:  BasketStore by inject()
    private val pointOfSaleRepository : PointOfSaleRepositoryImp by inject()

    override fun observeState(): StateFlow<PointOfSaleState> = state

    override fun observeSideEffect(): Flow<PointOfSaleEffect> = sideEffect

    override fun dispatch(action: PointOfSaleAction) {
        val oldState = state.value

        val newState = when (action) {
            is PointOfSaleAction.RefreshPointOfSale -> {
                // println("Miam refresh pos")
                if (oldState.idPointOfSale == action.idPointOfSale) {
                    // println("Miam refresh pos same pos")
                    oldState
                } else {
                    basketStore.dispatch(BasketAction.SetIdPointOfSale(action.idPointOfSale))
                    oldState.copy(idPointOfSale = action.idPointOfSale)
                }
            }
            is PointOfSaleAction.SetExtId -> {
                // println("Miam refresh ext_id")
                if (oldState.extIdPointOfSale == action.extId) {
                    // println("Miam refresh ext_id same ext_id")
                    oldState
                } else {
                    launch {
                        getPos(action.extId, oldState.idSupplier)
                    }
                    oldState.copy(extIdPointOfSale = action.extId)
                }
            }
            is PointOfSaleAction.SetSupplierId -> {
                // println("Miam refresh SetSupplierId")
                if (oldState.idSupplier == action.supplierId) {
                    // println("Miam refresh SetSupplierId same SetSupplierId")
                    oldState
                } else {
                    launch {
                        getPos(oldState.extIdPointOfSale, action.supplierId)
                    }
                    oldState.copy(idSupplier = action.supplierId)
                }
            }
            is PointOfSaleAction.Error -> {
                TODO("handle errors")
                oldState
            }
        }
        if (newState != oldState) {
            state.value = newState
        }
    }

    private  fun getPos(extIdPointOfSale: String?, idSupplier: Int?) {
        if(extIdPointOfSale == null || idSupplier == null ) return

        try {
            launch {
                pointOfSaleRepository.getPosFormExtId(extIdPointOfSale,idSupplier)
                    .collect {
                        dispatch(PointOfSaleAction.RefreshPointOfSale(it.id))
                    }
            }
        } catch (e: Exception) {
            dispatch(PointOfSaleAction.Error(e))
        }
    }
}
