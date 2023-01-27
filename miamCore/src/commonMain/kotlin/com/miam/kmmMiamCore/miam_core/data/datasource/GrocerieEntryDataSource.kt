package com.miam.kmmMiamCore.miam_core.data.datasource

import com.miam.kmmMiamCore.miam_core.model.GroceriesEntry

public interface GrocerieEntryDataSource {
    public suspend fun updateGroceriesEntry(ge: GroceriesEntry): GroceriesEntry
}