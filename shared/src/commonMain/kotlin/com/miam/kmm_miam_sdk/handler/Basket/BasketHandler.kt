package com.miam.kmm_miam_sdk.handler.Basket

import com.miam.kmm_miam_sdk.base.mvi.*
import com.miam.kmm_miam_sdk.handler.ContextHandlerInstance
import com.miam.kmm_miam_sdk.handler.LogHandler
import com.miam.kmm_miam_sdk.miam_core.model.Basket
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry
import com.miam.kmm_miam_sdk.miam_core.model.BasketPreviewLine
import com.miam.kmm_miam_sdk.miam_core.model.RetailerProduct
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object BasketHandlerInstance: KoinComponent {
    val instance: BasketHandler by inject()
}

data class BasketHandlerState(
    val comparator: BasketComparator? = null,
    val isProcessingRetailerEvent: Boolean = false,
    val firstMiamBasket: List<BasketEntry>? = null,
    val firstRetailerBasket: List<RetailerProduct>? = null
): State

class BasketHandler: KoinComponent, CoroutineScope by CoroutineScope(Dispatchers.Main)  {
    private val basketStore: BasketStore by inject()

    val state = MutableStateFlow(BasketHandlerState())

    var paymentTotal: () -> Double = fun():Double{return 0.0}
    var pushProductsToRetailerBasket: (products: List<RetailerProduct>) -> Unit = fun(_: List<Any>) {
        throw Error("pushProductsToBasket not implemented")
    }
    var listenToRetailerBasket: () -> Unit = fun(){
        throw Error("listenToRetailerBasket not implemented")
    }

    fun isReady(): Boolean {
        return state.value.comparator != null
    }

    private fun initFirstMiamBasket(miamActiveBasket: List<BasketEntry>) {
        state.value = state.value.copy(firstMiamBasket = miamActiveBasket)
        initIfPossible()
    }

    private fun initFirstRetailerBasket(retailerBasket: List<RetailerProduct>) {
        state.value = state.value.copy(firstRetailerBasket = retailerBasket)
        initIfPossible()
    }

    private fun initIfPossible() {
        LogHandler.info("Will try to init handler ${state.value.firstMiamBasket} ${state.value.firstRetailerBasket}")
        if (state.value.firstMiamBasket == null || state.value.firstRetailerBasket == null) return

        val newComparator = BasketComparator(state.value.firstRetailerBasket!!, state.value.firstMiamBasket!!)
        state.value = state.value.copy(comparator = newComparator)
        processRetailerEvent(state.value.firstRetailerBasket!!)
        listenToRetailerBasket()
        ContextHandlerInstance.instance.getReady()
    }

    fun basketChange(miamActiveBasket: List<BasketEntry>) {
        LogHandler.info("Miam basket changed $miamActiveBasket")
        if(!isReady()) return initFirstMiamBasket(miamActiveBasket)

        if (state.value.isProcessingRetailerEvent) return

        // When comparison is already initialized, we just update it
        val toPushToRetailer = state.value.comparator!!.updateReceivedFromMiam(miamActiveBasket)
        sendUpdateToRetailer(toPushToRetailer)
    }

    private fun sendUpdateToRetailer(itemsToAdd: List<RetailerProduct>) {
        if (itemsToAdd.isEmpty()) {
            return
        }
        pushProductsToRetailerBasket(itemsToAdd)
    }

    private fun processRetailerEvent(retailerBasket: List<RetailerProduct>) {
        state.value = state.value.copy(isProcessingRetailerEvent = true)
        val toRemoveFromMiam = state.value.comparator!!.updateReceivedFromRetailer(retailerBasket)
        sendUpdateToMiam(toRemoveFromMiam)
    }

    private fun sendUpdateToMiam(entriesToRemove : List<AlterQuantityBasketEntry>) {
        if (entriesToRemove.isEmpty()){
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

    fun pushProductsToMiamBasket(retailerBasket: List<RetailerProduct>) {
        LogHandler.info("Retailer basket changed $retailerBasket")
        if(!isReady()) return initFirstRetailerBasket(retailerBasket)

        processRetailerEvent(retailerBasket)
    }

    fun dispose() {
        state.value = state.value.copy(comparator = null)
    }

    fun handlePayment() {
        //TODO handle analytic
        val total = paymentTotal()
        if (basketStore.basketIsEmpty()) { return }
        basketStore.dispatch(BasketAction.ConfirmBasket(price = total.toString()))
    }
}
