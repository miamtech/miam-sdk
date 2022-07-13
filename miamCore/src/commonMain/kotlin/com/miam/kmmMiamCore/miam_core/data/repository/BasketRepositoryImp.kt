package com.miam.kmmMiamCore.miam_core.data.repository

import com.miam.kmmMiamCore.base.mvi.PointOfSaleStore
import com.miam.kmmMiamCore.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmmMiamCore.miam_core.model.Basket
import com.miam.kmmMiamCore.miam_core.model.BasketEntry
import com.miam.kmmMiamCore.miam_core.model.BasketEntryRelationships
import com.miam.kmmMiamCore.miam_core.model.BasketRelationships

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.math.ceil

class BasketRepositoryImp ( private val basketDataSource: MiamAPIDatasource) : BasketRepository , KoinComponent{

    private val pointOfSaleStore: PointOfSaleStore by inject()

    override suspend fun getFromListAndPos(listId: String, posId: Int): Basket  {
        return basketDataSource.getFromListAndPos(listId, posId, listOf("basket-entries", "basket-entries.groceries-entry", "basket-entries.items"))
    }

    override suspend fun updateBasket(basket: Basket): Basket {
        return basketDataSource.updateBasket(basket)
    }
}