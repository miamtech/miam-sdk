package com.miam.kmm_miam_sdk.handler.Basket

import com.miam.kmm_miam_sdk.base.mvi.*
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry
import com.miam.kmm_miam_sdk.miam_core.model.RetailerProduct
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BasketHandler () : KoinComponent, CoroutineScope by CoroutineScope(Dispatchers.Main)  {

    private var _comparator: BasketComparator? = null
    private val basketStore: BasketStore by inject()
    private val groceriesListStore: GroceriesListStore by inject()
    private var basketEntries: List<BasketEntry>? = null

    var paymentTotal: () -> Double = fun():Double{return 0.0}
    var getBasketProducts: () -> List<RetailerProduct> = fun() :List<RetailerProduct> { return emptyList()}
    var pushProductsToBasket: (products: List<RetailerProduct>) -> Unit = fun(products: List<Any>) {}
    var mapEntryToProduct: (item: RetailerProduct) -> Unit = fun(item: RetailerProduct){}
    var listenToRetailerBasket: (callback : (products: List<RetailerProduct>) -> Unit) -> Unit = fun(callback : (products: List<RetailerProduct>) -> Unit){ println("Miam --> please init listenToRetailerBasket")}

    init {
       handleBasketSync()
    }

    fun retailerBasketChangeCallBack(retailerBasket: List<RetailerProduct>){
        println("Miam : retailer basket changed"+ retailerBasket.toString())
        if(basketEntries != null) {
            println("Miam : miam basket exist"+ basketEntries.toString())
            if(_comparator == null ){
                _comparator = BasketComparator( this, retailerBasket, basketEntries!!)
                println("Miam : init comparator"+ _comparator.toString())
                return
            }
            _comparator!!.updateReceivedFromRetailer(retailerBasket)
        }
    }

    fun handlePayment() {
        //TODO handle analytic
        val total = paymentTotal()
        if (basketStore.basketIsEmpty()) { return }
            launch {
                basketStore.observeSideEffect().filter {
                        basketEffect -> basketEffect == BasketEffect.BasketConfirmed
                }.take(1).collect {
                    println("Miam : Reset GL")
                    groceriesListStore.dispatch(GroceriesListAction.ResetGroceriesList)
                }
            }
            basketStore.dispatch(BasketAction.ConfirmBasket(price = total.toString()))
    }

    private fun basketChange(miamBasket: List<BasketEntry> ) {
        // Comparison should be initialized when first Miam basket is received, based on existing Retailer basket
        if (_comparator != null) {
            if (_comparator!!.isProcessingRetailerEvent) return
            // When comparison is already initialized, we just update it
            _comparator!!.updateReceivedFromMiam(miamBasket)
        } else {
            listenToRetailerBasket( ::retailerBasketChangeCallBack)
        }
    }

   private fun handleBasketSync() {
       launch {
           basketStore.observeSideEffect().collect{
               basketStore.observeState().value.basket?._relationships?.basketEntries?.let { entries ->
                   basketChange(
                       entries
                   )
               }
           }
       }
    }
}
