package com.miam.kmm_miam_sdk.miam_core.data.datasource

import com.miam.kmm_miam_sdk.miam_core.model.GroceriesEntry
import com.miam.kmm_miam_sdk.miam_core.model.Item


interface BasketEntryDataSource {
    suspend fun getBasketEntryItems(basketEntryId: Int): List<Item>
    suspend fun getBasketEntryGrocerieEntry(basketEntryId: Int): GroceriesEntry
}