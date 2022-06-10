package com.miam.kmm_miam_sdk.miam_core.data.repository

import com.miam.kmm_miam_sdk.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntry
import com.miam.kmm_miam_sdk.miam_core.model.BasketEntryRelationships
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BasketEntryRepositoryImp(private val basketEntryDataSource: MiamAPIDatasource): BasketEntryRepository {
    override suspend fun updateBasketEntry(basketEntry: BasketEntry): BasketEntry {
        val newBasketEntry = basketEntry.copy()
        // reset the value to default so this field will not be sent
        newBasketEntry.needPatch = false
        return basketEntryDataSource.updateBasketEntry(newBasketEntry, listOf("groceries-entry", "items"))
    }
}