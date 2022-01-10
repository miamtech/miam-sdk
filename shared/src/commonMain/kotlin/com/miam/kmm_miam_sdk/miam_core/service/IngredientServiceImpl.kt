package com.miam.kmm_miam_sdk.miam_core.service

import com.miam.kmm_miam_sdk.miam_core.model.Ingredients
import com.miam.kmm_miam_sdk.miam_core.model.Recipe
import com.miam.kmm_miam_sdk.miam_core.model.RecipeWrapper
import com.miam.kmm_miam_sdk.miam_core.utils.HttpRoutes
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*


class IngredientServiceImpl(
    private val client: HttpClient
):IngredientService {

    override suspend fun getIngredient(entityId: Int, type: String): Ingredients {
       return callIngredientAPI(entityId, type)
    }

    private suspend fun callIngredientAPI(entityId: Int, type: String): Ingredients{
        return client.get<Ingredients>{
            headers {
                append(HttpHeaders.ContentType, "application/vnd.api+json")
                append(HttpHeaders.Accept,"*/*")
            }
            url(HttpRoutes.INGREDIENT_ENDPOINT+"/${type}/${entityId}/ingredients")
        }
    }

}