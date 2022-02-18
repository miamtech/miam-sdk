package com.miam.kmm_miam_sdk.miam_core.data.repository

import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntryRelationships
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BasketEntryRepositoryImp: BasketEntryRepository {


    override suspend fun getRelationships(basketEntry: BasketEntry): Flow<BasketEntry>  = flow  {
        basketEntry._relationships = BasketEntryRelationships()
        emit(basketEntry)
    }
}