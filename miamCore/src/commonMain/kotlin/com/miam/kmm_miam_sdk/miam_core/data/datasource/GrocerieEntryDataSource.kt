package com.miam.kmm_miam_sdk.miam_core.data.datasource

import com.miam.kmm_miam_sdk.miam_core.model.GroceriesEntry

interface GrocerieEntryDataSource {

    suspend fun updateGroceriesEntry(ge: GroceriesEntry): GroceriesEntry
}