package com.miam.kmm_miam_sdk.handler.Basket

import com.miam.kmm_miam_sdk.base.mvi.BasketStore
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry
import com.miam.kmm_miam_sdk.miam_core.model.RetailerProduct
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class BasketHandler () : KoinComponent, CoroutineScope by CoroutineScope(Dispatchers.Main)  {

    // TODO check if usefull
    private var firstMiamBasketReceived = true // we use a behaviourSubject and first emit value is not relevant
    private var _comparator: BasketComparator? = null
    private val basketStore: BasketStore by inject()
    private var basketEntries: List<BasketEntry>? = null

    var hasPayment : () -> Boolean = fun():Boolean{return false}
    var paymentTotal: () -> Double = fun():Double{return 0.0}
    var getBasketProducts: () -> List<RetailerProduct> = fun() :List<RetailerProduct> { return emptyList()}
    var pushProductsToBasket: (products: List<RetailerProduct>) -> Unit = fun(products: List<Any>) {}
    var mapEntryToProduct: (item: RetailerProduct) -> Unit = fun(item: RetailerProduct){}

    init {
        handlePayment(fun(){handleBasketSync()})
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

    fun   handlePayment(callback : () -> Unit) {
        if (hasPayment()) {
            val total = paymentTotal();

            //TODO STORE basket token detect payment
            val token = "token" //localStorage.getItem('_miam/basketToken');
            if (token != null) {
                // Miam basket that was confirmed on miam.tech => validate payment
                // TODO   localStorage.removeItem('_miam/basketToken');
                sendMiamOrder(total, token);
                callback();
            } else {
                // Local Miam basket (with entries OR NOT) => confirm it and validate payment only if total received from miam > 0
                confirmBasket(total, callback);
            }
        } else {
            callback();
        }
    }

    fun sendMiamOrder(total : Double, token: String) {
        // TODO send to analityc window.miam.basket.paid(total * 100); // need to pass the total in cents for analytics event
        // TODO  window.miam.supplier.notifyBasketUpdated(token, 'PAID', total);
    }

    fun  confirmBasket(total :Double, callback : () -> Unit) {
       /* window.miam.basket.confirm().subscribe(basket => {
            if (basket && basket.token && basket.totalPrice > 0) {
                const token = basket.token;
                console.log('[Miam] Send confirmation notification', token);
                window.miam.supplier.notifyBasketUpdated(token, 'CONFIRMED');
                this.sendMiamOrder(total, token);
            }
            callback();
        });*/
    }

    private fun basketChange(miamBasket: List<BasketEntry> ) {
        if (firstMiamBasketReceived && miamBasket.isEmpty()) {
            // Skip first empty array received
            firstMiamBasketReceived = true;
        } else {
        // Comparison should be initialized when first Miam basket is received, based on existing Retailer basket
        if (_comparator != null) {
            if (_comparator!!.isProcessingRetailerEvent) return
            // When comparison is already initialized, we just update it
            _comparator!!.updateReceivedFromMiam(miamBasket)
        }
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
