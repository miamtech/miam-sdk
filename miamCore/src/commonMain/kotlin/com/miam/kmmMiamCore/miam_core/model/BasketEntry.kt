package com.miam.kmmMiamCore.miam_core.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

public enum class BasketEntryRelationshipName(public val relationshipName: String) {
    GROCERIES_ENTRY("groceries-entry"),
    ITEM("items")
}

@Serializable
@SerialName("basket-entries")
public data class BasketEntry internal constructor(
    override val id: String,
    override val attributes: BasketEntryAttributes? = null,
    override val relationships: BasketEntryRelationships? = null
): Record(), BasketPreviewEntry {
    public constructor(
        id: String,
        attributes: JsonElement?,
        json_relationships: JsonElement?,
        includedRecords: List<Record>
    ): this(
        id,
        if (attributes == null) attributes else jsonFormat.decodeFromJsonElement<BasketEntryAttributes>(
            attributes
        ),
        if (json_relationships == null) null else jsonFormat.decodeFromJsonElement<BasketEntryRelationships>(
            Relationships.filterEmptyRelationships(json_relationships)
        )
    ) {
        relationships?.buildFromIncluded(includedRecords)
    }

    override fun toString(): String {
        return "$id - ${relationships?.groceriesEntry?.data?.attributes?.name} - ${attributes?.quantity} - ${attributes?.groceriesEntryStatus}"
    }

    // TODO Romain: val in constructor and use copy() function
    var needPatch: Boolean = false

    val selectedItem: Item?
        get() = relationships!!.items!!.data.find { item -> item.id == attributes!!.selectedItemId.toString() }

    val itemsCountOrZero: Int
        get() = relationships?.items?.data?.size ?: 0

    public fun updateQuantity(qty: Int): BasketEntry {
        if (qty <= 0) return updateStatus("deleted")

        var newRecord = this.copy(
            attributes = this.attributes!!.copy(
                quantity = qty,
            )
        )
        newRecord.needPatch = true
        if (this.attributes.groceriesEntryStatus != "active") {
            newRecord = newRecord.updateStatus("active")
        }
        return newRecord
    }

    public fun updateStatus(status: String): BasketEntry {
        var newRecord = this.copy(
            attributes = this.attributes!!.copy(
                groceriesEntryStatus = status
            )
        )
        newRecord.needPatch = true
        if (this.relationships?.groceriesEntry?.data != null) {
            newRecord = newRecord.copy(
                relationships = this.relationships.copy(
                    groceriesEntry = GroceriesEntryRelationship(
                        this.relationships.groceriesEntry!!.data.updateStatus(
                            status
                        )
                    )
                )
            )
        }
        return newRecord
    }

    public fun updateSelectedItem(selectedItemId: Int): BasketEntry {
        val newRecord = this.copy(
            attributes = this.attributes!!.copy(
                selectedItemId = selectedItemId
            )
        )
        newRecord.needPatch = true
        return newRecord
    }
}

@Serializable
public data class BasketEntryAttributes(
    @SerialName("selected-item-id")
    val selectedItemId: Int? = null,
    @SerialName("learning-factor")
    val learningFactor: Int?,
    val quantity: Int?, // TODO Romain: can a quantity be null??
    @SerialName("recipe-ids")
    val recipeIds: List<Int>?,
    @SerialName("groceries-entry-status")
    val groceriesEntryStatus: String? = "active", // TODO Romain: enum class??
    @SerialName("basket-entries-items")
    var basketEntriesItems: List<BasketEntriesItem>? = null,
): Attributes()

@Serializable
public data class BasketEntryRelationships constructor(
    var items: ItemRelationshipList? = null,
    @SerialName("groceries-entry") var groceriesEntry: GroceriesEntryRelationship? = null,
): Relationships() {
    override fun buildFromIncluded(includedRecords: List<Record>) {
        items?.buildFromIncluded(includedRecords)
        groceriesEntry?.buildFromIncluded(includedRecords)
    }
}

@Serializable
public data class BasketEntriesItem(
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
    @SerialName("unit-price")
    val unitPrice: Double? = null,
    val currency: String? = null,
    @SerialName("pft_plages")
    val pftPlages: List<Int>? = emptyList<Int>(),
    @SerialName("pft_checksum")
    val pftChecksum: String? = null,
)

/**
 * Used from others relations
 */

@Suppress("unchecked_cast")
@Serializable(with = BasketEntryRelationshipListSerializer::class)
public class BasketEntryRelationshipList(override var data: List<BasketEntry>): RelationshipList() {
    public fun buildFromIncluded(includedRecords: List<Record>) {
        data = buildedFromIncluded(includedRecords, BasketEntry::class)
            .filterIsInstance<BasketEntry>()
    }
}

@Serializer(forClass = BasketEntryRelationshipList::class)
public object BasketEntryRelationshipListSerializer: KSerializer<BasketEntryRelationshipList> {
    override fun serialize(encoder: Encoder, value: BasketEntryRelationshipList) {
        // super method call to only keep types and id
        value.serialize(encoder)
    }
}