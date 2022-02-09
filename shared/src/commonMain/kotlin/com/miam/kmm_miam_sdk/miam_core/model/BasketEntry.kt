package com.miam.kmm_miam_sdk.miam_core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BasketEntries(
    @SerialName("data")
    val basketEntries: List<BasketEntry>,
)

@Serializable
data class BasketEntry(
    val id: Int,
    val attributes: BasketEntryAttributes,
): BasketPreviewEntry {

    var _relationships : BasketEntryRelationships? = null
}

@Serializable
data class BasketEntryAttributes(
    @SerialName("selected-item-id")
    val selectedItemId: Int? = null,
    @SerialName("learning-factor")
    val learningFactor: Int? = 1,
    val quantity: Int? = 1,
    @SerialName("recipe-ids")
    val recipeIds: List<Int>? = emptyList(),
    @SerialName("groceries-entry-status")
    val groceriesEntryStatus: String? = "active",
    @SerialName("basket-entries-items")
    var  basketEntriesItems: List<BasketEntriesItem>? = null,
)


@Serializable
 class BasketEntryRelationships (
        var items : List<Item> = emptyList(),
     var groceriesEntry: GroceriesEntry? = null
)

@Serializable
data class BasketEntriesItem(
    val id: Int? = null,
    @SerialName("item_id")
    val itemId: Int? = null,
    val score: Float? = null,
    @SerialName("learning_factor")
    val learningFactor: Int? = 1,
    @SerialName("times_selected")
    val timesSelected: Int? = 0,
    val selected: Boolean = false,
    val default: Boolean = false,
    @SerialName("unit_price")
    val unitPrice: Double? = null,
    val currency: String? = null,
    @SerialName("pft_plages")
    val pftPlages: List<Int>? = emptyList<Int>(),
    @SerialName("pft_checksum")
    val pftChecksum: String? = null,
)