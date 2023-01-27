package com.miam.kmmMiamCore.miam_core.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
@SerialName("recipe-steps")
public data class RecipeStep(
    override val id: String,
    override val attributes: RecipeStepAttributes? = null,
    override val relationships: RecipeStepRelationships? = null
) : Record() {
    public constructor(
        id: String,
        attributes: JsonElement?,
        json_relationships: JsonElement?,
        includedRecords: List<Record>
    ) : this(
        id,
        if (attributes == null) attributes else jsonFormat.decodeFromJsonElement<RecipeStepAttributes>(
            attributes
        ),
        if (json_relationships == null) null else jsonFormat.decodeFromJsonElement<RecipeStepRelationships>(
            Relationships.filterEmptyRelationships(json_relationships)
        )
    ) {
        relationships?.buildFromIncluded(includedRecords)
    }
}

@Serializable
public data class RecipeStepAttributes(
    @SerialName("step-number")
    val stepNumber: Int,
    val title: String?,
    @SerialName("description")
    val stepDescription: String?,
    @SerialName("media-url")
    val mediaUrl: String?
) : Attributes()

@Serializable
public class RecipeStepRelationships : Relationships() {
    override fun buildFromIncluded(includedRecords: List<Record>) {
    }
}

/**
 * Used from others relations
 */
@Suppress("unchecked_cast")
@Serializable(with = RecipeStepListListSerializer::class)
public class RecipeStepListRelationship(override var data: List<RecipeStep>) : RelationshipList() {
    public fun buildFromIncluded(includedRecords: List<Record>) {
        data = buildedFromIncluded(includedRecords, RecipeStep::class) as List<RecipeStep>
    }
}

@Serializer(forClass = RecipeStepListRelationship::class)
public object RecipeStepListListSerializer : KSerializer<RecipeStepListRelationship> {
    override fun serialize(encoder: Encoder, value: RecipeStepListRelationship) {
        // super method call to only keep types and id
        value.serialize(encoder)
    }
}