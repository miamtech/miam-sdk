package com.miam.kmmMiamCore.miam_core.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement


@Serializable
@SerialName("sponsor-blocks")
public data class SponsorBlock internal constructor(
    override val id: String,
    override val attributes: SponsorBlockAttributes? = null,
    override val relationships: SponsorBlockRelationships? = null
): Record() {
    public constructor(
        id: String,
        attributes: JsonElement?,
        json_relationships: JsonElement?,
        includedRecords: List<Record>
    ): this(
        id,
        if (attributes == null) attributes else jsonFormat.decodeFromJsonElement<SponsorBlockAttributes>(
            attributes
        ),
        if (json_relationships == null) null else jsonFormat.decodeFromJsonElement<SponsorBlockRelationships>(
            Relationships.filterEmptyRelationships(json_relationships)
        )
    ) {
        relationships?.buildFromIncluded(includedRecords)
    }
}

@Serializable
public data class SponsorBlockAttributes(
    val position: Int,
    val height: Int,
    val width: Int,
    @SerialName("video-url")
    val videoUrl: String? = null,
    @SerialName("picture-url")
    val pictureUrl: String? = null,
    val content: String? = null,
    @SerialName("background-color")
    val backgroundColor: String? = null
): Attributes()

@Serializable
public class SponsorBlockRelationships(
    @SerialName("sponsor-block-type")
    public var sponsorBlockType: SponsorBlockTypeRelationship? = null,
): Relationships() {
    override fun buildFromIncluded(includedRecords: List<Record>) {
        sponsorBlockType?.buildFromIncluded(includedRecords)
    }
}

/**
 * Used from others relations
 */

@Serializable(with = SponsorBlocksSerializer::class)
public class SponsorBlocksRelationships(override var data: List<SponsorBlock>): RelationshipList() {
    public fun buildFromIncluded(includedRecords: List<Record>) {
        data = buildedFromIncluded(includedRecords, SponsorBlock::class).filterIsInstance<SponsorBlock>()
    }
}

@Serializer(forClass = SponsorBlocksRelationships::class)
public object SponsorBlocksSerializer: KSerializer<SponsorBlocksRelationships> {
    override fun serialize(encoder: Encoder, value: SponsorBlocksRelationships) {
        // super method call to only keep types and id
        value.serialize(encoder)
    }
}
