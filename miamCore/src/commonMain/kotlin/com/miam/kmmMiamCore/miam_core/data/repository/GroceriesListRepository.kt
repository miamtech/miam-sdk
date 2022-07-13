package com.miam.kmmMiamCore.miam_core.data.repository

import com.miam.kmmMiamCore.miam_core.model.GroceriesList

interface GroceriesListRepository {
    suspend fun getCurrent(): GroceriesList
    suspend fun reset(): GroceriesList
    suspend fun updateGroceriesList(gl : GroceriesList): GroceriesList
    suspend fun removeRecipeFromList() : GroceriesList
    suspend fun updateRecipeGuests() : GroceriesList
    suspend fun removeAllRecipes(): GroceriesList
}