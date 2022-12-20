package com.miam.kmmMiamCore.miam_core.data.datasource

import com.miam.kmmMiamCore.miam_core.model.Tag

interface TagDataSource {
    suspend fun autocompleteTag(searchStr: String): List<Tag>
    suspend fun getTagsByTagType(tagType: String): List<Tag>
    suspend fun getTagById(id: String): Tag
    suspend fun getTags(filters: Map<String, String>): List<Tag>
}