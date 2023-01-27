package com.miam.kmmMiamCore.miam_core.data.repository

import com.miam.kmmMiamCore.miam_core.model.GroceriesEntry

public interface GroceriesEntryRepository {

    public suspend fun updateGrocerieEntry(ge: GroceriesEntry): GroceriesEntry
}