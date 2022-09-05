package com.miam.kmmMiamCore.miam_core.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
@SerialName("recipe-providers")
data class RecipeProvider private constructor(
    override val id: String,
    override val attributes: RecipeProviderAttributes? = null,
    override val relationships: RecipeProviderRelationships? = null
) : Record() {
    constructor(
        id: String,
        attributes: JsonElement?,
        json_relationships: JsonElement?,
        includedRecords: List<Record>
    ) : this(
        id,
        if (attributes == null) attributes else jsonFormat.decodeFromJsonElement<RecipeProviderAttributes>(
            attributes
        ),
        if (json_relationships == null) null else jsonFormat.decodeFromJsonElement<RecipeProviderRelationships>(
            Relationships.filterEmptyRelationships(json_relationships)
        )
    ) {
        relationships?.buildFromIncluded(includedRecords)
    }
}

@Serializable
data class RecipeProviderAttributes(
    val name: String,
) : Attributes()

@Serializable
class RecipeProviderRelationships : Relationships() {
    override fun buildFromIncluded(includedRecords: List<Record>) {
    }
}

/**
 * Used from others relations
 */

@Serializable(with = RecipeProviderSerializer::class)
class RecipeProviderRelationship(override var data: RecipeProvider) : Relationship() {
    fun buildFromIncluded(includedRecords: List<Record>) {
        data = buildedFromIncluded(includedRecords, RecipeProvider::class) as RecipeProvider
    }
}

@Serializer(forClass = RecipeProviderRelationship::class)
object RecipeProviderSerializer : KSerializer<RecipeProviderRelationship> {
    override fun serialize(encoder: Encoder, value: RecipeProviderRelationship) {
        // super method call to only keep types and id
        value.serialize(encoder)
    }
}