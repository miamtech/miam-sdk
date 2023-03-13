package com.miam.kmmMiamCore.miam_core.data.datasource

import com.miam.kmmMiamCore.miam_core.model.Basket
import com.miam.kmmMiamCore.miam_core.model.BasketRelationshipName

public interface BasketDataSource {
    public suspend fun getFromListAndPos(listId: String, posId: Int, included: Array<BasketRelationshipName> = arrayOf()): Basket?

    public suspend fun updateBasket(basket: Basket): Basket
}