package com.miam.kmm_miam_sdk.miam_core.data.repository

import com.miam.kmm_miam_sdk.base.mvi.PointOfSaleStore
import com.miam.kmm_miam_sdk.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmm_miam_sdk.miam_core.model.Basket
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntryRelationships
import com.miam.kmm_miam_sdk.miam_core.model.BasketRelationships

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.math.ceil

class BasketRepositoryImp ( private val basketDataSource: MiamAPIDatasource) : BasketRepository , KoinComponent{

    private val entriesPerPages = 30;
    private val basketEntryRepo : BasketEntryRepositoryImp by inject()

    private val pointOfSaleStore: PointOfSaleStore by inject()

    override suspend fun getFromListAndPos(listId: Int, posId: Int): Basket  {
       val basket =  basketDataSource.getFromListAndPos(listId, posId)

        if(basket.attributes.completion != null || (basket.attributes.completion?.total ?: 0 ) > 1 ) {
            basket._relationships = BasketRelationships(fetchBasketEntriesPage(basket))
            // println("Miam basket relationships " + basket._relationships?.basketEntries)
        }
        return basket
    }

    override suspend fun updateBasket(basket: Basket): Basket {
        val origin  = pointOfSaleStore.getProviderOrigin()
        return basketDataSource.updateBasket(basket,origin)
    }

    private suspend fun fetchBasketEntriesPage(basket: Basket) : List<BasketEntry>{
        val pagesCount = ceil((basket.attributes.completion?.total ?: 0 ).div(entriesPerPages).toDouble()).toInt()
        var basketsEntries: MutableList<BasketEntry> = arrayListOf()
        for (i in 0..pagesCount){
            basketsEntries.addAll(
                basketDataSource.getBasketEntriesbyPages(basket.id ,i+1 , entriesPerPages).map {
                    // println("Miam fetchBasketEntriesPage " + it)
                    fillBasketEntryRelationships(it)
                })
        }
        // println("Miam fetchBasketEntriesPage return " + basketsEntries)
        return basketsEntries
    }

    private suspend fun fillBasketEntryRelationships(basketEntry: BasketEntry): BasketEntry {
        // println("Miam fillBasketEntryRelationships start " + basketEntry._relationships)

        val items = basketDataSource.getBasketEntryItems(basketEntry.id)
        val groceriesEntry = basketDataSource.getBasketEntryGrocerieEntry(basketEntry.relationships.groceriesEntry.data.id)
        if (items != null && groceriesEntry != null )
        basketEntry._relationships = BasketEntryRelationships(
            items,
            groceriesEntry
        )
        // println("Miam fillBasketEntryRelationships " + basketEntry._relationships?.items)
        // println("Miam fillBasketEntryRelationships " + basketEntry._relationships?.groceriesEntry)
        return  basketEntry
    }
}