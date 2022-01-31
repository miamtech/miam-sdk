package com.miam.kmm_miam_sdk.miam_core.data.repository

import com.miam.kmm_miam_sdk.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmm_miam_sdk.miam_core.model.Basket

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BasketRepositoryImp ( private val basketDataSource: MiamAPIDatasource) : BasketRepository{
    override suspend fun getFromListAndPos(listId: Int, posId: Int): Flow<Basket> = flow  {
       val basket =  basketDataSource.getFromListAndPos(listId, posId)
       val basketEntries = basketDataSource.getBasketEntries(basket.id)
       emit(basket.apply { basket.attributes.basketEntries = basketEntries })
    }
}