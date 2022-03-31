package com.miam.kmm_miam_sdk.miam_core.data.repository

import com.miam.kmm_miam_sdk.miam_core.model.GroceriesList

interface GroceriesListRepository {
    suspend fun getCurrent(): GroceriesList
    suspend fun reset(): GroceriesList
    suspend fun updateGroceriesList(gl : GroceriesList): GroceriesList
    suspend fun removeRecipeFromList() : GroceriesList
    suspend fun updateRecipeGuests() : GroceriesList
    suspend fun removeAllRecipes(): GroceriesList
}