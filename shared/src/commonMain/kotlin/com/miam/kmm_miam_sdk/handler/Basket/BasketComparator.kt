package com.miam.kmm_miam_sdk.handler.Basket

import com.miam.kmm_miam_sdk.base.mvi.AlterQuantityBasketEntry
import com.miam.kmm_miam_sdk.base.mvi.BasketAction
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry
import com.miam.kmm_miam_sdk.miam_core.model.RetailerProduct

//TODO add missing field in constructor
class   BasketComparator(
    private val basketHandler: BasketHandler,
    existingRetailerBasket: List<RetailerProduct> ,
    firstMiamBasket:List<BasketEntry>)
{

    val _comparisonMap: BasketComparisonMap
    var isProcessingRetailerEvent : Boolean = false

    init {
        // println("Miam start processing retailer event")
        this.isProcessingRetailerEvent = true
        this._comparisonMap = BasketComparisonMap(basketHandler, existingRetailerBasket, firstMiamBasket);
        val toRemoveFromMiam = _comparisonMap.resolveFromRetailer();
        this.sendUpdateToMiam(toRemoveFromMiam);
    }

    fun updateReceivedFromMiam(basket: List<BasketEntry>) {
        // println("Miam updateReceivedFromMiam " + basket)
        // println("Miam updateReceivedFromMiam _comparisonMap before " + _comparisonMap._extIdToComparisonItem)
        _comparisonMap.setTargetFromMiam(basket)
        // println("Miam updateReceivedFromMiam _comparisonMap " + _comparisonMap._extIdToComparisonItem)
        val toPushToRetailer = _comparisonMap.resolveFromMiam(basket);
        // println("Miam updateReceivedFromMiam toPushToRetailer " + toPushToRetailer)
        _comparisonMap.cleanNullProducts();
        // println("Miam updateReceivedFromMiam _comparisonMap after " + _comparisonMap._extIdToComparisonItem)
        sendUpdateToRetailer(toPushToRetailer)
    }

     fun updateReceivedFromRetailer(retailerBasket: List<RetailerProduct>) {
        //  println("Miam updateReceivedFromRetailer " + retailerBasket)
        //  println("Miam start processing retailer event comparaison before " + _comparisonMap._extIdToComparisonItem)
         isProcessingRetailerEvent = true
         _comparisonMap.updateMapFromRetailer(retailerBasket)
         //  println("Miam start processing retailer event2 comparaison2 " + _comparisonMap._extIdToComparisonItem)
         val toRemoveFromMiam = _comparisonMap.resolveFromRetailer()
         //  println("Miam start processing retailer event2 to remove " + toRemoveFromMiam)
         _comparisonMap.cleanNullProducts()
        //  println("Miam start processing retailer event comparaison after " + _comparisonMap._extIdToComparisonItem)
         sendUpdateToMiam(toRemoveFromMiam)
    }

    fun  sendUpdateToMiam(entriesToRemove : List<AlterQuantityBasketEntry>) {
        // println("Miam sendUpdateToMiam " + entriesToRemove)
        if (entriesToRemove.isEmpty()){
            // println("Miam stop processing retailer event")
            this.isProcessingRetailerEvent = false;
            return;
        }

        // println("Miam sendUpdateToMiam action to make")
        //update the entries and stop proccessing at end with a callback
        val basketAction = BasketAction.UpdateBasketEntriesDiff(
            entriesToRemove,
            fun () {
                // println("Miam my callback")
                this.isProcessingRetailerEvent = false;
            }
        )
        basketHandler.basketStore.dispatch(basketAction)
    }

    fun sendUpdateToRetailer(itemsToAdd: List<RetailerProduct>) {
        // println("Miam sendUpdateToRetailer " + itemsToAdd.size + " " + itemsToAdd.isEmpty())
        if (itemsToAdd.isEmpty()) {
            // println("Miam sendUpdateToRetailer is empty and will return")
            return
        }
        // println("Miam sendUpdateToRetailer not empty1")
        // println("Miam sendUpdateToRetailer not empty2 " + itemsToAdd)
        basketHandler.pushProductsToBasket(itemsToAdd)
    }


}