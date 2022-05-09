package com.miam.kmm_miam_sdk.base.mvi


import com.miam.kmm_miam_sdk.handler.LogHandler
import com.miam.kmm_miam_sdk.miam_core.data.repository.PointOfSaleRepositoryImp
import kotlinx.coroutines.*

import kotlinx.coroutines.flow.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

data class PointOfSaleState(
    val idSupplier :Int?,
    val extIdPointOfSale: String?,
    val idPointOfSale: Int?,
    val origin: String?,
) : State

sealed class  PointOfSaleAction : Action {
    data class SetExtId(val extId: String?) :PointOfSaleAction()
    data class SetSupplierId(val supplierId: Int): PointOfSaleAction()
}

sealed class  PointOfSaleEffect : Effect

class PointOfSaleStore: Store<PointOfSaleState, PointOfSaleAction, PointOfSaleEffect>, KoinComponent,
    CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private val coroutineHandler = CoroutineExceptionHandler {
            _, exception -> println("Miam error in BasketStore $exception")
    }

    override val state = MutableStateFlow(PointOfSaleState(null, null, null, null))
    private val sideEffect = MutableSharedFlow<PointOfSaleEffect>()

    private val basketStore:  BasketStore by inject()
    private val pointOfSaleRepository : PointOfSaleRepositoryImp by inject()

    override fun observeState(): StateFlow<PointOfSaleState> = state

    override fun observeSideEffect(): Flow<PointOfSaleEffect> = sideEffect

    override fun dispatch(action: PointOfSaleAction): Job {
        when (action) {
            is PointOfSaleAction.SetExtId -> {
                updateStateIfChanged(state.value.copy(extIdPointOfSale = action.extId))
                return launch(coroutineHandler) {
                    if (action.extId != null && state.value.idSupplier != null) {
                        val pos = pointOfSaleRepository.getPosFormExtId(action.extId, state.value.idSupplier!!)
                        updateStateIfChanged(state.value.copy(
                            extIdPointOfSale = action.extId,
                            idPointOfSale = pos.id
                        ))
                        basketStore.dispatch(BasketAction.RefreshBasket)
                    }
                }
            }
            is PointOfSaleAction.SetSupplierId -> {
                updateStateIfChanged(state.value.copy(idSupplier = action.supplierId))
                return launch(coroutineHandler) {
                    if (state.value.extIdPointOfSale != null) {
                        val pos = pointOfSaleRepository.getPosFormExtId(state.value.extIdPointOfSale!!, action.supplierId)
                        updateStateIfChanged(state.value.copy(
                            idSupplier = action.supplierId,
                            idPointOfSale = pos.id
                        ))
                        basketStore.dispatch(BasketAction.RefreshBasket)
                    }
                }
            }
        }
    }

    fun samePos(extId: String?): Boolean {
        return extId == state.value.extIdPointOfSale
    }

    fun sameSupplier(supplierId: Int): Boolean {
        return supplierId == state.value.idSupplier
    }

    fun setOrigin(origin: String) {
        updateStateIfChanged(state.value.copy(origin = origin))
    }

    fun getPosId(): Int? {
        return state.value.idPointOfSale
    }

    fun getProviderId(): Int{
        return state.value.idSupplier ?: -1
    }

    fun getProviderOrigin(): String {
        return state.value.origin ?: ""
    }
}
