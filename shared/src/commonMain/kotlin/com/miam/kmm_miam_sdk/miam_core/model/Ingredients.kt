package com.miam.kmm_miam_sdk.miam_core.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
@SerialName(Ingredient.TYPE)
data class Ingredient private constructor(
    override val id: String,
    override val attributes: IngredientAttributes? = null,
    override  val relationships: GroceriesEntry2Relationships? = null
): Record() {

    constructor(id: String, attributes: JsonElement?, relationships: JsonElement?, includedRecords: List<Record>?) : this(
        id,
        if (attributes == null) attributes else jsonFormat.decodeFromJsonElement<IngredientAttributes>(attributes),
        null,
    )

    override fun toString(): String {
        return "GroceriesEntry2: $id - $attributes - $relationships"
    }

    companion object {
        const val TYPE: String = "ingredients"
    }
}

@Serializable
data class IngredientAttributes constructor(
    val name : String?,
    val quantity : String?,
    val unit : String?,
    val active : Boolean = true,

    @SerialName("forced-eans")
    val forcedEans: List<String>? = emptyList<String>(),
): Attributes()


@Serializer(forClass = IngredientListRelationship::class)
object IngredientListSerializer : KSerializer<IngredientListRelationship> {
    override fun serialize(encoder: Encoder, value: IngredientListRelationship) {
        // super method call to only keep types and id
        value.serialize(encoder)
    }
}

@Serializable(with = IngredientListSerializer::class)
class IngredientListRelationship(override var data: List<Ingredient>): RelationshipList() {
    fun buildFromIncluded(includedRecords: List<Record>) {
        data = data.map { ing ->
            val existingEntry = includedRecords.find { record -> record is Ingredient && record.id == ing.id }
            if (existingEntry != null) ing.copy(attributes = (existingEntry as Ingredient).attributes) else ing
        }
    }
}
