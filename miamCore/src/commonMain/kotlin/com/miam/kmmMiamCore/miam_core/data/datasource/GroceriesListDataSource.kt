package com.miam.kmmMiamCore.miam_core.data.datasource


import com.miam.kmmMiamCore.miam_core.model.GroceriesList


public interface GroceriesListDataSource {
    public suspend fun getCurrent(included: List<String> = listOf()): GroceriesList
    public suspend fun reset(): GroceriesList
    public suspend fun updateGroceriesList(
        groceriesList: GroceriesList,
        included: List<String> = listOf()
    ): GroceriesList
}