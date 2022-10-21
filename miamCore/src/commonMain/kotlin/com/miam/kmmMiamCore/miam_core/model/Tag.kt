package com.miam.kmmMiamCore.miam_core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
@SerialName("tags")
data class Tag private constructor(
    override val id: String? = null,
    override val attributes: TagAttributes? = null,
    override val relationships: TagRelationships? = null
) : Record() {

    constructor(
        id: String?,
        attributes: JsonElement?,
        json_relationships: JsonElement?,
        includedRecords: List<Record>
    ) : this(
        id,
        if (attributes == null) attributes else jsonFormat.decodeFromJsonElement<TagAttributes>(
            attributes
        ),
        if (json_relationships == null) null else jsonFormat.decodeFromJsonElement<TagRelationships>(
            Relationships.filterEmptyRelationships(json_relationships)
        )
    ) {
        relationships?.buildFromIncluded(includedRecords)
    }

    fun toCheckableTag(without: Boolean): CheckableTag {
        return CheckableTag(
            tag = this,
            isChecked = false,
            without = without
        )
    }

    companion object {
        fun createDefault(recipeId: String): Tag {
            val attributes = TagAttributes("-1", "example", "", "")
            val rel = TagRelationships()
            return Tag(null, attributes, rel)
        }
    }
}

@Serializable
data class TagAttributes constructor(
    @SerialName("tag-type-id")
    val tagTypeId: String,
    val name: String,
    @SerialName("icon-url")
    val iconUrl: String,
    @SerialName("picture-url")
    val pictureUrl: String,
) : Attributes()

@Serializable
class TagRelationships : Relationships() {
    override fun buildFromIncluded(includedRecords: List<Record>) {
    }
}

data class CheckableTag(
    val tag: Tag,
    val isChecked: Boolean,
    val without: Boolean
)
