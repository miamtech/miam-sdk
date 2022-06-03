package com.miam.kmm_miam_sdk.handler.Basket

import com.miam.kmm_miam_sdk.base.mvi.AlterQuantityBasketEntry
import com.miam.kmm_miam_sdk.base.mvi.BasketAction
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry
import com.miam.kmm_miam_sdk.miam_core.model.RetailerProduct

class BasketComparator(existingRetailerBasket: List<RetailerProduct>, firstMiamBasket:List<BasketEntry>)
{
    private val comparisonMap: BasketComparisonMap = BasketComparisonMap(existingRetailerBasket, firstMiamBasket)

    fun updateReceivedFromMiam(basket: List<BasketEntry>): MutableList<RetailerProduct> {
        comparisonMap.setTargetFromMiam(basket)
        val toPushToRetailer = comparisonMap.resolveFromMiam(basket)
        comparisonMap.cleanNullProducts();
        return toPushToRetailer
    }

     fun updateReceivedFromRetailer(retailerBasket: List<RetailerProduct>): MutableList<AlterQuantityBasketEntry> {
         comparisonMap.updateMapFromRetailer(retailerBasket)
         val toRemoveFromMiam = comparisonMap.resolveFromRetailer()
         comparisonMap.cleanNullProducts()
         return toRemoveFromMiam
    }
}