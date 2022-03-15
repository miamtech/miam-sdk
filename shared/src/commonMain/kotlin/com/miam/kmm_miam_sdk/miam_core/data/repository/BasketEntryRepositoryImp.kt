package com.miam.kmm_miam_sdk.miam_core.data.repository

import com.miam.kmm_miam_sdk.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntryRelationships
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BasketEntryRepositoryImp(private val basketEntryDataSource: MiamAPIDatasource): BasketEntryRepository {


    override suspend fun getRelationships(basketEntry: BasketEntry): Flow<BasketEntry> = flow  {
        basketEntry._relationships = BasketEntryRelationships(
            basketEntryDataSource.getBasketEntryItems(basketEntry.id),
            if(basketEntry.relationships.groceriesEntry.data.id != null) {
                basketEntryDataSource.getBasketEntryGrocerieEntry(basketEntry.relationships.groceriesEntry.data.id)
            } else {
              null
             }
            )
        emit(basketEntry)
    }

    override suspend fun updateBasketEntry(basketEntry: BasketEntry): Flow<BasketEntry> = flow {
        // println("Miam will update be")
        // println("Miam will update be rels ge" + basketEntry?._relationships?.groceriesEntry)
        // println("Miam will update be rels items" + basketEntry?._relationships?.items)
        val updatedBasketEntry = basketEntryDataSource.updateBasketEntry(basketEntry)
        updatedBasketEntry._relationships = basketEntry._relationships
        // println("Miam will update be rels2 ge" + basketEntry?._relationships?.groceriesEntry)
        // println("Miam will update be rels items" + basketEntry?._relationships?.items)
        emit(updatedBasketEntry)
    }
}