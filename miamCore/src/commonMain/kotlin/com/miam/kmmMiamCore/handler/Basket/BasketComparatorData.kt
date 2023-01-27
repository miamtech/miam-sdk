package com.miam.kmmMiamCore.handler.Basket

import com.miam.kmmMiamCore.miam_core.model.BasketEntry
import com.miam.kmmMiamCore.miam_core.model.RetailerProduct

public data class BasketComparatorData(val extIdToComparisonItem: MutableMap<String, BasketComparisonItem> = mutableMapOf()) {

    public fun init(retailerBasket: List<RetailerProduct>, miamActiveBasket: List<BasketEntry>) {
        addOrUpdateEntryFromRetailer(retailerBasket)
        addOrUpdateEntryFromMiam(miamActiveBasket)
    }

    /**
     *  Update coming from retailer
     */
    public fun updateMapFromRetailer(retailerBasket: List<RetailerProduct>) {
        addOrUpdateEntryFromRetailer(retailerBasket)
        deleteEntryInMapFromRetailer(retailerBasket)
    }

    /**
     * foreach product of retailerBasket insert it in the map or update the quantity
     */
    private fun addOrUpdateEntryFromRetailer(retailerBasket: List<RetailerProduct>) {
        retailerBasket.forEach { retailerProduct ->
            val compItem =
                extIdToComparisonItem[retailerProduct.retailerId] ?: BasketComparisonItem(
                    retailerProduct.retailerId
                )
            extIdToComparisonItem[retailerProduct.retailerId] =
                compItem.copy(retailerProduct = retailerProduct)
        }
    }

    /**
     * for each product in the map that is not in retailerBasket remove it
     */
    private fun deleteEntryInMapFromRetailer(retailerBasket: List<RetailerProduct>) {
        extIdToComparisonItem.keys.toList().forEach { extId ->
            val compItem = extIdToComparisonItem[extId]
            val itemInRetailerBasket =
                retailerBasket.find { retailerEntry -> retailerEntry.retailerId == extId }
            if (itemInRetailerBasket == null && compItem != null) {
                extIdToComparisonItem.remove(extId)
            }
        }
    }

    /**
     * Update comming from miam
     */

    public fun updateMapFromMiam(miamActiveBasket: List<BasketEntry>) {
        addOrUpdateEntryFromMiam(miamActiveBasket)
        deleteEntryInMapFromMiam(miamActiveBasket)
    }

    private fun addOrUpdateEntryFromMiam(miamActiveBasket: List<BasketEntry>) {
        miamActiveBasket.forEach { basketEntry ->
            val extId = basketEntry.selectedItem?.attributes?.extId ?: return@forEach
            val compItem = extIdToComparisonItem[extId] ?: BasketComparisonItem(extId)
            extIdToComparisonItem[extId] = compItem.addMiamEntry(basketEntry)
        }
    }

    private fun deleteEntryInMapFromMiam(miamActiveBasket: List<BasketEntry>) {
        extIdToComparisonItem.keys.toList().forEach { extId ->
            val compItem = extIdToComparisonItem[extId]!!
            // you can have différent ingredients (ie different be) matching the same product
            val miamBasketEntryIds = miamActiveBasket.filter { basketEntry ->
                basketEntry.selectedItem != null &&
                        basketEntry.selectedItem?.attributes?.extId.toString() == extId &&
                        (basketEntry.attributes!!.quantity ?: 0) > 0
            }.associate { basketEntry ->
                basketEntry.id to basketEntry.attributes!!.quantity!!
            }
            extIdToComparisonItem[extId] = compItem.copy(miamBasketEntryIds = miamBasketEntryIds)
        }
    }
}