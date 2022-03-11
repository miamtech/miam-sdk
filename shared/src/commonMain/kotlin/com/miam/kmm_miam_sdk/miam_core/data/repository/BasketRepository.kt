package com.miam.kmm_miam_sdk.miam_core.data.repository

import com.miam.kmm_miam_sdk.miam_core.model.Basket
import kotlinx.coroutines.flow.Flow

interface BasketRepository {
    suspend fun getFromListAndPos(listId :Int, posId :Int): Flow<Basket>
    suspend fun updateBasket(basket: Basket):Flow<Basket>
}