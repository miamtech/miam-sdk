package com.miam.kmm_miam_sdk.miam_core.data.repository

import com.miam.kmm_miam_sdk.miam_core.model.GroceriesList
import kotlinx.coroutines.flow.Flow

interface GroceriesListRepository {
    suspend fun getCurrent(): Flow<GroceriesList>
    suspend fun getNew(): Flow<GroceriesList>
    suspend fun updateGroceriesList(gl :GroceriesList) :Flow<GroceriesList>
    suspend fun removeRecipeFromList() : Flow<GroceriesList>
    suspend fun updateRecipeGuests() : Flow<GroceriesList>
    suspend fun removeAllRecipes():Flow<GroceriesList>
}