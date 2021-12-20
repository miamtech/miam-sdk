package com.example.kmm_miam_sdk.network.service.recipeService

import com.example.kmm_miam_sdk.network.model.Recipe

interface RecipeService {

    suspend fun get(
        id: Int
    ): Recipe
}