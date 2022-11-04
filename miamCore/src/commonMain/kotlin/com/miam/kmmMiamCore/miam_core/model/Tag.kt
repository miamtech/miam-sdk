package com.miam.kmmMiamCore.miam_core.model

import com.miam.kmmMiamCore.handler.LogHandler
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
@SerialName("tags")
data class Tag private constructor(
    override val id: String,
    override val attributes: TagAttributes? = null,
    override val relationships: TagRelationships? = null
): Record() {
    constructor(
        id: String,
        attributes: JsonElement?,
        json_relationships: JsonElement?,
        includedRecords: List<Record>
    ): this(
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

    fun toCheckableTag(selectedTagIds: List<String>?, defaultCheck: Boolean = false): CheckableTag {
        LogHandler.info("toCheckableTag for ${this.id} and $selectedTagIds will be ${selectedTagIds?.contains(this.id)}")
        return CheckableTag(tag = this, isChecked = selectedTagIds?.contains(this.id) ?: defaultCheck)
    }

    companion object {
        fun createDefault(recipeId: String): Tag {
            val attributes = TagAttributes("-1", "example", "", "")
            val rel = TagRelationships()
            return Tag("-1", attributes, rel)
        }
    }
}

@Serializable
data class TagAttributes constructor(
    @SerialName("tag-type-id")
    val tagTypeId: String,
    val name: String,
    @SerialName("icon-url")
    val iconUrl: String?,
    @SerialName("picture-url")
    val pictureUrl: String?,
): Attributes()

@Serializable
class TagRelationships: Relationships() {
    override fun buildFromIncluded(includedRecords: List<Record>) {
    }
}

data class CheckableTag(
    val tag: Tag,
    val isChecked: Boolean
) {
    fun toggleIfNeeded(tagIdToToggle: String): CheckableTag {
        if (tag.id != tagIdToToggle) return this

        return this.copy(isChecked = !isChecked)
    }

    fun resetWith(selectedTagIds: List<String>?, defaultCheck: Boolean = false): CheckableTag {
        val shouldBeChecked = selectedTagIds?.contains(this.tag.id) ?: defaultCheck
        if (this.isChecked == shouldBeChecked) return this

        return this.copy(isChecked = shouldBeChecked)
    }
}
