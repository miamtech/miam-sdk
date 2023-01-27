package com.miam.kmmMiamCore.handler.Basket

import com.miam.kmmMiamCore.base.mvi.AlterQuantityBasketEntry
import com.miam.kmmMiamCore.miam_core.model.BasketEntry
import com.miam.kmmMiamCore.miam_core.model.RetailerProduct
import kotlin.math.min

public data class BasketComparisonItem(
    val retailerId: String,
    val miamBasketEntryIds: Map<String, Int> = mapOf(), // need multiple if two different ingredients matched same item
    val retailerProduct: RetailerProduct = RetailerProduct(retailerId, 0)
) {
    override fun toString(): String {
        return "$miamBasketEntryIds $retailerProduct"
    }

    val retailerQuantity: Int
        get() {
            return retailerProduct.quantity
        }

    val miamQuantity: Int
        get() {
            return miamBasketEntryIds.values.sum()
        }

    val firstBasketEntryId: String
        get() {
            return miamBasketEntryIds.keys.first()
        }

    public fun addMiamEntry(basketEntry: BasketEntry): BasketComparisonItem {
        val newMiamBasketEntryIds = miamBasketEntryIds.toMutableMap()
        newMiamBasketEntryIds[basketEntry.id] = basketEntry.attributes!!.quantity ?: 0
        return this.copy(miamBasketEntryIds = newMiamBasketEntryIds)
    }

    public fun retailerProductAddedOrUpdatedFromMiam(previousCompItem: BasketComparisonItem?): RetailerProduct? {
        // product wasn't in miam basket nor retailer basket, just add it
        if (previousCompItem == null) {
            return RetailerProduct(retailerId, miamQuantity)
        }
        // then we would like to know the final retailer quantity
        // it can only be greater or equals than new miam quantity, never lower
        if (retailerQuantity == miamQuantity) {
            // update comes from miam we can either use retailerQuantity or previousCompItem.retailerQuantity
            // retailer quantity is already the target. Miam update probably comes after a retailer update
            return null
        }
        if (retailerQuantity < miamQuantity) {
            // don't even compute the diff, retailer quantity should adjust anyway
            return retailerProduct.copy(quantity = miamQuantity)
        }
        // here we have few possibilities, retailer quantity could have been greater than miam one
        // and still have both case of an increase or a decrease, check what change and adjuste retailer quantity
        val deltaQuantity = miamQuantity - previousCompItem.miamQuantity
        if (deltaQuantity == 0) {
            return null
        }
        return retailerProduct.copy(quantity = retailerProduct.quantity + deltaQuantity)
    }

    public fun retailerProductsRemovedFromMiam(newCompItem: BasketComparisonItem?): RetailerProduct? {
        if (newCompItem != null) {
            return null
        }
        return retailerProduct.copy(quantity = 0)
    }

    public fun miamProductRemoved(): List<AlterQuantityBasketEntry> {
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