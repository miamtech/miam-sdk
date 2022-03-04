package com.miam.kmm_miam_sdk.miam_core.data.repository

import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry
import kotlinx.coroutines.flow.Flow

interface BasketEntryRepository {
    suspend fun getRelationships(basketEntry : BasketEntry): Flow<BasketEntry>
    suspend fun updateBasketEntry(basketEntry: BasketEntry):Flow<BasketEntry>
}