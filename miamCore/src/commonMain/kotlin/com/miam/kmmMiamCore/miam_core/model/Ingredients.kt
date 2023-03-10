package com.miam.kmmMiamCore.miam_core.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
@SerialName("ingredients")
public data class Ingredient internal constructor(
    override val id: String,
    override val attributes: IngredientAttributes? = null,
    override val relationships: IngredientRelationships? = null
) : Record() {

    public constructor(
        id: String,
        attributes: JsonElement?,
        json_relationships: JsonElement?,
        includedRecords: List<Record>
    ) : this(
        id,
        if (attributes == null) attributes else jsonFormat.decodeFromJsonElement<IngredientAttributes>(
            attributes
        ),
        if (json_relationships == null) null else jsonFormat.decodeFromJsonElement<IngredientRelationships>(
            Relationships.filterEmptyRelationships(json_relationships)
        )
    ) {
        relationships?.buildFromIncluded(includedRecords)
    }
}

@Serializable
public data class IngredientAttributes(
    val name: String?,
    val quantity: String?, // TODO Romain: can a quantity be nullable ? String ?
    val unit: String?, // TODO Romain: handle Unit with a type
    val active: Boolean = true,
    @SerialName("picture-url")
    val pictureUrl: String?,

    @SerialName("forced-eans")
    val forcedEans: List<String>? = emptyList<String>(),
) : Attributes()

@Serializable
public class IngredientRelationships : Relationships() {
    override fun buildFromIncluded(includedRecords: List<Record>) {
    }
}

/**
 * Used from others relations
 */
@Serializable(with = IngredientListSerializer::class)
public class IngredientListRelationship(override var data: List<Ingredient>) : RelationshipList() {
    public fun buildFromIncluded(includedRecords: List<Record>) {
        data = buildedFromIncluded(includedRecords, Ingredient::class)
            .filterIsInstance<Ingredient>()
    }
}

@Serializer(forClass = IngredientListRelationship::class)
public object IngredientListSerializer : KSerializer<IngredientListRelationship> {
    override fun serialize(encoder: Encoder, value: IngredientListRelationship) {
        // super method call to only keep types and id
        value.serialize(encoder)
    }
}
