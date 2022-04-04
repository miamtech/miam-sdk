package com.miam.kmm_miam_sdk.miam_core.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement


@Serializable
@SerialName("recipe-statuses")
data class RecipeStatus private constructor(
    override val id: String,
    override val attributes: RecipeStatusAttributes? = null,
    override  val relationships: RecipeStatusRelationships? = null
): Record() {
    constructor(id: String, attributes: JsonElement?, json_relationships: JsonElement?, includedRecords: List<Record>) : this(
        id,
        if (attributes == null) attributes else jsonFormat.decodeFromJsonElement<RecipeStatusAttributes>(attributes),
        if (json_relationships == null) null else jsonFormat.decodeFromJsonElement<RecipeStatusRelationships>(Relationships.filterEmptyRelationships(json_relationships))
    ) {
        relationships?.buildFromIncluded(includedRecords)
    }
}

@Serializable
data class RecipeStatusAttributes(
    val name: String,
    val label:String
): Attributes()

@Serializable
class RecipeStatusRelationships: Relationships() {
    override fun buildFromIncluded(includedRecords: List<Record>) {
    }
}

/**
 * Used from others relations
 */

@Serializable(with = RecipeStatusSerializer::class)
class RecipeStatusRelationship(override var data: RecipeStatus): Relationship() {
    fun buildFromIncluded(includedRecords: List<Record>) {
        data = buildedFromIncluded(includedRecords, RecipeStatus::class) as RecipeStatus
    }
}

@Serializer(forClass = RecipeStatusRelationship::class)
object RecipeStatusSerializer : KSerializer<RecipeStatusRelationship> {
    override fun serialize(encoder: Encoder, value: RecipeStatusRelationship) {
        // super method call to only keep types and id
        value.serialize(encoder)
    }
}