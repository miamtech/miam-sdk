package com.miam.kmm_miam_sdk.network.service


import com.miam.kmm_miam_sdk.network.model.DAO.RecipeDAO
import com.miam.kmm_miam_sdk.network.model.DAO.RecipeDAOResult



import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class RecipeServiceOld(
    private val httpClient: HttpClient,
    private val baseUrl: String,
) {

     suspend fun get(id: Int): RecipeDAO {

       val response = httpClient.get<RecipeDAOResult>{

            headers {
                append(HttpHeaders.ContentType, "application/vnd.api+json")
                append(HttpHeaders.Accept,"*/*")
            }
            url("${baseUrl}recipes/$id")
        }

         return response.data

    }

}