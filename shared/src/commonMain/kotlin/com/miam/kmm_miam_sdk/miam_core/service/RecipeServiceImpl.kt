package com.miam.kmm_miam_sdk.miam_core.service

import com.miam.kmm_miam_sdk.miam_core.model.Recipe
import com.miam.kmm_miam_sdk.miam_core.model.RecipeWrapper
import com.miam.kmm_miam_sdk.miam_core.utils.HttpRoutes
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*


class RecipeServiceImpl(
    private val client: HttpClient
):RecipeService {

    override suspend fun getRecipeById(): Recipe {
       return callRecipeAPI()
    }
    
    private suspend fun callRecipeAPI(): Recipe{
        return client.get<RecipeWrapper>{
            headers {
                append(HttpHeaders.ContentType, "application/vnd.api+json")
                append(HttpHeaders.Accept,"*/*")
            }
            url(HttpRoutes.RECIPE_ENDPOINT)
        }.data
    }

}