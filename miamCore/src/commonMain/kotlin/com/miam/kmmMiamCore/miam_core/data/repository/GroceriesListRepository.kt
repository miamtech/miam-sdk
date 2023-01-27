package com.miam.kmmMiamCore.miam_core.data.repository

import com.miam.kmmMiamCore.miam_core.model.GroceriesList

public interface GroceriesListRepository {
    public suspend fun getCurrent(): GroceriesList
    public suspend fun reset(): GroceriesList
    public suspend fun updateGroceriesList(gl: GroceriesList): GroceriesList
    public suspend fun removeRecipeFromList(): GroceriesList
    public suspend fun updateRecipeGuests(): GroceriesList
    public suspend fun removeAllRecipes(): GroceriesList
}