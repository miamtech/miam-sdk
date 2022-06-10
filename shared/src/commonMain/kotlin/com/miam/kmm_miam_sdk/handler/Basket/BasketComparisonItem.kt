package com.miam.kmm_miam_sdk.handler.Basket

import com.miam.kmm_miam_sdk.base.mvi.AlterQuantityBasketEntry
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry
import com.miam.kmm_miam_sdk.miam_core.model.RetailerProduct
import kotlin.math.min

data class BasketComparisonItem(
    val retailerId: String,
    val miamBasketEntryIds: Map<String, Int> = mapOf(), // need multiple if two different ingredients matched same item
    val retailerProduct: RetailerProduct = RetailerProduct(retailerId, 0)
) {
    override fun toString(): String {
        return "$miamBasketEntryIds $retailerProduct"
    }

    val retailerQuantity: Int
         get() {
             return retailerProduct?.quantity?: 0
         }

    val miamQuantity: Int
        get() {
            return miamBasketEntryIds.values.sum()
        }

    val firstBasketEntryId: String
        get() {
            return  miamBasketEntryIds.keys.first()
        }

    fun addMiamEntry(basketEntry: BasketEntry): BasketComparisonItem {
        val newMiamBasketEntryIds = miamBasketEntryIds.toMutableMap()
        newMiamBasketEntryIds[basketEntry.id] = basketEntry.attributes!!.quantity ?: 0
        return this.copy(miamBasketEntryIds = newMiamBasketEntryIds)
    }

    fun retailerProductAddedOrUpdatedFromMiam(previousCompItem: BasketComparisonItem?): RetailerProduct? {
        if (previousCompItem == null) {
            return RetailerProduct(retailerId, miamQuantity)
        }
        val deltaQuantity = miamQuantity - previousCompItem.miamQuantity
        if (deltaQuantity == 0) {
            return null
        }
        return retailerProduct.copy(quantity = retailerProduct.quantity + deltaQuantity)
    }

    fun retailerProductsRemovedFromMiam(newCompItem: BasketComparisonItem?): RetailerProduct? {
        if (newCompItem != null) {
            return null
        }
        return retailerProduct.copy(quantity = 0)
    }

    fun miamProductRemoved(): List<AlterQuantityBasketEntry> {
        var quantityToRemove = miamQuantity - retailerQuantity
        if (quantityToRemove < 0) return listOf()

        return miamBasketEntryIds.mapNotNull { entry ->
            if (quantityToRemove <= 0) {
                null
            } else {
                val quantityRemoved = min(entry.value, quantityToRemove)
                quantityToRemove -= quantityRemoved
                AlterQuantityBasketEntry(entry.key, -quantityRemoved)
            }
        }
    }
}