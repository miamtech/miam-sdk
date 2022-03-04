package com.miam.kmm_miam_sdk.miam_core.data.repository

import com.miam.kmm_miam_sdk.miam_core.model.GroceriesEntry
import kotlinx.coroutines.flow.Flow

interface GroceriesEntryRepository {

    suspend fun updateGrocerieEntry(ge : GroceriesEntry) : Flow<GroceriesEntry>
}