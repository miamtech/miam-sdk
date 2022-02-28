package com.miam.kmm_miam_sdk.miam_core.data.repository

import com.miam.kmm_miam_sdk.miam_core.model.Basket
import kotlinx.coroutines.flow.Flow

interface BasketRepository {
    suspend fun pointofsal(listId :Int, posId :Int): Flow<Basket>
}