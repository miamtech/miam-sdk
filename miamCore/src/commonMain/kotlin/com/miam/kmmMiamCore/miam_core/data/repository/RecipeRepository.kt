package com.miam.kmmMiamCore.miam_core.data.repository

import com.miam.kmmMiamCore.miam_core.model.Recipe
import com.miam.kmmMiamCore.miam_core.model.SuggestionsCriteria

interface RecipeRepository {
    suspend fun getRecipeById(recipeId: String): Recipe
    suspend fun getRecipesByIds(recipeIds: List<String>): List<Recipe>
    suspend fun getRecipeSuggestion(supplierId: Int, criteria: SuggestionsCriteria): Recipe
    suspend fun getRecipeSuggestions(supplierId: Int, criteria: SuggestionsCriteria, size: Int): List<Recipe>
    suspend fun getRecipeNumberOfResult(filter: String): Int
}