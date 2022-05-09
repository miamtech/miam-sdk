package com.miam.kmm_miam_sdk.handler.Basket

import com.miam.kmm_miam_sdk.base.mvi.*
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry
import com.miam.kmm_miam_sdk.miam_core.model.RetailerProduct
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object BasketHandlerInstance: KoinComponent {
    val instance: BasketHandler by inject()
}

class BasketHandler: KoinComponent, CoroutineScope by CoroutineScope(Dispatchers.Main)  {
    private val basketStore: BasketStore by inject()

    private var comparator: BasketComparator? = null
    var isProcessingRetailerEvent : Boolean = false
    private var miamActiveBasket: List<BasketEntry>? = null

    var paymentTotal: () -> Double = fun():Double{return 0.0}
    var pushProductsToBasket: (products: List<RetailerProduct>) -> Unit = fun(_: List<Any>) {
        throw Error("pushProductsToBasket not implemented")
    }
    var listenToRetailerBasket: (_: (products: List<RetailerProduct>) -> Unit) -> Unit = fun(_ : (products: List<RetailerProduct>) -> Unit){
        println("Miam --> please init listenToRetailerBasket")
    }

    private var basketListnerJob: Job? = null

    fun basketChange(miamActiveBasket: List<BasketEntry> ) {
        // assign the entries so that the callback retailerBasketChangeCallBack can use them
        this.miamActiveBasket = miamActiveBasket
        // Comparison should be initialized when first Miam basket is received, based on existing Retailer basket
        if (comparator == null){
            listenToRetailerBasket(::retailerBasketChangeCallBack)
            return
        }
        if (isProcessingRetailerEvent) return

        // When comparison is already initialized, we just update it
        val toPushToRetailer = comparator!!.updateReceivedFromMiam(miamActiveBasket)
        sendUpdateToRetailer(toPushToRetailer)
    }

    private fun sendUpdateToRetailer(itemsToAdd: List<RetailerProduct>) {
        if (itemsToAdd.isEmpty()) {
            return
        }
        pushProductsToBasket(itemsToAdd)
    }

    /*
    Should update Miam basket accordingly, ie remove entries that are not in the basket anymore
    */
    private fun retailerBasketChangeCallBack(retailerBasket: List<RetailerProduct>){
        if(miamActiveBasket == null) {
            // can't update Miam basket if there is nothing in there. Should not happen
            return
        }
        if(comparator == null ){
            // create comparator that make the first sync
            comparator = BasketComparator( this, retailerBasket, miamActiveBasket!!)
        }
        isProcessingRetailerEvent = true
        val toRemoveFromMiam = comparator!!.updateReceivedFromRetailer(retailerBasket)
        sendUpdateToMiam(toRemoveFromMiam)
    }

    private fun sendUpdateToMiam(entriesToRemove : List<AlterQuantityBasketEntry>) {
        if (entriesToRemove.isEmpty()){
            this.isProcessingRetailerEvent = false;
            return;
        }

        //update the entries and stop proccessing at end
        val basketAction = BasketAction.UpdateBasketEntriesDiff(entriesToRemove)
        val job = basketStore.dispatch(basketAction)
        job?.invokeOnCompletion {
            this.isProcessingRetailerEvent = false
        }
    }

    /**
     * called from app
     */

    fun dispose() {
        basketListnerJob?.cancel()
        comparator = null
    }

    fun handlePayment() {
        //TODO handle analytic
        val total = paymentTotal()
        if (basketStore.basketIsEmpty()) { return }
        basketStore.dispatch(BasketAction.ConfirmBasket(price = total.toString()))
    }
}
