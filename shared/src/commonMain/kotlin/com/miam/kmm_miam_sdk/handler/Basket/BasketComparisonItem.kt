package com.miam.kmm_miam_sdk.handler.Basket

import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry
import com.miam.kmm_miam_sdk.miam_core.model.RetailerProduct

class BasketComparisonItem(val basketHandler : BasketHandler) {

   var miamQuantity :Int = 0;
   var miamTargetQuantity: Int = 0;
   val miamBasketEntryIds : MutableMap<Int, Int> = mutableMapOf()
   val _retailerProducts : MutableList<RetailerProduct> = mutableListOf()

    val retailerQuantity: Int
         get() {
             val quantities = _retailerProducts.map { retailerProduct -> retailerProduct.quantity }
             return quantities.reduce { acc,value : Int ->  acc + value }
         }

    val firstBasketEntryId : Int
        get() {
            return  miamBasketEntryIds.keys.first()
        }

   fun clearRetailerProduct() {
       _retailerProducts.clear()
   }

    fun removeFirstMiamEntry(qtyToRemove :Int): Pair<Int,Int> {
        if(miamBasketEntryIds.entries.isEmpty()) return Pair(-1, 0)
        val quantity = miamBasketEntryIds.values.first()
        if(quantity > qtyToRemove){
            miamBasketEntryIds.put(firstBasketEntryId, quantity - qtyToRemove)
            return Pair(firstBasketEntryId, qtyToRemove)
        }
        miamBasketEntryIds.remove(firstBasketEntryId)
        return Pair(firstBasketEntryId, quantity)
    }

    /**
     * Update comming from retailer
     */

    fun addOrUpdateRetailerProduct(retailerProduct: RetailerProduct) {
        if(_retailerProducts.isEmpty() || retailerQuantity == 0){
            _retailerProducts.add(retailerProduct)
            return
        }
        _retailerProducts[0] = _retailerProducts[0].copy(quantity = retailerProduct.quantity)
    }

    /**
     * Update comming from miam
     */

    fun createRetailerProducts(basketEntry: BasketEntry, targetQuantity : Int) : MutableList<RetailerProduct> {
        if(basketEntry.attributes.selectedItemId != null ){
            // TODO  it extId here to change once we have selected Item /!\
            _retailerProducts.add(RetailerProduct(basketEntry.attributes.selectedItemId.toString(), targetQuantity))
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