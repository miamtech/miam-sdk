package com.miam.kmm_miam_sdk.miam_core.data.repository

import com.miam.kmm_miam_sdk.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmm_miam_sdk.miam_core.model.GroceriesEntry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GroceriesEntryRepositoryImp(private val grocerieEntryDataSource: MiamAPIDatasource) : GroceriesEntryRepository {
    override suspend fun updateGrocerieEntry(ge: GroceriesEntry): Flow<GroceriesEntry> = flow {
        val newGl = grocerieEntryDataSource.updateGroceriesEntry(ge)
        emit(newGl)
    }
}