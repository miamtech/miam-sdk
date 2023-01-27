package com.miam.kmmMiamCore.miam_core.data.datasource

import com.miam.kmmMiamCore.miam_core.model.Tag

public interface TagDataSource {
    public suspend fun autocompleteTag(searchStr: String): List<Tag>
    public suspend fun getTagById(id: String): Tag
    public suspend fun getTags(filters: Map<String, String>): List<Tag>
}