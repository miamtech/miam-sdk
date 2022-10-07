package com.miam.kmmMiamCore.miam_core.data.datasource

import com.miam.kmmMiamCore.miam_core.model.Recipe
import com.miam.kmmMiamCore.miam_core.model.RecipeLike
import com.miam.kmmMiamCore.miam_core.model.SuggestionsCriteria

interface RecipeDataSource {
    suspend fun getRecipeById(id: String, included: List<String> = listOf()): Recipe
    suspend fun getRecipeByIds(
        recipesIds: List<String>,
        included: List<String> = listOf(),
        pageSize: Int = 20
    ): List<Recipe>

    suspend fun getRecipes(
        filters: Map<String, String>,
        included: List<String>,
        pageSize: Int = 20,
        pageNumber: Int = 1
    ): List<Recipe>

    suspend fun getRecipesFromStringFilter(
        filters: String,
        included: List<String>,
        pageSize: Int = 20,
        pageNumber: Int = 1
    ): List<Recipe>

    suspend fun getRecipeSuggestions(
        supplierId: Int,
        size: Int = 1,
        criteria: SuggestionsCriteria,
        included: List<String> = listOf()
    ): List<Recipe>

    suspend fun getRecipeLikes(recipesIds: List<String>, pageSize: Int = 20): List<RecipeLike>
    suspend fun createRecipeLike(recipeLike: RecipeLike): RecipeLike
    suspend fun updateRecipeLike(recipeLike: RecipeLike): RecipeLike
    suspend fun getRecipeNumberOfResult(filter: String): Int
}