package com.miam.kmm_miam_sdk.miam_core.data.repository

import com.miam.kmm_miam_sdk.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmm_miam_sdk.miam_core.model.Basket
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntryRelationships
import com.miam.kmm_miam_sdk.miam_core.model.BasketRelationships

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.math.ceil

class BasketRepositoryImp ( private val basketDataSource: MiamAPIDatasource) : BasketRepository,
    KoinComponent {

    private val entriesPerPages = 30;
    private val basketEntryRepo : BasketEntryRepositoryImp by inject()

    override suspend fun getFromListAndPos(listId: Int, posId: Int): Flow<Basket> = flow  {
       val basket =  basketDataSource.getFromListAndPos(listId, posId)

        if(basket.attributes.completion != null || (basket.attributes.completion?.total ?: 0 ) > 1 ) {
            basket._relationships = BasketRelationships(fetchBasketEntriesPage(basket))
            // println("Miam basket relationships " + basket._relationships?.basketEntries)
        }
        emit(basket)
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
        basketEntry._relationships = BasketEntryRelationships(
            basketDataSource.getBasketEntryItems(basketEntry.id),
            if(basketEntry.relationships.groceriesEntry.data.id != null) {
                basketDataSource.getBasketEntryGrocerieEntry(basketEntry.relationships.groceriesEntry.data.id)
            } else {
                null
            }
        )
        // println("Miam fillBasketEntryRelationships " + basketEntry._relationships?.items)
        // println("Miam fillBasketEntryRelationships " + basketEntry._relationships?.groceriesEntry)
        return  basketEntry
    }
}