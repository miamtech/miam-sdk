package com.miam.kmmMiamCore.miam_core.data.repository

import com.miam.kmmMiamCore.miam_core.model.BasketEntry

interface BasketEntryRepository {
    suspend fun updateBasketEntry(basketEntry: BasketEntry): BasketEntry
}