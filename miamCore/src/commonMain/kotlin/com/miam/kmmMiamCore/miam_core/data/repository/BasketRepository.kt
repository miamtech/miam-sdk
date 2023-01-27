package com.miam.kmmMiamCore.miam_core.data.repository

import com.miam.kmmMiamCore.miam_core.model.Basket

public interface BasketRepository {
    public suspend fun getFromListAndPos(listId: String, posId: Int): Basket?
    public suspend fun updateBasket(basket: Basket): Basket
}