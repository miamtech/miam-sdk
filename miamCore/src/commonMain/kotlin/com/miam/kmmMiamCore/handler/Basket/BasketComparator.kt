package com.miam.kmmMiamCore.handler.Basket

import com.miam.kmmMiamCore.base.mvi.AlterQuantityBasketEntry
import com.miam.kmmMiamCore.miam_core.model.BasketEntry
import com.miam.kmmMiamCore.miam_core.model.RetailerProduct

data class BasketComparator(val extIdToComparisonItem: Map<String, BasketComparisonItem> = mapOf()) {
    fun init(
        retailerBasket: List<RetailerProduct>,
        miamActiveBasket: List<BasketEntry>
    ): BasketComparator {
        val comparatorData = BasketComparatorData()
        comparatorData.init(retailerBasket, miamActiveBasket)
        return this.copy(extIdToComparisonItem = comparatorData.extIdToComparisonItem)
    }

    override fun toString(): String {
        return extIdToComparisonItem.toString()
    }

    /**
     * updateReceivedFromMiam
     */

    fun updateReceivedFromMiam(miamActiveBasket: List<BasketEntry>): BasketComparator {
        val newComparatorData = BasketComparatorData(extIdToComparisonItem.toMutableMap())
        newComparatorData.updateMapFromMiam(miamActiveBasket)
        return this.copy(extIdToComparisonItem = newComparatorData.extIdToComparisonItem)
    }

    /**
     * compare Miam baskets to get products that were added, changed or removed
     */
    fun resolveFromMiam(newBasketComparator: BasketComparator): MutableList<RetailerProduct> {
        val toPushToRetailer = mutableListOf<RetailerProduct>()
        toPushToRetailer.addAll(addedOrUpdatedFromMiam(newBasketComparator))
        toPushToRetailer.addAll(removedFromMiam(newBasketComparator))
        return toPushToRetailer
    }

    private fun addedOrUpdatedFromMiam(newBasketComparator: BasketComparator): List<RetailerProduct> {
        return newBasketComparator.extIdToComparisonItem.entries.mapNotNull { entry ->
            val newCompItem = entry.value
            val previousCompItem = extIdToComparisonItem[entry.key]
            newCompItem.retailerProductAddedOrUpdatedFromMiam(previousCompItem)
        }
    }

    private fun removedFromMiam(newBasketComparator: BasketComparator): List<RetailerProduct> {
        return extIdToComparisonItem.entries.mapNotNull { entry ->
            val previousCompItem = entry.value
            val newCompItem = newBasketComparator.extIdToComparisonItem[entry.key]
            previousCompItem.retailerProductsRemovedFromMiam(newCompItem)
        }
    }

    /**
     * updateReceivedFromRetailer
     */

    fun updateReceivedFromRetailer(retailerBasket: List<RetailerProduct>): BasketComparator {
        val newComparatorData = BasketComparatorData(extIdToComparisonItem.toMutableMap())
        newComparatorData.updateMapFromRetailer(retailerBasket.filter { retailerProduct -> retailerProduct.quantity > 0 })
        return this.copy(extIdToComparisonItem = newComparatorData.extIdToComparisonItem)
    }

    /**
     *
     */
    fun resolveFromRetailer(newComparator: BasketComparator): List<AlterQuantityBasketEntry> {
        return extIdToComparisonItem.entries.flatMap { entry ->
            val previousCompItem = entry.value
            val newCompItem = newComparator.extIdToComparisonItem[entry.key]
            if (newCompItem == null) {
                // create a fake comp item just to reuse existing function miamProductRemoved
                val fakeCompItem = previousCompItem.copy(
                    retailerProduct = RetailerProduct(
                        previousCompItem.retailerId,
                        0
                    )
                )
                fakeCompItem.miamProductRemoved()
            } else {
                newCompItem.miamProductRemoved()
            }
        }
    }
}