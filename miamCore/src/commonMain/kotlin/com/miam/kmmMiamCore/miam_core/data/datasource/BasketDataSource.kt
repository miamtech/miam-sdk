package com.miam.kmmMiamCore.miam_core.data.datasource

import com.miam.kmmMiamCore.miam_core.model.Basket

public interface BasketDataSource {
    public suspend fun getFromListAndPos(listId: String, posId: Int, included: List<String> = listOf()): Basket?

    public suspend fun updateBasket(basket: Basket): Basket
}