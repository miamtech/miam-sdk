package com.miam.kmmMiamCore.handler.Basket

import com.miam.core.sdk.di.MiamDI
import com.miam.kmmMiamCore.base.mvi.AlterQuantityBasketEntry
import com.miam.kmmMiamCore.base.mvi.BasketAction
import com.miam.kmmMiamCore.base.mvi.BasketStore
import com.miam.kmmMiamCore.base.mvi.State
import com.miam.kmmMiamCore.handler.ContextHandlerInstance
import com.miam.kmmMiamCore.handler.LogHandler
import com.miam.kmmMiamCore.miam_core.model.BasketEntry
import com.miam.kmmMiamCore.miam_core.model.RetailerProduct
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow

public object BasketHandlerInstance {
    public val instance: BasketHandler = MiamDI.basketHandler
}

public data class BasketHandlerState(
    val comparator: BasketComparator? = null,
    val isProcessingRetailerEvent: Boolean = false,
    val firstMiamActiveBasket: List<BasketEntry>? = null,
    val firstRetailerBasket: List<RetailerProduct>? = null,
    val pushProductsToRetailerBasket: (products: List<RetailerProduct>) -> Unit = fun(_: List<Any>) {
        LogHandler.error("pushProductsToBasket not implemented")
    },
    val listenToRetailerBasket: () -> Unit = fun() {
        LogHandler.error("listenToRetailerBasket not implemented")
    }
): State

public class BasketHandler: CoroutineScope by MainScope() {
    // TODO By lazy allows cyclic dependencies, even if it is bad design
    private val basketStore: BasketStore by lazy { MiamDI.basketStore }

    public val state: MutableStateFlow<BasketHandlerState> = MutableStateFlow(BasketHandlerState())

    public fun isReady(): Boolean {
        return state.value.comparator != null
    }

    private fun initFirstMiamBasket(miamActiveBasket: List<BasketEntry>) {
        state.value = state.value.copy(firstMiamActiveBasket = miamActiveBasket)
        initIfPossible()
    }

    private fun initFirstRetailerBasket(retailerBasket: List<RetailerProduct>) {
        state.value = state.value.copy(firstRetailerBasket = retailerBasket)
        initIfPossible()
    }

    private fun initIfPossible() {
        LogHandler.info("Will try to init handler ${state.value.firstRetailerBasket} ${state.value.firstMiamActiveBasket}")
        if (state.value.firstMiamActiveBasket == null || state.value.firstRetailerBasket == null) return

        var newComparator = BasketComparator()
        newComparator = newComparator.init(
            state.value.firstRetailerBasket!!,
            state.value.firstMiamActiveBasket!!
        )
        state.value = state.value.copy(comparator = newComparator)
        processRetailerEvent(state.value.firstRetailerBasket!!)
        this.state.value.listenToRetailerBasket()
        ContextHandlerInstance.instance.emitReadiness()
    }

    public fun basketChange(miamActiveBasket: List<BasketEntry>) {
        LogHandler.info("Miam basket changed ${state.value.comparator} $miamActiveBasket")
        if (!isReady()) return initFirstMiamBasket(miamActiveBasket)

        if (state.value.isProcessingRetailerEvent) return

        // When comparison is already initialized, we just update it
        val newComparator = state.value.comparator!!.updateReceivedFromMiam(miamActiveBasket)
        val updateToSend = state.value.comparator!!.resolveFromMiam(newComparator)
        state.value = state.value.copy(comparator = newComparator)
        LogHandler.info("Miam basket changed finished ${state.value.comparator} ")
        sendUpdateToRetailer(updateToSend)
    }

    private fun sendUpdateToRetailer(itemsToAdd: List<RetailerProduct>) {
        LogHandler.info("Miam will sendUpdateToRetailer $itemsToAdd")
        if (itemsToAdd.isEmpty()) {
            return
        }
        this.state.value.pushProductsToRetailerBasket(itemsToAdd)
    }

    private fun processRetailerEvent(retailerBasket: List<RetailerProduct>) {
        LogHandler.info("processRetailerEvent ${state.value.comparator} $retailerBasket")
        state.value = state.value.copy(isProcessingRetailerEvent = true)
        val newComparator = state.value.comparator!!.updateReceivedFromRetailer(retailerBasket)
        sendUpdateToMiam(state.value.comparator!!.resolveFromRetailer(newComparator))
        state.value = state.value.copy(comparator = newComparator)
        LogHandler.info("processRetailerEvent finished ${state.value.comparator} $retailerBasket")
    }

    private fun sendUpdateToMiam(entriesToRemove: List<AlterQuantityBasketEntry>) {
        LogHandler.info("Miam will sendUpdateToMiam $entriesToRemove")
        if (entriesToRemove.isEmpty()) {
            state.value = state.value.copy(isProcessingRetailerEvent = false)
            return
        }

        //update the entries and stop proccessing at end
        val basketAction = BasketAction.UpdateBasketEntriesDiff(entriesToRemove)
        val job = basketStore.dispatch(basketAction)
        job.invokeOnCompletion {
            state.value = state.value.copy(isProcessingRetailerEvent = false)
        }
    }

    /**
     * called from app
     */

    public fun setPushProductsToRetailerBasket(func: (products: List<RetailerProduct>) -> Unit) {
        state.value = state.value.copy(pushProductsToRetailerBasket = func)
    }

    public fun setListenToRetailerBasket(func: () -> Unit) {
        state.value = state.value.copy(listenToRetailerBasket = func)
    }

    public fun pushProductsToMiamBasket(retailerBasket: List<RetailerProduct>) {
        LogHandler.info("Miam Retailer basket changed $retailerBasket")
        if (!isReady()) return initFirstRetailerBasket(retailerBasket)

        processRetailerEvent(retailerBasket)
    }

    public fun dispose() {
        state.value = state.value.copy(comparator = null)
        ContextHandlerInstance.instance.emitReadiness()
    }

    public fun handlePayment(totalAmount: Double) {
        //TODO handle analytic
        LogHandler.info("Miam will handle payment for ${basketStore.getBasket()}")
        if (basketStore.basketIsEmpty()) {
            return
        }
        basketStore.dispatch(BasketAction.ConfirmBasket(price = totalAmount.toString()))
    }
}
