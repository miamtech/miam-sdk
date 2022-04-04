package com.miam.kmm_miam_sdk.miam_core.data.repository

import com.miam.kmm_miam_sdk.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntryRelationships
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BasketEntryRepositoryImp(private val basketEntryDataSource: MiamAPIDatasource): BasketEntryRepository {
    override suspend fun updateBasketEntry(basketEntry: BasketEntry): BasketEntry {
        // println("Miam will update be")
        // println("Miam will update be rels ge" + basketEntry?._relationships?.groceriesEntry)
        // println("Miam will update be rels items" + basketEntry?._relationships?.items)
        basketEntry.needPatch = false
        val updatedBasketEntry = basketEntryDataSource.updateBasketEntry(basketEntry)
        // println("Miam will update be rels2 ge" + basketEntry?._relationships?.groceriesEntry)
        // println("Miam will update be rels items" + basketEntry?._relationships?.items)
        return updatedBasketEntry
    }
}