package com.miam.kmmMiamCore.miam_core.data.repository

import com.miam.kmmMiamCore.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmmMiamCore.miam_core.model.Basket

public class BasketRepositoryImp(private val basketDataSource: MiamAPIDatasource): BasketRepository {

    override suspend fun getFromListAndPos(listId: String, posId: Int): Basket? {
        return basketDataSource.getFromListAndPos(listId, posId, listOf("basket-entries", "basket-entries.groceries-entry", "basket-entries.items"))
    }

    override suspend fun updateBasket(basket: Basket): Basket {
        return basketDataSource.updateBasket(basket)
    }
}