package com.miam.kmmMiamCore.miam_core.data.repository


import com.miam.kmmMiamCore.base.mvi.PointOfSaleStore
import com.miam.kmmMiamCore.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmmMiamCore.miam_core.model.Tag
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class TagsRepositoryImp(private val tagDataSource: MiamAPIDatasource): KoinComponent {


    private val pointOfSaleStore: PointOfSaleStore by inject()

    suspend fun getTagById(id: String): Tag {
        return tagDataSource.getTagById(id)
    }

    suspend fun autocomplete(searchStr: String): List<Tag> {
        return tagDataSource.autocompleteTag(searchStr)
    }

    suspend fun fetchDietTags(): List<Tag> {
        return fetchTagsForSupplier(mapOf(Pair(TAGTYPE, "diet")))
    }

    suspend fun fetchEquipmentTags(): List<Tag> {
        return fetchTagsForSupplier(mapOf(Pair(TAGTYPE, "equipment")))
    }

    private suspend fun fetchTagsForSupplier(filters: Map<String, String>): List<Tag> {
        pointOfSaleStore.supplierId?.toString()?.let {
            val mutableFilters = filters.toMutableMap()
            mutableFilters["for_supplier"] = it
            return tagDataSource.getTags(mutableFilters)
        }

        return tagDataSource.getTags(filters)
    }

    companion object {
        const val TAGTYPE = "tag_type"
    }
}