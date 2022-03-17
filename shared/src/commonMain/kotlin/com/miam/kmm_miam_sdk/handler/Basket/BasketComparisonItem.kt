package com.miam.kmm_miam_sdk.handler.Basket

import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry
import com.miam.kmm_miam_sdk.miam_core.model.RetailerProduct

class BasketComparisonItem(val basketHandler : BasketHandler) {

   var miamQuantity :Int = 0;
   var miamTargetQuantity: Int = 0;
   val miamBasketEntryIds : MutableMap<Int, Int> = mutableMapOf()
   val _retailerProducts : MutableList<RetailerProduct> = mutableListOf()

    override fun toString(): String {
        return "" + miamQuantity + " " + miamTargetQuantity + " " + miamBasketEntryIds + " " + _retailerProducts
    }

    val retailerQuantity: Int
         get() {
             val quantities = _retailerProducts.map { retailerProduct -> retailerProduct.quantity }
             return quantities.sum()
         }

    val firstBasketEntryId : Int
        get() {
            return  miamBasketEntryIds.keys.first()
        }

   fun clearRetailerProduct() {
       _retailerProducts.clear()
   }

    fun removeFirstMiamEntry(qtyToRemove :Int): Pair<Int,Int> {
        // println("removeFirstMiamEntry " + miamBasketEntryIds)
        if(miamBasketEntryIds.entries.isEmpty()) {
            // println("removeFirstMiamEntry nothing can be removed")
            return Pair(-1, 0)
        }
        val quantity = miamBasketEntryIds.values.first()
        // println("removeFirstMiamEntry has quantity $quantity")
        if(quantity > qtyToRemove){
            // println("removeFirstMiamEntry enough to remove")
            miamBasketEntryIds[firstBasketEntryId] = quantity - qtyToRemove
            return Pair(firstBasketEntryId, qtyToRemove)
        }
        // println("removeFirstMiamEntry removing first entry")
        val toReturn = Pair(firstBasketEntryId, quantity)
        miamBasketEntryIds.remove(firstBasketEntryId)
        // println("removeFirstMiamEntry remain $miamBasketEntryIds")
        // println("removeFirstMiamEntry will return $toReturn")
        return toReturn
    }

    /**
     * Update comming from retailer
     */

    fun addOrUpdateRetailerProduct(retailerProduct: RetailerProduct) {
        // println("Miam addOrUpdateRetailerProduct " + retailerProduct + " to " + this)
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
        // println("Miam creating retailer product with " + BasketEntry + " : " + targetQuantity)
        if(basketEntry.selectedItem != null ){
            // println("Miam creating retailer product 2 with " + basketEntry.attributes.selectedItemId.toString() + " : " + targetQuantity)
            _retailerProducts.add(RetailerProduct(basketEntry.selectedItem!!.attributes.extId, targetQuantity, basketEntry.selectedItem!!.attributes.name))
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