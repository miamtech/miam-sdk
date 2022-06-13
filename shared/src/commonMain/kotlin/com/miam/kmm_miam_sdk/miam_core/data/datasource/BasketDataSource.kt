package com.miam.kmm_miam_sdk.miam_core.data.datasource

import com.miam.kmm_miam_sdk.miam_core.model.*

interface BasketDataSource {
   suspend fun getFromListAndPos(listId: String, posId: Int, included: List<String> = listOf()): Basket
   suspend fun updateBasket(basket: Basket) :Basket
}