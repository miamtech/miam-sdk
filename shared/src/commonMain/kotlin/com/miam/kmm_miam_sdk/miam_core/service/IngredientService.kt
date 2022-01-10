package com.miam.kmm_miam_sdk.miam_core.service

import com.miam.kmm_miam_sdk.miam_core.model.Ingredients
import com.miam.kmm_miam_sdk.miam_core.model.Recipe
import com.miam.kmm_miam_sdk.miam_core.utils.HttpClientHelper
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*

interface IngredientService {
    suspend fun getIngredient(entityId: Int): Ingredients

    companion object {
        fun create(): IngredientService{
            return IngredientServiceImpl(
                client = HttpClientHelper.httpClient
            )
        }
    }
}