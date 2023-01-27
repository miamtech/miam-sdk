package com.miam.kmmMiamCore.miam_core.data.repository

import com.miam.kmmMiamCore.miam_core.model.Recipe
import com.miam.kmmMiamCore.miam_core.model.SuggestionsCriteria

public interface RecipeRepository {
    public suspend fun getRecipeById(recipeId: String): Recipe
    public suspend fun getRecipesByIds(recipeIds: List<String>): List<Recipe>
    public suspend fun getRecipeSuggestion(supplierId: Int, criteria: SuggestionsCriteria): Recipe
    public suspend fun getRecipeSuggestions(supplierId: Int, criteria: SuggestionsCriteria, size: Int): List<Recipe>
    public suspend fun getRecipeNumberOfResult(filter: String): Int
}