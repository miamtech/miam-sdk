package com.miam.kmm_miam_sdk.miam_core.data.repository

import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry
import kotlinx.coroutines.flow.Flow

interface BasketEntryRepository {
    suspend fun getRelationshipsIfNecessary(basketEntry: BasketEntry): BasketEntry
    suspend fun getRelationships(basketEntry : BasketEntry): BasketEntry
    suspend fun updateBasketEntry(basketEntry: BasketEntry): BasketEntry
}