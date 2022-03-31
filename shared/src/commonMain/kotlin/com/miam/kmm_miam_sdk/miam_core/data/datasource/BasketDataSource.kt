package com.miam.kmm_miam_sdk.miam_core.data.datasource

import com.miam.kmm_miam_sdk.miam_core.model.*

interface BasketDataSource {
   suspend fun getFromListAndPos(listId: String, posId: Int): Basket
   suspend fun getBasketEntries(basketId : Int): BasketEntries
   suspend fun getBasketEntriesbyPages(basketId : Int, pageIndex :Int, pageSize: Int):  List<BasketEntry>
   suspend fun updateBasket(basket: Basket, origin :String) :Basket
}