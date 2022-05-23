package com.miam.kmm_miam_sdk.miam_core.data.datasource

import com.miam.kmm_miam_sdk.miam_core.model.*
import io.ktor.util.reflect.*

interface RecipeDataSource {
    suspend fun getRecipeById(id: String, included: List<String> = listOf()): Recipe
    suspend fun getRecipes(recipesIds: List<String>, included: List<String> = listOf(), pageSize: Int = 20): List<Recipe>
    suspend fun getRecipeSuggestions(supplierId: Int, criteria: SuggestionsCriteria, included: List<String> = listOf()): List<Recipe>
    suspend fun getRecipeLikes(recipesIds: List<String>, pageSize: Int = 20): List<RecipeLike>
    suspend fun createRecipeLike(recipeLike: RecipeLike): RecipeLike
    suspend fun updateRecipeLike(recipeLike: RecipeLike): RecipeLike
}