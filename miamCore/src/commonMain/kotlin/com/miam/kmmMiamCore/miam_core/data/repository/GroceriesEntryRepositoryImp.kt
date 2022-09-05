package com.miam.kmmMiamCore.miam_core.data.repository

import com.miam.kmmMiamCore.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmmMiamCore.miam_core.model.GroceriesEntry

class GroceriesEntryRepositoryImp(private val grocerieEntryDataSource: MiamAPIDatasource) :
    GroceriesEntryRepository {
    override suspend fun updateGrocerieEntry(ge: GroceriesEntry): GroceriesEntry {
        ge.needPatch = false
        return grocerieEntryDataSource.updateGroceriesEntry(ge)
    }
}