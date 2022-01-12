package com.miam.kmm_miam_sdk.miam_core.data.datasource

import com.miam.kmm_miam_sdk.miam_core.model.Ingredients
import com.miam.kmm_miam_sdk.miam_core.model.Recipe
import com.miam.kmm_miam_sdk.miam_core.model.RecipeWrapper
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*

object HttpRoutes {
    private const val BASE_URL = "https://api.miam.tech/api/v1"
    const val RECIPE_ENDPOINT = "$BASE_URL/recipes/"
    const val INGREDIENT_ENDPOINT = "$BASE_URL/recipes/"
}

class MiamAPIDatasource: RecipeDataSource {

    // TODO manage exception when 3xx 4xx 5xx
    private val httpClient = HttpClient{
        install(JsonFeature) {
            serializer = KotlinxSerializer(
                kotlinx.serialization.json.Json {
                    ignoreUnknownKeys = true // if the server sends extra fields, ignore them
                }
            )
        }
    }

    // TODO factorize header in a object
    override suspend fun getIngredient(entityId: Int): Ingredients {
        return httpClient.get<Ingredients>{
            headers {
                append(HttpHeaders.ContentType, "application/vnd.api+json")
                append(HttpHeaders.Accept,"*/*")
            }
            url(HttpRoutes.INGREDIENT_ENDPOINT+"${entityId}/ingredients")
        }
    }

    override suspend fun getRecipeById(id: Int): Recipe {
        return httpClient.get<RecipeWrapper>{
            headers {
                append(HttpHeaders.ContentType, "application/vnd.api+json")
                append(HttpHeaders.Accept,"*/*")
            }
            url(HttpRoutes.RECIPE_ENDPOINT+"$id")
        }.data
    }
}