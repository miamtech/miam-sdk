package com.miam.kmmMiamCore.miam_core.data.datasource

import com.miam.kmmMiamCore.miam_core.model.*

interface BasketDataSource {
   suspend fun getFromListAndPos(listId: String, posId: Int, included: List<String> = listOf()): Basket
   suspend fun updateBasket(basket: Basket) :Basket
}