package com.miam.kmm_miam_sdk.miam_core.data.datasource


import com.miam.kmm_miam_sdk.miam_core.model.*


interface GroceriesListDataSource {
    suspend fun getCurrent(included: List<String> = listOf()) : GroceriesList
    suspend fun reset() : GroceriesList
    suspend fun updateGroceriesList(groceriesList: GroceriesList, included: List<String> = listOf()) :GroceriesList
}