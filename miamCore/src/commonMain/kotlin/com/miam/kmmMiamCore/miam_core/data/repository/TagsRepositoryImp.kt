package com.miam.kmmMiamCore.miam_core.data.repository


import com.miam.kmmMiamCore.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmmMiamCore.miam_core.model.Tag


class TagsRepositoryImp(private val tagDataSource: MiamAPIDatasource) {

    suspend fun autocomplete(searchStr: String): List<Tag> {
        return tagDataSource.autocompleteTag(searchStr)
    }

    suspend fun fetchDietTags(): List<Tag> {
        return tagDataSource.getTagsByTagType("diet")
    }

    suspend fun fetchEquipmentTags(): List<Tag> {
        return tagDataSource.getTagsByTagType("equipment")
    }

    suspend fun getTagById(id: String): Tag {
        return tagDataSource.getTagById(id)
    }

}