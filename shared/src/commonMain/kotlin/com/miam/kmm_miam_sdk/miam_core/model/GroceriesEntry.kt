package com.miam.kmm_miam_sdk.miam_core.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
@SerialName("groceries-entries")
data class GroceriesEntry private constructor(
    override val id: String,
    override val attributes: GroceriesEntryAttributes? = null,
    override  val relationships: GroceriesEntryRelationships? = null
): Record() {
    constructor(id: String, attributes: JsonElement?, json_relationships: JsonElement?, includedRecords: List<Record>) : this(
        id,
        if (attributes == null) attributes else jsonFormat.decodeFromJsonElement<GroceriesEntryAttributes>(attributes),
        if (json_relationships == null) null else jsonFormat.decodeFromJsonElement<GroceriesEntryRelationships>(Relationships.filterEmptyRelationships(json_relationships))
    ) {
        relationships?.buildFromIncluded(includedRecords)
    }

    var needPatch: Boolean = false

    fun updateStatus(status: String): GroceriesEntry {
        needPatch = true
        val copy = this.copy(
            attributes = this.attributes?.copy(status = status)
        )
        copy.needPatch = true
        return copy
    }
}

@Serializable
data class GroceriesEntryAttributes constructor(
    val name : String?,
    @SerialName("capacity-volume")
    val capacityVolume: String? = null,
    @SerialName("capacity-unit")
    val capacityUnit: String? = null,
    @SerialName("capacity-factor")
    val capacityFactor: Int? = null,
    val status: String? = null,
    @SerialName("recipe-ids")
    val recipeIds: List<Int>? = emptyList()
): Attributes() {
}

@Serializable
class GroceriesEntryRelationships: Relationships() {
    override fun buildFromIncluded(includedRecords: List<Record>) {
    }
}

/**
 * Used from others relations
 */

@Serializable(with = GroceriesEntryRelationshipListSerializer::class)
class GroceriesEntryRelationshipList(override var data: List<GroceriesEntry>): RelationshipList() {
    fun buildFromIncluded(includedRecords: List<Record>) {
        data = buildedFromIncluded(includedRecords, GroceriesEntry::class) as List<GroceriesEntry>
    }
}

@Serializer(forClass = GroceriesEntryRelationshipList::class)
object GroceriesEntryRelationshipListSerializer : KSerializer<GroceriesEntryRelationshipList> {
    override fun serialize(encoder: Encoder, value: GroceriesEntryRelationshipList) {
        // super method call to only keep types and id
        value.serialize(encoder)
    }
}

@Serializable(with = GroceriesEntryRelationshipSerializer::class)
class GroceriesEntryRelationship(override var data: GroceriesEntry): Relationship() {
    fun buildFromIncluded(includedRecords: List<Record>) {
        data = buildedFromIncluded(includedRecords, GroceriesEntry::class) as GroceriesEntry
    }
}

@Serializer(forClass = GroceriesEntryRelationship::class)
object GroceriesEntryRelationshipSerializer : KSerializer<GroceriesEntryRelationship> {
    override fun serialize(encoder: Encoder, value: GroceriesEntryRelationship) {
        // super method call to only keep types and id
        value.serialize(encoder)
    }
}
