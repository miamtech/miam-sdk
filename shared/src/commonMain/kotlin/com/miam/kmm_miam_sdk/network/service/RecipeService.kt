package com.miam.kmm_miam_sdk.network.service

import com.miam.kmm_miam_sdk.network.model.Recipe
import com.miam.kmm_miam_sdk.network.model.RecipeDTO
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class RecipeService(
    private val httpClient: HttpClient,
    private val baseUrl: String,
) {

     suspend fun get(id: Int): Recipe {
         println("Get Recipe call Miam" )
        return httpClient.get<RecipeDTO>{

            headers {
                append(HttpHeaders.ContentType, "application/vnd.api+json")
                append(HttpHeaders.Accept,"*/*")
            }
            url("${baseUrl}recipes/$id")
        }.recipe

    }

}