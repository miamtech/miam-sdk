package com.miam.kmmMiamCore.miam_core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
@SerialName("recipe-likes")
public data class RecipeLike internal constructor(
    override val id: String? = null,
    override val attributes: RecipeLikeAttributes? = null,
    override val relationships: RecipeLikeRelationships? = null
): Record(), BasketPreviewEntry {

    public constructor(
        id: String?,
        attributes: JsonElement?,
        json_relationships: JsonElement?,
        includedRecords: List<Record>
    ): this(
        id,
        if (attributes == null) attributes else jsonFormat.decodeFromJsonElement<RecipeLikeAttributes>(attributes),
        if (json_relationships == null) null else jsonFormat.decodeFromJsonElement<RecipeLikeRelationships>(
            Relationships.filterEmptyRelationships(json_relationships)
        )
    ) {
        relationships?.buildFromIncluded(includedRecords)
    }

    val isLiked: Boolean
        get() = exists && attributes?.isPast == false

    val exists: Boolean
        get() = !id.isNullOrBlank()

    public fun toggle(): RecipeLike {
        return copy(attributes = attributes!!.copy(isPast = !attributes.isPast))
    }

    public companion object {
        public fun createDefault(recipeId: String): RecipeLike {
            val attributes = RecipeLikeAttributes(isPast = true, recipeId = recipeId.toInt())
            val rel = RecipeLikeRelationships()
            return RecipeLike(null, attributes, rel)
        }
    }
}

@Serializable
public data class RecipeLikeAttributes constructor(
    @SerialName("is-past")
    val isPast: Boolean,
    @SerialName("recipe-id")
    val recipeId: Int
): Attributes()

@Serializable
public class RecipeLikeRelationships: Relationships() {
    override fun buildFromIncluded(includedRecords: List<Record>) {
    }
}
