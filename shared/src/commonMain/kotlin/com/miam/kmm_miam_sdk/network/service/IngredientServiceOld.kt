package com.miam.kmm_miam_sdk.network.service

import com.miam.kmm_miam_sdk.network.model.DAO.IngredientDAO
import com.miam.kmm_miam_sdk.network.model.DAO.IngredientsDAO
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class IngredientServiceOld(private val httpClient: HttpClient,
                           private val baseUrl: String,) {



    suspend fun getWhere(type :String, entityId :Int): List<IngredientDAO> {

        val response = httpClient.get<IngredientsDAO>{

            headers {
                append(HttpHeaders.ContentType, "application/vnd.api+json")
                append(HttpHeaders.Accept,"*/*")
            }
            url("${baseUrl}${type}/${entityId}/ingredients")
        }

        return response.ingredients

    }
}