package com.miam.kmm_miam_sdk.miam_core.data.repository

import com.miam.kmm_miam_sdk.miam_core.model.Recipe
import com.miam.kmm_miam_sdk.miam_core.model.SuggestionsCriteria
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun getRecipeById(recipeId: String): Recipe
    suspend fun getRecipesByIds(recipeIds: List<String>): List<Recipe>
    suspend fun getRecipeSuggestions(supplierId: Int, criteria: SuggestionsCriteria): Recipe
}