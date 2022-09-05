package com.miam.kmmMiamCore.miam_core.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
@SerialName("sponsors")
data class Sponsor private constructor(
    override val id: String,
    override val attributes: SponsorAttributes? = null,
    override val relationships: SponsorRelationships? = null
) : Record() {
    constructor(
        id: String,
        attributes: JsonElement?,
        json_relationships: JsonElement?,
        includedRecords: List<Record>
    ) : this(
        id,
        if (attributes == null) attributes else jsonFormat.decodeFromJsonElement<SponsorAttributes>(
            attributes
        ),
        if (json_relationships == null) null else jsonFormat.decodeFromJsonElement<SponsorRelationships>(
            Relationships.filterEmptyRelationships(json_relationships)
        )
    ) {
        relationships?.buildFromIncluded(includedRecords)
    }
}

@Serializable
data class SponsorAttributes(
    val name: String,
    @SerialName("logo-url")
    val logoUrl: String
) : Attributes()

@Serializable
class SponsorRelationships : Relationships() {
    override fun buildFromIncluded(includedRecords: List<Record>) {
    }
}

/**
 * Used from others relations
 */

@Serializable(with = SponsorListSerializer::class)
class SponsorListRelationship(override var data: List<Sponsor>) : RelationshipList() {
    fun buildFromIncluded(includedRecords: List<Record>) {
        data = buildedFromIncluded(includedRecords, Sponsor::class) as List<Sponsor>
    }
}

@Serializer(forClass = SponsorListRelationship::class)
object SponsorListSerializer : KSerializer<SponsorListRelationship> {
    override fun serialize(encoder: Encoder, value: SponsorListRelationship) {
        // super method call to only keep types and id
        value.serialize(encoder)
    }
}