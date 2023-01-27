package com.miam.kmmMiamCore.miam_core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
@SerialName("tags")
public data class Tag internal constructor(
    override val id: String,
    override val attributes: TagAttributes? = null,
    override val relationships: TagRelationships? = null
): Record() {
    public constructor(
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
}

@Serializable
public data class TagAttributes constructor(
    @SerialName("tag-type-id")
    val tagTypeId: String, // TODO Romain: Use enum class?
    val name: String,
    @SerialName("icon-url")
    val iconUrl: String?,
    @SerialName("picture-url")
    val pictureUrl: String?,
): Attributes()

@Serializable
public class TagRelationships: Relationships() {
    override fun buildFromIncluded(includedRecords: List<Record>) {
    }
}

public enum class TagTypes {
    DIET, INGREDIENT, EQUIPMENT
}

public data class CheckableTag constructor(
    val tagType: TagTypes,
    val tag: Tag,
    val isChecked: Boolean
) {
    public constructor(tagType: TagTypes, tag: Tag): this(tagType, tag, checkedByDefault(tagType))

    public fun toggleIfNeeded(tagIdToToggle: String): CheckableTag {
        if (tag.id != tagIdToToggle) return this

        return this.copy(isChecked = !isChecked)
    }

    public fun resetWith(storageTagIds: List<String>): CheckableTag {
        val savedInStorage = savedInStorage(storageTagIds)
        if (this.isChecked == savedInStorage) return this

        return this.copy(isChecked = savedInStorage)
    }

    private val isCheckedByDefault: Boolean
        get() = checkedByDefault(tagType)


    // if we are not checked by default, we check the box -> it appears in list. Only one in list should be checked
    // if we are not checked by default the list is an exclusion list
    private fun savedInStorage(storageTagIds: List<String>): Boolean {
        val isInSelection = storageTagIds.contains(this.tag.id)
        return if (isCheckedByDefault) !isInSelection else isInSelection
    }

    val changedFromItsDefaultValue: Boolean
        get() = if (isCheckedByDefault) !isChecked else isChecked

    val isIncludedInQuery: Boolean
        get() = tagType == TagTypes.DIET

    public companion object {
        public fun checkedByDefault(tagType: TagTypes): Boolean {
            return tagType == TagTypes.EQUIPMENT
        }
    }
}
