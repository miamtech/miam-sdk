package com.miam.kmmMiamCore.miam_core.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement


@Serializable
@SerialName("recipe-statuses")
public data class RecipeStatus internal constructor(
    override val id: String,
    override val attributes: RecipeStatusAttributes? = null,
    override val relationships: RecipeStatusRelationships? = null
) : Record() {
    public constructor(
        id: String,
        attributes: JsonElement?,
        json_relationships: JsonElement?,
        includedRecords: List<Record>
    ) : this(
        id,
        if (attributes == null) attributes else jsonFormat.decodeFromJsonElement<RecipeStatusAttributes>(
            attributes
        ),
        if (json_relationships == null) null else jsonFormat.decodeFromJsonElement<RecipeStatusRelationships>(
            Relationships.filterEmptyRelationships(json_relationships)
        )
    ) {
        relationships?.buildFromIncluded(includedRecords)
    }
}

@Serializable
public data class RecipeStatusAttributes(
    val name: String,
    val label: String
) : Attributes()

@Serializable
public class RecipeStatusRelationships : Relationships() {
    override fun buildFromIncluded(includedRecords: List<Record>) {
    }
}

/**
 * Used from others relations
 */

@Serializable(with = RecipeStatusSerializer::class)
public class RecipeStatusRelationship(override var data: RecipeStatus) : Relationship() {
    public fun buildFromIncluded(includedRecords: List<Record>) {
        data = buildedFromIncluded(includedRecords, RecipeStatus::class) as RecipeStatus
    }
}

@Serializer(forClass = RecipeStatusRelationship::class)
public object RecipeStatusSerializer : KSerializer<RecipeStatusRelationship> {
    override fun serialize(encoder: Encoder, value: RecipeStatusRelationship) {
        // super method call to only keep types and id
        value.serialize(encoder)
    }
}