package com.miam.kmmMiamCore.base.mvi


import com.miam.core.sdk.di.MiamDI
import com.miam.kmmMiamCore.base.executor.ExecutorHelper
import com.miam.kmmMiamCore.miam_core.data.repository.PointOfSaleRepositoryImp
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

public data class PointOfSaleState(
    val idSupplier: Int?,
    val extIdPointOfSale: String?,
    val idPointOfSale: Int?,
    val origin: String?,
    val currentJob: Job? = null
): State

public sealed class PointOfSaleAction: Action {
    public data class SetExtId(val extId: String?): PointOfSaleAction()
    public data class SetSupplierId(val supplierId: Int): PointOfSaleAction()
}

public sealed class PointOfSaleEffect: Effect

public class PointOfSaleStore: Store<PointOfSaleState, PointOfSaleAction, PointOfSaleEffect>,
    CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        println("Miam error in BasketStore $exception ${exception.stackTraceToString()}")
    }

    override val state: MutableStateFlow<PointOfSaleState> = MutableStateFlow(PointOfSaleState(null, null, null, null))
    private val sideEffect = MutableSharedFlow<PointOfSaleEffect>()

    // TODO By lazy allows cyclic dependencies, even if it is bad design
    private val basketStore: BasketStore by lazy { MiamDI.basketStore }
    private val pointOfSaleRepository: PointOfSaleRepositoryImp by lazy { MiamDI.pointOfSaleRepository }

    override fun observeState(): StateFlow<PointOfSaleState> = state

    override fun observeSideEffect(): Flow<PointOfSaleEffect> = sideEffect

    override fun dispatch(action: PointOfSaleAction): Job {
        when (action) {
            is PointOfSaleAction.SetExtId -> {
                updateStateIfChanged(state.value.copy(extIdPointOfSale = action.extId))
                if (!canFetch()) return ExecutorHelper.emptyJob()

                return launchNewPosRefresh()
            }
            is PointOfSaleAction.SetSupplierId -> {
                updateStateIfChanged(state.value.copy(idSupplier = action.supplierId))
                if (!canFetch()) return ExecutorHelper.emptyJob()

                return launchNewPosRefresh()
            }
        }
    }

    public fun samePos(extId: String?): Boolean {
        return extId == state.value.extIdPointOfSale
    }

    public fun sameSupplier(supplierId: Int): Boolean {
        return supplierId == state.value.idSupplier
    }

    public fun setOrigin(origin: String) {
        updateStateIfChanged(state.value.copy(origin = origin))
    }

    public fun getPosId(): Int? {
        return state.value.idPointOfSale
    }

    public val supplierId: Int?
        get() = state.value.idSupplier

    public fun getProviderOrigin(): String {
        return state.value.origin ?: ""
    }

    private fun canFetch(): Boolean {
        return state.value.extIdPointOfSale != null && state.value.idSupplier != null
    }

    private fun launchNewPosRefresh(): Job {
        ExecutorHelper.cancelRunningJob(state.value.currentJob)
        val currentJob = launch(coroutineHandler) {
            val fetchedPos = pointOfSaleRepository.getPosFormExtId(
                state.value.extIdPointOfSale!!,
                state.value.idSupplier!!
            )
            fetchedPos?.let { pos ->
                updateStateIfChanged(state.value.copy(idPointOfSale = pos.id))
                basketStore.dispatch(BasketAction.RefreshBasket)
            }
        }
        updateStateIfChanged(state.value.copy(currentJob = currentJob))
        return currentJob
    }
}
