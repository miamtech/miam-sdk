package com.miam.kmm_miam_sdk.miam_core.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
@SerialName("recipe-steps")
data class RecipeStep private constructor(
    override val id: String,
    override val attributes: RecipeStepAttributes? = null,
    override  val relationships: RecipeStepRelationships? = null
): Record() {
    constructor(id: String, attributes: JsonElement?, json_relationships: JsonElement?, includedRecords: List<Record>) : this(
        id,
        if (attributes == null) attributes else jsonFormat.decodeFromJsonElement<RecipeStepAttributes>(attributes),
        if (json_relationships == null) null else jsonFormat.decodeFromJsonElement<RecipeStepRelationships>(Relationships.filterEmptyRelationships(json_relationships))
    ) {
        relationships?.buildFromIncluded(includedRecords)
    }
}

@Serializable
data class RecipeStepAttributes(
    @SerialName("step-number")
    val stepNumber: Int,
    val title : String?,
    @SerialName("description")
    val stepDescription : String?,
    @SerialName("media-url")
    val mediaUrl: String?
): Attributes()

@Serializable
class RecipeStepRelationships: Relationships() {
    override fun buildFromIncluded(includedRecords: List<Record>) {
    }
}

/**
 * Used from others relations
 */

@Serializable(with = RecipeStepListListSerializer::class)
class RecipeStepListRelationship(override var data: List<RecipeStep>): RelationshipList() {
    fun buildFromIncluded(includedRecords: List<Record>) {
        data = buildedFromIncluded(includedRecords, RecipeStep::class) as List<RecipeStep>
    }
}

@Serializer(forClass = RecipeStepListRelationship::class)
object RecipeStepListListSerializer : KSerializer<RecipeStepListRelationship> {
    override fun serialize(encoder: Encoder, value: RecipeStepListRelationship) {
        // super method call to only keep types and id
        value.serialize(encoder)
    }
}