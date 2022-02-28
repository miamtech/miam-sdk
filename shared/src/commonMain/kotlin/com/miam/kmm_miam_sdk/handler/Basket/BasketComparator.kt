package com.miam.kmm_miam_sdk.handler.Basket

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
        this.isProcessingRetailerEvent = true
        this._comparisonMap = BasketComparisonMap(basketHandler, existingRetailerBasket, firstMiamBasket);
        val toRemoveFromMiam = _comparisonMap.resolveFromRetailer();
        this.sendUpdateToMiam(toRemoveFromMiam);
    }

    fun updateReceivedFromMiam(basket: List<BasketEntry>) {
        _comparisonMap.setTargetFromMiam(basket);
        val toPushToRetailer = _comparisonMap.resolveFromMiam(basket);
        _comparisonMap.cleanNullProducts();
        sendUpdateToRetailer(toPushToRetailer);
    }

     fun updateReceivedFromRetailer(retailerBasket: List<RetailerProduct>) {
        isProcessingRetailerEvent = true;
        _comparisonMap.updateMapFromRetailer(retailerBasket);
        val toRemoveFromMiam = _comparisonMap.resolveFromRetailer();
        _comparisonMap.cleanNullProducts();
        sendUpdateToMiam(toRemoveFromMiam);
    }

    fun  sendUpdateToMiam(entriesToRemove : List<RetailerProduct>) {
        if (entriesToRemove.isEmpty()){
            this.isProcessingRetailerEvent = false;
            return;
        }

        // TODO remove entry
       /* window.miam.basket.removeEntries(entriesToRemove).subscribe(() => {
            console.debug('stop processing retailer event');
            this.isProcessingRetailerEvent = false;
        });*/
    }

    fun sendUpdateToRetailer(itemsToAdd: List<RetailerProduct>) {

        if (itemsToAdd.isEmpty()) return
        basketHandler.pushProductsToBasket(itemsToAdd.map{ item ->
                    return basketHandler.mapEntryToProduct(item)
            }
        )
    }


}