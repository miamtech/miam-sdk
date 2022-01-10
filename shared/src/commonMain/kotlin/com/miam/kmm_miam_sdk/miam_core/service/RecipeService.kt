package com.miam.kmm_miam_sdk.miam_core.service

import com.miam.kmm_miam_sdk.miam_core.model.Recipe
import com.miam.kmm_miam_sdk.miam_core.utils.HttpClientHelper
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*

interface RecipeService {
    suspend fun getRecipeById(id: Int): Recipe

    companion object {
        fun create(): RecipeService{
            return RecipeServiceImpl(
                client = HttpClientHelper.httpClient
            )
        }
    }
}