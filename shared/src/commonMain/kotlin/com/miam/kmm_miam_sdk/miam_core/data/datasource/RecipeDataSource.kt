package com.miam.kmm_miam_sdk.miam_core.data.datasource

import com.miam.kmm_miam_sdk.miam_core.model.*
import io.ktor.util.reflect.*

interface RecipeDataSource {
    suspend fun getRecipeById(id: String, included: List<String> = listOf()): Recipe
    suspend fun getRecipeSuggestions(customerId: Int, criteria: SuggestionsCriteria, included: List<String> = listOf()): List<Recipe>
}