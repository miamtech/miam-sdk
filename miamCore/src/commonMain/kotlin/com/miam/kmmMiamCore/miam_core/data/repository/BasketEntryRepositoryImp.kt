package com.miam.kmmMiamCore.miam_core.data.repository

import com.miam.kmmMiamCore.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmmMiamCore.miam_core.model.BasketEntry

class BasketEntryRepositoryImp(private val basketEntryDataSource: MiamAPIDatasource) :
    BasketEntryRepository {
    override suspend fun updateBasketEntry(basketEntry: BasketEntry): BasketEntry {
        val newBasketEntry = basketEntry.copy()
        // reset the value to default so this field will not be sent
        newBasketEntry.needPatch = false
        return basketEntryDataSource.updateBasketEntry(
            newBasketEntry,
            listOf("groceries-entry", "items")
        )
    }
}