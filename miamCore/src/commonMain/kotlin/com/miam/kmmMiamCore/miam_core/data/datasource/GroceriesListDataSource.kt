package com.miam.kmmMiamCore.miam_core.data.datasource


import com.miam.kmmMiamCore.miam_core.model.GroceriesList


interface GroceriesListDataSource {
    suspend fun getCurrent(included: List<String> = listOf()): GroceriesList
    suspend fun reset(): GroceriesList
    suspend fun updateGroceriesList(
        groceriesList: GroceriesList,
        included: List<String> = listOf()
    ): GroceriesList
}