package com.miam.kmmMiamCore.miam_core.data.datasource

import com.miam.kmmMiamCore.miam_core.model.BasketEntry
import com.miam.kmmMiamCore.miam_core.model.BasketEntryRelationshipName


public interface BasketEntryDataSource {
    public suspend fun updateBasketEntry(
        basketEntry: BasketEntry,
        included: Array<BasketEntryRelationshipName> = arrayOf()
    ): BasketEntry
}