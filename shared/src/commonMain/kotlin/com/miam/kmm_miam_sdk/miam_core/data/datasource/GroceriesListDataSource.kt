package com.miam.kmm_miam_sdk.miam_core.data.datasource

import com.miam.kmm_miam_sdk.miam_core.model.Baskets
import com.miam.kmm_miam_sdk.miam_core.model.GroceriesEntries
import com.miam.kmm_miam_sdk.miam_core.model.GroceriesList

interface GroceriesListDataSource {
    suspend fun getCurrent() : GroceriesList
    suspend fun getNew() : GroceriesList
    suspend fun getGroceriesEntries(glId : Int) : GroceriesEntries
    suspend fun getBasket(glId : Int) : Baskets
}