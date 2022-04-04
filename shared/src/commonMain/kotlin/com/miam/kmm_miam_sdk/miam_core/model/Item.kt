package com.miam.kmm_miam_sdk.miam_core.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
@SerialName("items")
data class Item private constructor(
    override val id: String,
    override val attributes: ItemAttributes? = null,
    override  val relationships: ItemRelationships? = null
): Record() {
    constructor(id: String, attributes: JsonElement?, json_relationships: JsonElement?, includedRecords: List<Record>) : this(
        id,
        if (attributes == null) attributes else jsonFormat.decodeFromJsonElement<ItemAttributes>(attributes),
        if (json_relationships == null) null else jsonFormat.decodeFromJsonElement<ItemRelationships>(Relationships.filterEmptyRelationships(json_relationships))
    ) {
        relationships?.buildFromIncluded(includedRecords)
    }
}

@Serializable
data class ItemAttributes (
    @SerialName("ext-id")
    val extId: String? = null,
    val name: String,
    val status: String? = null,
    val description: String? = null,
    val brand: String? = null,
    @SerialName("product-page")
    val productPage: String? = null,
    val image : String? = null,
    @SerialName("unit-price")
    val unitPrice: String,
    @SerialName("capacity-unit")
    val capacityUnit: String? = null,
    @SerialName("capacity-volume")
    val capacityVolume: String? = null,
    @SerialName("capacity-factor")
    val capacityFactor: Int? = 1,
    @SerialName("created-at")
    val createdAt: String,
    @SerialName("updated-at")
    val updatedAt: String,
    val promoted : Boolean = false,
    @SerialName("variable-capacity")
    val variableCapacity: Boolean
): Attributes()

@Serializable
class ItemRelationships: Relationships() {
    override fun buildFromIncluded(includedRecords: List<Record>) {
    }
}

/**
 * Used from others relations
 */

@Serializable(with = ItemRelationshipListSerializer::class)
class ItemRelationshipList(override var data: List<Item>): RelationshipList() {
    fun buildFromIncluded(includedRecords: List<Record>) {
        data = buildedFromIncluded(includedRecords, Item::class) as List<Item>
    }
}

@Serializer(forClass = ItemRelationshipList::class)
object ItemRelationshipListSerializer : KSerializer<ItemRelationshipList> {
    override fun serialize(encoder: Encoder, value: ItemRelationshipList) {
        // super method call to only keep types and id
        value.serialize(encoder)
    }
}