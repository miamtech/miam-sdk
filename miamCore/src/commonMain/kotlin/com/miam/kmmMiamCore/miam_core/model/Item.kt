package com.miam.kmmMiamCore.miam_core.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
@SerialName("items")
public data class Item internal constructor(
    override val id: String,
    override val attributes: ItemAttributes? = null,
    override val relationships: ItemRelationships? = null
) : Record() {
    public constructor(
        id: String,
        attributes: JsonElement?,
        json_relationships: JsonElement?,
        includedRecords: List<Record>
    ) : this(
        id,
        if (attributes == null) attributes else jsonFormat.decodeFromJsonElement<ItemAttributes>(
            attributes
        ),
        if (json_relationships == null) null else jsonFormat.decodeFromJsonElement<ItemRelationships>(
            Relationships.filterEmptyRelationships(json_relationships)
        )
    ) {
        relationships?.buildFromIncluded(includedRecords)
    }
}

@Serializable
public data class ItemAttributes(
    @SerialName("ext-id")
    val extId: String? = null,
    val name: String,
    val status: String? = null, // TODO Romain: enum class ?
    // description is a key word in swift
    @SerialName("description")
    val itemDescription: String? = null,
    val brand: String? = null,
    @SerialName("product-page")
    val productPage: String? = null,
    val image: String? = null,
    @SerialName("unit-price")
    val unitPrice: String, // TODO Romain: handle Unit with a type?
    @SerialName("capacity-unit")
    val capacityUnit: String? = null, // TODO Romain: handle Unit with a type?
    @SerialName("capacity-volume")
    val capacityVolume: String? = null,
    @SerialName("capacity-factor")
    val capacityFactor: Int? = 1,
    @SerialName("created-at")
    val createdAt: String, // TODO Romain: use Instant
    @SerialName("updated-at")
    val updatedAt: String, // TODO Romain: use Instant, nullable if not updated?
    val promoted: Boolean = false,
    @SerialName("variable-capacity")
    val variableCapacity: Boolean
) : Attributes()

@Serializable
public class ItemRelationships : Relationships() {
    override fun buildFromIncluded(includedRecords: List<Record>) {
    }
}

/**
 * Used from others relations
 */
@Serializable(with = ItemRelationshipListSerializer::class)
public class ItemRelationshipList(override var data: List<Item>) : RelationshipList() {
    public fun buildFromIncluded(includedRecords: List<Record>) {
        data = buildedFromIncluded(includedRecords, Item::class)
            .filterIsInstance<Item>()
    }
}

@Serializer(forClass = ItemRelationshipList::class)
public object ItemRelationshipListSerializer : KSerializer<ItemRelationshipList> {
    override fun serialize(encoder: Encoder, value: ItemRelationshipList) {
        // super method call to only keep types and id
        value.serialize(encoder)
    }
}