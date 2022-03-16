package com.miam.kmm_miam_sdk.handler.Basket

import com.miam.kmm_miam_sdk.base.mvi.AlterQuantityBasketEntry
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry
import com.miam.kmm_miam_sdk.miam_core.model.RetailerProduct

class BasketComparisonMap(val basketHandler: BasketHandler,
                          retailerBasket: List<RetailerProduct>,
                          miamBasket: List<BasketEntry>
) {

    val _extIdToComparisonItem:  MutableMap<String, BasketComparisonItem> = mutableMapOf()

    init {
        addEntryInMapFromRetailer(retailerBasket);
        _setFromMiam(miamBasket);
    }

    /**
     * TODO check  new this._comparisonItemType was not use
     * */
    private fun _fetchComparisonItem(extId :String): BasketComparisonItem {
        // println("Miam _fetchComparisonItem")
        var compItem = _extIdToComparisonItem[extId]
        // println("Miam _fetchComparisonItem found " + compItem)
        if (compItem == null)  {
            compItem = BasketComparisonItem(basketHandler)
            _extIdToComparisonItem[extId] = compItem
        }
        return compItem
    }


    fun cleanNullProducts() {
        // println("Miam cleanNullProducts")
        val keysToRemove = mutableListOf<String>()
        _extIdToComparisonItem.entries.forEach { entry ->
            if (entry.value.miamQuantity == 0 && entry.value.retailerQuantity == 0) {
                // println("Miam cleanNullProducts removing " + entry.key)
                keysToRemove.add(entry.key)
            }
        }
        // println("Miam cleanNullProducts clean keys " + keysToRemove)
        keysToRemove.forEach { _extIdToComparisonItem.remove(it) }
    }


    /**
     *  Update comming from retailer
     */

    // TODO  type retailer basket
     fun updateMapFromRetailer(retailerBasket: List<RetailerProduct>){
        // println("Miam updateMapFromRetailer " + retailerBasket)
        addEntryInMapFromRetailer(retailerBasket)
        deleteEntryInMapFromRetailer(retailerBasket)
        // println("Miam updateMapFromRetailer ended " + this)
    }
    
    /**
     * foreach product of retailerBasket insert it in the map or update the quantity
     */
    fun addEntryInMapFromRetailer(retailerBasket: List<RetailerProduct>) {
        retailerBasket.forEach { retailerEntry ->
            // println("Miam addEntryInMapFromRetailer checking " + retailerEntry)
            val compItem = _fetchComparisonItem(retailerEntry.retailerId);
            // println("Miam addEntryInMapFromRetailer found " + compItem)
            compItem.addOrUpdateRetailerProduct(retailerEntry);
        }
    }

    /**
     * for each product in the map that is not in retailerBasket remove it
     */
    fun deleteEntryInMapFromRetailer(retailerBasket: List<RetailerProduct>) {
        // println("Miam deleteEntryInMapFromRetailer  " + retailerBasket)
        _extIdToComparisonItem.entries.forEach { entry ->
            // println("Miam deleteEntryInMapFromRetailer checking " + entry)
            checkIfEntryIsToDeleteFromMap(entry.key, retailerBasket)
        }
    }

    fun checkIfEntryIsToDeleteFromMap(extId : String, retailerBasket: List<RetailerProduct>) {
        val compItem = _extIdToComparisonItem[extId];
        // println("Miam checkIfEntryIsToDeleteFromMap found compItem " + compItem)
        val itemInRetailerBasket = retailerBasket.find { retailerEntry ->
            // println("Miam checkIfEntryIsToDeleteFromMap checking " + retailerEntry)
            retailerEntry.retailerId == extId
        }
        // println("Miam checkIfEntryIsToDeleteFromMap checking itemInRetailerBasket " + itemInRetailerBasket)
        if (itemInRetailerBasket == null && compItem != null) {
            compItem.clearRetailerProduct();
        }
    }

   fun resolveFromRetailer(): MutableList<AlterQuantityBasketEntry> {
    //    println("Miam resolveFromRetailer")
       val toRemoveFromMiam = mutableListOf<AlterQuantityBasketEntry>();
    //    println("Miam resolveFromRetailer comparaison : " + _extIdToComparisonItem)
       _extIdToComparisonItem.entries.forEach { entry ->
        //    println("Miam resolveFromRetailer checking " + entry.key + " " + entry.value.miamQuantity +" " + entry.value.retailerQuantity + " " + entry.value.miamBasketEntryIds+ " " + entry.value._retailerProducts)
           val quantityDelta = entry.value.miamQuantity - entry.value.retailerQuantity
        //    println("Miam resolveFromRetailer checking delta " + quantityDelta)
           if (quantityDelta > 0) {
               toRemoveFromMiam.addAll(getQuantityToRemove(entry.value, quantityDelta))
               entry.value.miamQuantity = entry.value.retailerQuantity;
           }
       }
    //    println("Miam resolveFromRetailer return " + toRemoveFromMiam)
       return toRemoveFromMiam
    }


    fun getQuantityToRemove(compItem : BasketComparisonItem, stillToRemove :Int): MutableList<AlterQuantityBasketEntry> {
        // println("Miam getQuantityToRemove ")
        val toRemoveFromMiam = mutableListOf<AlterQuantityBasketEntry>()
        var _stillToRemove = stillToRemove
        // println("Miam getQuantityToRemove stillToRemove " + _stillToRemove)
        while(_stillToRemove > 0) {
            // println("Miam getQuantityToRemove will try to remove")
            val pairDeleteReturn = compItem.removeFirstMiamEntry(stillToRemove)
            // println("Miam getQuantityToRemove pairDeleteReturn " + pairDeleteReturn)
            val beId = pairDeleteReturn.first
            val quantityRemoved = pairDeleteReturn.second

            if (beId == -1 || quantityRemoved == 0){
                _stillToRemove = 0
            } else {
                _stillToRemove -= quantityRemoved;
                // println("Miam getQuantityToRemove adding " + beId + " " + quantityRemoved)
                toRemoveFromMiam.add(AlterQuantityBasketEntry(beId, -quantityRemoved));
            }
        }
        // println("Miam getQuantityToRemove returning $toRemoveFromMiam")
        return toRemoveFromMiam
    }


    /**
     * Update comming from miam
     */

   fun  setTargetFromMiam(miamBasket: List<BasketEntry>){
        // println("Miam setTargetFromMiam")
        _updateTargetFromMiam(miamBasket)
        _addTargetFromMiam(miamBasket)
    }


    fun resolveFromMiam(miamBasket: List<BasketEntry>): MutableList<RetailerProduct> {
        val toPushToRetailer = mutableListOf<RetailerProduct>();
        // println("Miam resolveFromMiam")
        _extIdToComparisonItem.entries.forEach { entry ->
            // println("Miam resolveFromMiam try " + entry.key)
            toPushToRetailer.addAll(createOrUpdate(entry.key, miamBasket))
        }
        return toPushToRetailer;
    }


    fun createOrUpdate(extId : String, miamBasket:  List<BasketEntry>): MutableList<RetailerProduct> {
        val compItem = _extIdToComparisonItem[extId];
        var itemsToPush = mutableListOf<RetailerProduct>();

        // println("Miam createOrUpdate compItem " + compItem)
        // TODO check
        if (compItem !=  null) {
            if(compItem.retailerQuantity == 0 && compItem.miamTargetQuantity != 0) {
                // println("Miam createOrUpdate creation ")
                // it is a creation
                // if there is more than one basket id for this product, arbitrary take the first
                // it will result at the same cora creation anyway
                val basketEntry = miamBasket.find { be -> be.id == compItem.firstBasketEntryId }
                // println("Miam createOrUpdate creation basketEntry " + basketEntry)

                //TODO check !!  for basket entry
                itemsToPush = compItem.createRetailerProducts(
                    basketEntry!!,
                    compItem.miamTargetQuantity
                );
            } else {
                itemsToPush = compItem.updateRetailerProducts()
            }
            compItem.miamQuantity = compItem.miamTargetQuantity;
        }
        // println("Miam createOrUpdate return " + itemsToPush)
        return itemsToPush;
    }

   private fun _setFromMiam(miamBasket : List<BasketEntry>) {
    //    println("Miam set from miam " + miamBasket)
        miamBasket.forEach { basketEntry ->
            // println("Miam set from miam cheking " + basketEntry)
            // TODO : retirer si on a que des basketEntry actives ?
            val extId = basketEntry.selectedItem?.attributes?.extId ?: return@forEach
            val compItem = _fetchComparisonItem(extId)
            // may already exist if two different ingredients matched same item
            compItem.miamQuantity += basketEntry.attributes.quantity ?: 0
            compItem.miamBasketEntryIds[basketEntry.id] = basketEntry.attributes.quantity ?: 0
        }
    }

    private fun _updateTargetFromMiam(miamBasket:  List<BasketEntry>) {
        // println("Miam _updateTargetFromMiam")
        _extIdToComparisonItem.entries.forEach { entry ->
            // println("Miam _updateTargetFromMiam trying " + entry)
            val compItem = entry.value
            // you can have différent ingredients (ie different be) matching the same product
            val miamBasketEntries = miamBasket.filter { basketEntry ->
                basketEntry.selectedItem != null &&  basketEntry.selectedItem?.attributes?.extId.toString() == entry.key
            }
            // println("Miam _updateTargetFromMiam found existing " + miamBasketEntries)
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
            println("Miam _updateTargetFromMiam updated " + compItem)
        }
    }

    private fun  _addTargetFromMiam(miamBasket:  List<BasketEntry>) {
        // println("Miam _addTargetFromMiam")
        miamBasket.forEach { basketEntry ->
            // println("Miam _addTargetFromMiam trying " + basketEntry)
            // println("Miam _addTargetFromMiam items " + basketEntry._relationships?.items)
            // println("Miam _addTargetFromMiam ext_id " + basketEntry.selectedItem?.attributes?.extId)
            // TODO : retirer si on a que des basketEntry actives ?
            val extId = basketEntry.selectedItem?.attributes?.extId ?: return@forEach
            if (_extIdToComparisonItem[extId] != null) return@forEach

            // println("Miam _addTargetFromMiam will create")
            val compItem = _fetchComparisonItem(extId);
            // here in fact the target quantity is useless as we we will need to create a product, we can use basketEntry.quantity
            compItem.miamTargetQuantity += basketEntry.attributes.quantity ?: 0
            compItem.miamBasketEntryIds[basketEntry.id] = basketEntry.attributes.quantity ?: 0
            // println("Miam _addTargetFromMiam created " + compItem)
        }
    }
}