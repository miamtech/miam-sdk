package com.miam.kmm_miam_sdk.miam_core.data.datasource

import com.miam.kmm_miam_sdk.miam_core.model.*
import io.ktor.util.reflect.*

interface RecipeDataSource {
    suspend fun getIngredient(entityId: Int): Ingredients
    suspend fun getProvider(entityId: Int): RecipeProvider
    suspend fun getStep(entityId: Int): RecipeSteps
    suspend fun getStatus(entityId: Int): RecipeStatus
    suspend fun getSponsor(entityId: Int): Sponsors
    suspend fun getType(entityId: Int): RecipeType
    suspend fun getRecipeById(id: Int): Recipe
    suspend fun getRecipeSuggestions(customerId: Int, criteria: SuggestionsCriteria): Recipe
}