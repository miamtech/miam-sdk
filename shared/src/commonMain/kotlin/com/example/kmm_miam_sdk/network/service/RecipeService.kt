package com.example.kmm_miam_sdk.network.service

import com.example.kmm_miam_sdk.network.model.Recipe
import com.example.kmm_miam_sdk.network.model.RecipeDTO
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class RecipeService(
    private val httpClient: HttpClient,
    private val baseUrl: String,
) {


     suspend fun get(id: Int): Recipe {
        return httpClient.get<RecipeDTO>{

            headers {
                append(HttpHeaders.ContentType, "application/vnd.api+json")
                append(HttpHeaders.Accept,"*/*")
            }
            url("$baseUrl/$id")
        }.recipe

    }

    companion object {
        const val BASE_URL = "https://api-uat.miam.tech/api/v1/recipes"
    }
}