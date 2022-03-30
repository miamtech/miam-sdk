package com.miam.kmm_miam_sdk.miam_core.data.datasource


import com.miam.kmm_miam_sdk.miam_core.model.*


interface GroceriesListDataSource {
    suspend fun getCurrent() : GroceriesList
    suspend fun reset() : GroceriesList
    suspend fun getGroceriesEntries(glId : Int) : GroceriesEntries
    suspend fun updateGroceriesList(groceriesList: GroceriesListWithoutRelationship) :GroceriesList
    suspend fun getRecipes(recipesInfos: List<RecipeInfos>): List<Recipe>
}