package com.miam.kmmMiamCore.miam_core.data.repository

import com.miam.kmmMiamCore.miam_core.model.GroceriesEntry

interface GroceriesEntryRepository {

    suspend fun updateGrocerieEntry(ge: GroceriesEntry): GroceriesEntry
}