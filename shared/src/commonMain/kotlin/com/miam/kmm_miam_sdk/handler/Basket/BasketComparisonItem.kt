package com.miam.kmm_miam_sdk.handler.Basket

import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry
import com.miam.kmm_miam_sdk.miam_core.model.RetailerProduct

class BasketComparisonItem() {

   var miamQuantity :Int = 0;
   var miamTargetQuantity: Int = 0;
   val miamBasketEntryIds : MutableMap<String, Int> = mutableMapOf()
   val _retailerProducts : MutableList<RetailerProduct> = mutableListOf()

    override fun toString(): String {
        return "" + miamQuantity + " " + miamTargetQuantity + " " + miamBasketEntryIds + " " + _retailerProducts
    }

    val retailerQuantity: Int
         get() {
             val quantities = _retailerProducts.map { retailerProduct -> retailerProduct.quantity }
             return quantities.sum()
         }

    val firstBasketEntryId : String
        get() {
            return  miamBasketEntryIds.keys.first()
        }

   fun clearRetailerProduct() {
       _retailerProducts.clear()
   }

    fun removeFirstMiamEntry(qtyToRemove :Int): Pair<String, Int> {
        if(miamBasketEntryIds.entries.isEmpty()) {
            return Pair("", 0)
        }
        val quantity = miamBasketEntryIds.values.first()
        if(quantity > qtyToRemove){
            miamBasketEntryIds[firstBasketEntryId] = quantity - qtyToRemove
            return Pair(firstBasketEntryId, qtyToRemove)
        }
        val toReturn = Pair(firstBasketEntryId, quantity)
        miamBasketEntryIds.remove(firstBasketEntryId)
        return toReturn
    }

    /**
     * Update comming from retailer
     */

    fun addOrUpdateRetailerProduct(retailerProduct: RetailerProduct) {
        if(_retailerProducts.isEmpty() || retailerQuantity == 0){
            _retailerProducts.add(retailerProduct)
            return
        }
        _retailerProducts[0] = _retailerProducts[0].copy(quantity = retailerProduct.quantity, productIdentifier = retailerProduct.productIdentifier)
    }

    /**
     * Update comming from miam
     */

    fun createRetailerProducts(basketEntry: BasketEntry, targetQuantity : Int) : MutableList<RetailerProduct> {
        if(basketEntry.selectedItem != null && basketEntry.selectedItem!!.attributes?.extId != null){
            _retailerProducts.add(RetailerProduct(basketEntry.selectedItem!!.attributes!!.extId!!, targetQuantity, basketEntry.selectedItem!!.attributes!!.name))
        }
        return _retailerProducts
    }

    fun updateRetailerProducts() : MutableList<RetailerProduct>{
        val quantityDiff = miamTargetQuantity - miamQuantity
        if(quantityDiff == 0 || _retailerProducts.isEmpty()) return mutableListOf()
        var item = _retailerProducts[0]
        item = item.copy(quantity= item.quantity + quantityDiff)
        if(item.quantity <= 0) clearRetailerProduct()
        return mutableListOf(item)
    }

}