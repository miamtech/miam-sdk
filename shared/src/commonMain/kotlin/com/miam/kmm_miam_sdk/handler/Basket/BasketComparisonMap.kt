package com.miam.kmm_miam_sdk.handler.Basket

import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry
import com.miam.kmm_miam_sdk.miam_core.model.RetailerProduct

class BasketComparisonMap(val basketHandler : BasketHandler,
                          retailerBasket:List<RetailerProduct>,
                          miamBasket :List<BasketEntry>
) {

    val _extIdToComparisonItem :  MutableMap<String, BasketComparisonItem> = mutableMapOf()



    init {
        addEntryInMapFromRetailer(retailerBasket);
        _setFromMiam(miamBasket);
    }

    /**
     * TODO check  new this._comparisonItemType was not use
     * */
    private fun _fetchComparisonItem(extId :String): BasketComparisonItem {
        var compItem =_extIdToComparisonItem[extId]
        if (compItem == null)  {
            compItem = BasketComparisonItem(basketHandler)
            _extIdToComparisonItem[extId] = compItem
        }
        return compItem
    }


    fun cleanNullProducts() {
       _extIdToComparisonItem.entries.forEach { entry ->
            if (entry.value.miamQuantity == 0 && entry.value.retailerQuantity == 0) {
               _extIdToComparisonItem.remove(entry.key)
            }
        };
    }


    /**
     *  Update comming from retailer
     */

    // TODO  type retailer basket
     fun updateMapFromRetailer(retailerBasket: List<RetailerProduct>){
        addEntryInMapFromRetailer(retailerBasket)
        deleteEntryInMapFromRetailer(retailerBasket)
    }

    fun addEntryInMapFromRetailer(retailerBasket: List<RetailerProduct>) {
        retailerBasket.forEach { retailerEntrie ->
            val compItem = _fetchComparisonItem(retailerEntrie.id.toString());
            compItem.addOrUpdateRetailerProduct(retailerEntrie);
        }
    }

    fun deleteEntryInMapFromRetailer(retailerBasket: List<RetailerProduct>) {
        _extIdToComparisonItem.entries.forEach { entry ->
            checkIfEntryIsToDeleteFromMap(entry.key, retailerBasket)
        }
    }

    fun checkIfEntryIsToDeleteFromMap(extId : String, retailerBasket: List<RetailerProduct>) {
        val compItem = _extIdToComparisonItem[extId];
        val itemInRetailerBasket = retailerBasket.find { retailerEntrie ->
            retailerEntrie.id === extId
        }
        if (itemInRetailerBasket == null && compItem != null) {
            compItem.clearRetailerProduct();
        }
    }

   fun resolveFromRetailer(): MutableList<RetailerProduct> {
        val toRemoveFromMiam = mutableListOf<RetailerProduct>();
        _extIdToComparisonItem.entries.forEach { entry ->
            val quantityDelta = entry.value.miamQuantity - entry.value.retailerQuantity
            if (quantityDelta > 0) {
                toRemoveFromMiam.addAll(getQuantityToRemove(entry.value, quantityDelta))
                entry.value.miamQuantity = entry.value.retailerQuantity;
            }
        }
       return toRemoveFromMiam
    }


    fun getQuantityToRemove(compItem : BasketComparisonItem, stillToRemove :Int): MutableList<RetailerProduct> {
        val toRemoveFromMiam = mutableListOf<RetailerProduct>()
        var _stillToRemove = stillToRemove
        while(_stillToRemove > 0) {

            val pairDeleteReturn = compItem.removeFirstMiamEntry(stillToRemove)
            val beId = pairDeleteReturn.first
            val quantityRemoved = pairDeleteReturn.second

            if (beId == -1 || quantityRemoved == 0){
                _stillToRemove = 0
            } else {
                _stillToRemove -= quantityRemoved;
                toRemoveFromMiam.add( RetailerProduct(beId.toString(),quantityRemoved));
            }
        }
        return toRemoveFromMiam
    }


    /**
     * Update comming from miam
     */

   fun  setTargetFromMiam(miamBasket: List<BasketEntry>){
        _updateTargetFromMiam(miamBasket)

        _addTargetFromMiam(miamBasket)
    }


    fun resolveFromMiam(miamBasket: List<BasketEntry>): MutableList<RetailerProduct> {
        val toPushToRetailer = mutableListOf<RetailerProduct>();
        _extIdToComparisonItem.entries.forEach { entry ->
            toPushToRetailer.addAll(createOrUpdate(entry.key, miamBasket))
        }
        return toPushToRetailer;
    }


    fun createOrUpdate(extId : String, miamBasket:  List<BasketEntry>): MutableList<RetailerProduct> {
        val compItem = _extIdToComparisonItem[extId];
        var itemsToPush = mutableListOf<RetailerProduct>();

        // TODO check
        if (compItem !=  null) {
            if(compItem.retailerQuantity == 0 && compItem.miamTargetQuantity != 0) {
                // it is a creation
                // if there is more than one basket id for this product, arbitrary take the first
                // it will result at the same cora creation anyway
                val basketEntry = miamBasket.find { be -> be.id == compItem.firstBasketEntryId }

                //TODO check !!  for basket entry
                itemsToPush = compItem.createRetailerProducts(
                    basketEntry!!,
                    compItem.miamTargetQuantity
                );
            } else {
                if (compItem != null) {
                    itemsToPush = compItem.updateRetailerProducts()
                };
            }
            compItem.miamQuantity = compItem.miamTargetQuantity;
        }

        return itemsToPush;
    }

   private fun _setFromMiam(miamBasket : List<BasketEntry>) {
        miamBasket.forEach { basketEntry ->
            // TODO : retirer si on a que des basketEntry actives ?
            val extId = basketEntry.selectedItem?.attributes?.extId ?: return
            val compItem = _fetchComparisonItem(extId.toString());
            // may already exist if two different ingredients matched same item
            compItem.miamQuantity += basketEntry.attributes.quantity ?: 0
            compItem.miamBasketEntryIds[basketEntry.id] = basketEntry.attributes.quantity ?: 0
        }
    }

    private fun _updateTargetFromMiam(miamBasket:  List<BasketEntry>) {

        _extIdToComparisonItem.entries.forEach { entry ->
            val compItem = entry.value
            // you can have différent ingredients (ie different be) matching the same product
            val miamBasketEntries = miamBasket.filter { basketEntry ->
                basketEntry.selectedItem != null &&  basketEntry.selectedItem?.attributes?.extId.toString() == entry.key
            }
            if (miamBasketEntries.isNotEmpty()) {
                val quantities = miamBasketEntries.map { be -> be.attributes.quantity}
                compItem.miamTargetQuantity = quantities.reduce { a, b  -> (a ?: 0) + (b ?: 0)} ?: 0
                val tempMap = mutableMapOf<Int,Int>()
                miamBasketEntries.forEach { be -> tempMap[be.id] = be.attributes.quantity ?: 0 }
                compItem.miamBasketEntryIds.clear()
                compItem.miamBasketEntryIds.putAll(tempMap)
            } else {
                compItem.miamTargetQuantity = 0;
                compItem.miamBasketEntryIds.clear();
            }
        }
    }

    private fun  _addTargetFromMiam(miamBasket:  List<BasketEntry>) {
        miamBasket.forEach { basketEntry ->
            // TODO : retirer si on a que des basketEntry actives ?
            val extId = basketEntry.selectedItem?.attributes?.extId ?: return
            if (_extIdToComparisonItem[extId.toString()] != null) return

            val compItem = _fetchComparisonItem(extId.toString());
            // here in fact the target quantity is useless as we we will need to create a product, we can use basketEntry.quantity
            compItem.miamTargetQuantity += basketEntry.attributes.quantity ?: 0
            compItem.miamBasketEntryIds[basketEntry.id] = basketEntry.attributes.quantity ?: 0
        }
    }
}