package com.miam.kmm_miam_sdk.miam_core.service

import com.miam.kmm_miam_sdk.miam_core.model.Recipe
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*

interface RecipeService {
    suspend fun getRecipeById(): Recipe

    companion object {
        fun create(): RecipeService{
            return RecipeServiceImpl(
                client = HttpClient(CIO){
                    install(JsonFeature) {
                        serializer = io.ktor.client.features.json.serializer.KotlinxSerializer(
                            kotlinx.serialization.json.Json {
                                ignoreUnknownKeys = true // if the server sends extra fields, ignore them
                            }
                        )
                    }
                }
            )
        }
    }
}