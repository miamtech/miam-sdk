package com.miam.kmm_miam_sdk.miam_core.data.repository

import com.miam.kmm_miam_sdk.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmm_miam_sdk.miam_core.model.Basket
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntryRelationships
import com.miam.kmm_miam_sdk.miam_core.model.BasketRelationships

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.math.ceil

class BasketRepositoryImp ( private val basketDataSource: MiamAPIDatasource) : BasketRepository{

    private val entriesPerPages = 30;

    override suspend fun getFromListAndPos(listId: Int, posId: Int): Flow<Basket> = flow  {
       val basket =  basketDataSource.getFromListAndPos(listId, posId)

        if(basket.attributes.completion != null || (basket.attributes.completion?.total ?: 0 ) > 1 ) {
            basket._relationships = BasketRelationships( fetchBasketEntriesPage(basket) )
        }
        emit(basket)
    }

    private suspend fun fetchBasketEntriesPage(basket: Basket) : List<BasketEntry>{
        val pagesCount = ceil((basket.attributes.completion?.total ?: 0 ).div(entriesPerPages).toDouble()).toInt()
        var basketsEntries: MutableList<BasketEntry> = arrayListOf()
        for (i in 0..pagesCount){
            basketsEntries.addAll(
                basketDataSource.getBasketEntriesbyPages(basket.id ,i+1 , entriesPerPages).map {
                    fetchBasketEntryitem(it)
                })
        }

        return basketsEntries
    }

    private suspend fun fetchBasketEntryitem(basketEntry: BasketEntry): BasketEntry {

        // TODO uncomment When PR !675 of miam api is passed 
       /* basketEntry._relationships = BasketEntryRelationships(
            basketDataSource.getBasketEntriesItems(basketEntry.id),
            basketDataSource.getBasketEntriesGroceriesEntry(basketEntry.id)
        )*/
        return  basketEntry
    }
}