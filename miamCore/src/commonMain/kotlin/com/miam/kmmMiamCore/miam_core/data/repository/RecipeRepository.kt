package com.miam.kmmMiamCore.miam_core.data.repository

import com.miam.kmmMiamCore.miam_core.model.Recipe
import com.miam.kmmMiamCore.miam_core.model.SuggestionsCriteria

public interface RecipeRepository {
    public suspend fun getRecipeById(recipeId: String): Recipe
    public suspend fun getRecipesByIds(recipeIds: List<String>): List<Recipe>
    public suspend fun getRecipeSuggestion(supplierId: String, criteria: SuggestionsCriteria): Recipe
    public suspend fun getRecipeSuggestions(supplierId: String, criteria: SuggestionsCriteria, size: Int): List<Recipe>
    public suspend fun getRecipeNumberOfResult(filters: Map<String, String>): Int
}