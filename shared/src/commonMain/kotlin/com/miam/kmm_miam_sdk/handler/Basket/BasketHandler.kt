package com.miam.kmm_miam_sdk.handler.Basket

import com.miam.kmm_miam_sdk.base.mvi.*
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry
import com.miam.kmm_miam_sdk.miam_core.model.RetailerProduct
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.take
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BasketHandler () : KoinComponent, CoroutineScope by CoroutineScope(Dispatchers.Main)  {

    private val coroutineHandler = CoroutineExceptionHandler {
            _, exception -> println("Miam error in basket preview $exception")
    }

    private var _comparator: BasketComparator? = null
    val basketStore: BasketStore by inject()
    private val groceriesListStore: GroceriesListStore by inject()
    private var basketEntries: List<BasketEntry>? = null
    private var _miamActiveBasket: List<BasketEntry>? = null

    var paymentTotal: () -> Double = fun():Double{return 0.0}
    var getBasketProducts: () -> List<RetailerProduct> = fun() :List<RetailerProduct> { return emptyList()}
    var pushProductsToBasket: (products: List<RetailerProduct>) -> Unit = fun(products: List<Any>) {
        throw Error("pushProductsToBasket not implemented")
    }
    var mapEntryToProduct: (item: RetailerProduct) -> Unit = fun(item: RetailerProduct){
        throw Error("pushProductsToBasket not implemented")
    }
    var listenToRetailerBasket: (callback: (products: List<RetailerProduct>) -> Unit) -> Unit = fun(callback : (products: List<RetailerProduct>) -> Unit){println("Miam --> please init listenToRetailerBasket")}

    private var basketListnerJob: Job? = null

    init {
       handleBasketSync()
    }

    fun dispose() {
        basketListnerJob?.cancel()
        _comparator = null
    }

    /*
    Should update Miam basket accordingly, ie remove entries that are not in the basket anymore
    */
    fun retailerBasketChangeCallBack(retailerBasket: List<RetailerProduct>){
        // println("Miam : retailer basket changed"+ retailerBasket.toString())
        // println("current basket " + _miamActiveBasket)
        if(_miamActiveBasket == null) {
            // can't update Miam basket if there is nothing in there. Should not happen
            return
        }
        // println("Miam : miam basket exist"+ _miamActiveBasket.toString())
        if(_comparator == null ){
            // create comparator that make the first sync
            _comparator = BasketComparator( this, retailerBasket, _miamActiveBasket!!)
            // println("Miam : init comparator"+ _comparator.toString())
        } else {
            _comparator!!.updateReceivedFromRetailer(retailerBasket)
        }
    }

    fun handlePayment() {
        //TODO handle analytic
        val total = paymentTotal()
        if (basketStore.basketIsEmpty()) { return }
            launch(coroutineHandler) {
                basketStore.observeSideEffect().filter {
                        basketEffect -> basketEffect == BasketEffect.BasketConfirmed
                }.take(1).collect {
                    println("Miam : Reset GL")
                    groceriesListStore.dispatch(GroceriesListAction.ResetGroceriesList)
                }
            }
            basketStore.dispatch(BasketAction.ConfirmBasket(price = total.toString()))
    }

    private fun basketChange(miamActiveBasket: List<BasketEntry> ) {
        // println("Miam basketChange " + miamActiveBasket)
        // assign the entries so that the callback retailerBasketChangeCallBack can use them
        // TODO : can we send them throught the callback retailerBasketChangeCallBack ??
        _miamActiveBasket = miamActiveBasket
        // Comparison should be initialized when first Miam basket is received, based on existing Retailer basket
        if (_comparator == null){
            listenToRetailerBasket(::retailerBasketChangeCallBack)
            return
        }
        // println("Miam basketChange processing " + _comparator!!.isProcessingRetailerEvent)
        if (_comparator!!.isProcessingRetailerEvent) return

        // println("Miam basketChange comparator ok")
        // When comparison is already initialized, we just update it
        _comparator!!.updateReceivedFromMiam(miamActiveBasket)
    }

   private fun handleBasketSync() {
    //    println("Miam handleBasketSync")
       basketListnerJob = launch(coroutineHandler) {
           basketStore.observeSideEffect().collect{
               basketStore.observeState().value.basket?.relationships?.basketEntries?.data.let { entries ->
                   println("Miam sync emited " + entries)
                   // when user is not logged or not on valid pos, basket is not fetched and we can't get here
                   // when user is loged on valid pos, miam basket is fetched and initial value emitted
                   if (entries != null) {
                       val activeEntries = entries.filter { e -> e.attributes!!.groceriesEntryStatus == "active" }
                       basketChange(activeEntries)
                   }
               }
           }
       }
    }
}
