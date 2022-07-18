package com.miam.kmmMiamCore.miam_core.data.datasource

import com.miam.kmmMiamCore.miam_core.model.GroceriesEntry

interface GrocerieEntryDataSource {

    suspend fun updateGroceriesEntry(ge: GroceriesEntry): GroceriesEntry
}