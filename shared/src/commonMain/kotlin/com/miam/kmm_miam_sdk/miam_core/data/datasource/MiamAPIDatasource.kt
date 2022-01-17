package com.miam.kmm_miam_sdk.miam_core.data.datasource

import com.miam.kmm_miam_sdk.miam_core.model.Ingredients
import com.miam.kmm_miam_sdk.miam_core.model.Recipe
import com.miam.kmm_miam_sdk.miam_core.model.RecipeWrapper
import io.ktor.client.*
import io.ktor.client.features.*
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

    private val httpClient = HttpClient{
        install(JsonFeature) {
            serializer = KotlinxSerializer(
                kotlinx.serialization.json.Json {
                    ignoreUnknownKeys = true // if the server sends extra fields, ignore them
                }
            )
        }
    }

    private suspend inline fun <reified T> get(url:String): T? {
        return try {
            httpClient.get<T>{
                headers {
                    append(HttpHeaders.ContentType, "application/vnd.api+json")
                    append(HttpHeaders.Accept,"*/*")
                }
                url(url)
            }
        } catch(e: RedirectResponseException){
            // 3XX
            println ("Error: ${e.response.status.description}")
            null
        }catch(e: ClientRequestException){
            // 4xx
            println ("Error: ${e.response.status.description}")
            null
        }catch(e: ServerResponseException){
            // 5xx
            println ("Error: ${e.response.status.description}")
            null
        }catch(e: Exception){
            println ("Error: ${e.message}")
            null
        }
    }

    override suspend fun getIngredient(entityId: Int): Ingredients? {
        return this.get<Ingredients>(HttpRoutes.INGREDIENT_ENDPOINT+"${entityId}/ingredients")
    }

    override suspend fun getRecipeById(id: Int): Recipe {
        return this.get<RecipeWrapper>(HttpRoutes.RECIPE_ENDPOINT + "$id")!!.data
    }
}


