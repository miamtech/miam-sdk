package com.miam.kmmMiamCore.miam_core.data.repository

import com.miam.kmmMiamCore.miam_core.model.Basket
import kotlinx.coroutines.flow.Flow

interface BasketRepository {
    suspend fun getFromListAndPos(listId: String, posId :Int): Basket
    suspend fun updateBasket(basket: Basket): Basket
}