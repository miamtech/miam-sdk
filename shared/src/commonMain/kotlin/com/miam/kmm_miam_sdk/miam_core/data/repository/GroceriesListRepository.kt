package com.miam.kmm_miam_sdk.miam_core.data.repository

import com.miam.kmm_miam_sdk.miam_core.model.GroceriesList
import com.miam.kmm_miam_sdk.miam_core.model.GroceriesListWithoutRelationship
import com.miam.kmm_miam_sdk.miam_core.model.Recipe
import kotlinx.coroutines.flow.Flow

interface GroceriesListRepository {
    suspend fun getCurrent(): GroceriesList
    suspend fun reset(): GroceriesList
    suspend fun updateGroceriesList(gl : GroceriesListWithoutRelationship) : GroceriesList
    suspend fun removeRecipeFromList() : Flow<GroceriesList>
    suspend fun updateRecipeGuests() : Flow<GroceriesList>
    suspend fun removeAllRecipes():Flow<GroceriesList>
}