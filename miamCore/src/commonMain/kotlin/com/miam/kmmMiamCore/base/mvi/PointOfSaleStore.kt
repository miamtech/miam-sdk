package com.miam.kmmMiamCore.base.mvi


import com.miam.kmmMiamCore.base.executor.ExecutorHelper
import com.miam.kmmMiamCore.miam_core.data.repository.PointOfSaleRepositoryImp
import kotlinx.coroutines.*

import kotlinx.coroutines.flow.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

data class PointOfSaleState(
    val idSupplier :Int?,
    val extIdPointOfSale: String?,
    val idPointOfSale: Int?,
    val origin: String?,
    val currentJob: Job? = null
): State

sealed class  PointOfSaleAction : Action {
    data class SetExtId(val extId: String?) :PointOfSaleAction()
    data class SetSupplierId(val supplierId: Int): PointOfSaleAction()
}

sealed class  PointOfSaleEffect : Effect

class PointOfSaleStore: Store<PointOfSaleState, PointOfSaleAction, PointOfSaleEffect>, KoinComponent,
    CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private val coroutineHandler = CoroutineExceptionHandler {
            _, exception -> println("Miam error in BasketStore $exception ${exception.stackTraceToString()}")
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
                if (!canFetch()) return com.miam.kmmMiamCore.base.executor.ExecutorHelper.emptyJob()

                return launchNewPosRefresh()
            }
            is PointOfSaleAction.SetSupplierId -> {
                updateStateIfChanged(state.value.copy(idSupplier = action.supplierId))
                if (!canFetch()) return com.miam.kmmMiamCore.base.executor.ExecutorHelper.emptyJob()

                return launchNewPosRefresh()
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

    private fun canFetch(): Boolean {
        return state.value.extIdPointOfSale != null && state.value.idSupplier != null
    }

    private fun launchNewPosRefresh(): Job {
        com.miam.kmmMiamCore.base.executor.ExecutorHelper.cancelRunningJob(state.value.currentJob)
        val currentJob = launch(coroutineHandler) {
            val pos = pointOfSaleRepository.getPosFormExtId(
                state.value.extIdPointOfSale!!,
                state.value.idSupplier!!
            )
            updateStateIfChanged(state.value.copy(idPointOfSale = pos.id))
            basketStore.dispatch(BasketAction.RefreshBasket)
        }
        updateStateIfChanged(state.value.copy(currentJob = currentJob))
        return currentJob
    }
}
