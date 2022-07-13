package com.miam.kmmMiamCore.miam_core.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
@SerialName("recipe-types")
data class RecipeType private constructor(
    override val id: String,
    override val attributes: RecipeTypeAttributes? = null,
    override  val relationships: RecipeTypeRelationships? = null
): Record() {
    constructor(id: String, attributes: JsonElement?, json_relationships: JsonElement?, includedRecords: List<Record>) : this(
        id,
        if (attributes == null) attributes else jsonFormat.decodeFromJsonElement<RecipeTypeAttributes>(attributes),
        if (json_relationships == null) null else jsonFormat.decodeFromJsonElement<RecipeTypeRelationships>(Relationships.filterEmptyRelationships(json_relationships))
    ) {
        relationships?.buildFromIncluded(includedRecords)
    }
}

@Serializable
data class RecipeTypeAttributes(
    val name : String,
): Attributes()

@Serializable
class RecipeTypeRelationships: Relationships() {
    override fun buildFromIncluded(includedRecords: List<Record>) {
    }
}

/**
 * Used from others relations
 */

@Serializable(with = RecipeTypeSerializer::class)
class RecipeTypeRelationship(override var data: RecipeType): Relationship() {
    fun buildFromIncluded(includedRecords: List<Record>) {
        data = buildedFromIncluded(includedRecords, RecipeType::class) as RecipeType
    }
}

@Serializer(forClass = RecipeTypeRelationship::class)
object RecipeTypeSerializer : KSerializer<RecipeTypeRelationship> {
    override fun serialize(encoder: Encoder, value: RecipeTypeRelationship) {
        // super method call to only keep types and id
        value.serialize(encoder)
    }
}