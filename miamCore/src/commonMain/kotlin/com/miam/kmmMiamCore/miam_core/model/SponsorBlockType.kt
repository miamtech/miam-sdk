package com.miam.kmmMiamCore.miam_core.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

public enum class SponsorBlockTypesList {
    TITLE, SMALL_TITLE, PICTURE, SMALL_PICTURE, IMAGE_WITH_TEXT, IMAGE_AND_TEXT, TEXT_AND_IMAGE, TEXT, SMALL_TEXT, VIDEO
}

@Serializable
@SerialName("sponsor-block-types")
public data class SponsorBlockType internal constructor(
    override val id: String,
    override val attributes: SponsorBlockTypeAttributes? = null,
    override val relationships: SponsorBlockTypeRelationships? = null
): Record() {
    public constructor(
        id: String,
        attributes: JsonElement?,
        json_relationships: JsonElement?,
        includedRecords: List<Record>
    ): this(
        id,
        if (attributes == null) attributes else jsonFormat.decodeFromJsonElement<SponsorBlockTypeAttributes>(
            attributes
        ),
        if (json_relationships == null) null else jsonFormat.decodeFromJsonElement<SponsorBlockTypeRelationships>(
            Relationships.filterEmptyRelationships(json_relationships)
        )
    ) {
        relationships?.buildFromIncluded(includedRecords)
    }
}

@Serializable
public data class SponsorBlockTypeAttributes(
    val name: String,
    @SerialName("icon-url")
    val iconUrl: String,
    val description: String,
    @SerialName("default-height")
    val defaultHeight: Int,
    @SerialName("default-width")
    val defaultWidth: Int,
    @SerialName("mandatory-fields")
    val mandatoryFields: List<String>
): Attributes()

@Serializable
public object SponsorBlockTypeRelationships: Relationships() {
    override fun buildFromIncluded(includedRecords: List<Record>) {
    }
}

/**
 * Used from others relations
 */

@Serializable(with = SponsorBlockTypeSerializer::class)
public class SponsorBlockTypeRelationship(override var data: SponsorBlockType): Relationship() {
    public fun buildFromIncluded(includedRecords: List<Record>) {
        data = buildedFromIncluded(includedRecords, RecipeType::class) as SponsorBlockType
    }
}

@Serializer(forClass = SponsorBlockTypeRelationship::class)
public object SponsorBlockTypeSerializer: KSerializer<SponsorBlockTypeRelationship> {
    override fun serialize(encoder: Encoder, value: SponsorBlockTypeRelationship) {
        // super method call to only keep types and id
        value.serialize(encoder)
    }
}
