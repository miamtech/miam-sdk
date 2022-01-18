package com.miam.kmm_miam_sdk.miam_core.data.datasource

import com.miam.kmm_miam_sdk.miam_core.model.Basket
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntries

interface BasketDataSource {
   suspend fun getFromListAndPos(listId: Int, posId: Int): Basket
   suspend fun getBasketEntries(basketId : Int): BasketEntries
}