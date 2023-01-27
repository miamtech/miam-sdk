package com.miam.kmmMiamCore.miam_core.data.repository

import com.miam.kmmMiamCore.miam_core.model.BasketEntry

public interface BasketEntryRepository {
    public suspend fun updateBasketEntry(basketEntry: BasketEntry): BasketEntry
}