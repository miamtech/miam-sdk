package com.miam.kmmMiamCore.miam_core.data.datasource

import com.miam.kmmMiamCore.miam_core.model.BasketEntry


interface BasketEntryDataSource {
    suspend fun updateBasketEntry(
        basketEntry: BasketEntry,
        included: List<String> = listOf()
    ): BasketEntry
}