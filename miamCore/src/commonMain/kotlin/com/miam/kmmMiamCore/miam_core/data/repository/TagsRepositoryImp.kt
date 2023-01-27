package com.miam.kmmMiamCore.miam_core.data.repository


import com.miam.kmmMiamCore.base.mvi.PointOfSaleStore
import com.miam.kmmMiamCore.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmmMiamCore.miam_core.model.Tag
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


public class TagsRepositoryImp(private val tagDataSource: MiamAPIDatasource): KoinComponent {


    private val pointOfSaleStore: PointOfSaleStore by inject()

    public suspend fun getTagById(id: String): Tag {
        return tagDataSource.getTagById(id)
    }

    public suspend fun autocomplete(searchStr: String): List<Tag> {
        return tagDataSource.autocompleteTag(searchStr)
    }

    public suspend fun fetchDietTags(): List<Tag> {
        return fetchTagsForSupplier(mapOf(Pair(TAGTYPE, "diet")))
    }

    public suspend fun fetchEquipmentTags(): List<Tag> {
        return fetchTagsForSupplier(mapOf(Pair(TAGTYPE, "equipment")))
    }

    private suspend fun fetchTagsForSupplier(filters: Map<String, String>): List<Tag> {
        pointOfSaleStore.supplierId?.toString()?.let {
            return tagDataSource.getTags(filters + mapOf(Pair("for_supplier", it)))
        }
        return tagDataSource.getTags(filters)
    }

    public companion object {
        public const val TAGTYPE: String = "tag_type"
    }
}