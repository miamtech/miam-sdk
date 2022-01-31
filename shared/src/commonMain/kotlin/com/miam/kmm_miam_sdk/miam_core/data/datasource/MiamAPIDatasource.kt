package com.miam.kmm_miam_sdk.miam_core.data.datasource

import com.miam.kmm_miam_sdk.base.mvi.UserStore
import com.miam.kmm_miam_sdk.miam_core.model.*

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

import io.ktor.http.*
import io.ktor.util.*

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


object HttpRoutes {
    private const val BASE_URL = "https://api.miam.tech/api/v1"
    const val RECIPE_ENDPOINT = "$BASE_URL/recipes/"
    const val INGREDIENT_ENDPOINT = "$BASE_URL/recipes/"
    const val STEP_ENDPOINT = "$BASE_URL/recipes/"
    const val STATUS_ENDPOINT = "$BASE_URL/recipes/"
    const val PROVIDER_ENDPOINT = "$BASE_URL/recipes/"
    const val SPONSOR_ENDPOINT = "$BASE_URL/recipes/"
    const val TYPE_ENDPOINT = "$BASE_URL/recipes/"
    const val GROCERIESLIST_ENDPOINT = "$BASE_URL/groceries-lists/"
    const val POINTOFSALE_ENDPOINT = "$BASE_URL/point-of-sales/"
    const val BASKET_ENDPOINT = "$BASE_URL/baskets/"
}

@OptIn(InternalAPI::class)
class MiamAPIDatasource: RecipeDataSource ,GroceriesListDataSource, PointOfSaleDataSource,BasketDataSource, KoinComponent {

    private val userStore: UserStore by inject()

    private val httpClient = HttpClient{
        install(JsonFeature) {
            serializer = KotlinxSerializer(
                kotlinx.serialization.json.Json {
                    ignoreUnknownKeys = true // if the server sends extra fields, ignore them
                }
            )
            acceptContentTypes = listOf(ContentType.parse("application/vnd.api+json"),
                                        ContentType.parse("application/json"))

        }
    }

    init {
       httpClient.sendPipeline.intercept(HttpSendPipeline.State) {
           context.headers.append(HttpHeaders.Accept,"*/*")
           val userId =  userStore.observeState().value.userId
           if(userId != null){
               context.headers.append(HttpHeaders.Authorization, "user_id $userId" )
           }

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

    override suspend fun getProvider(entityId: Int): RecipeProvider {
        return httpClient.get<RecipeProviderWrapper>{
            url(HttpRoutes.PROVIDER_ENDPOINT+"${entityId}/recipe-provider")
        }.data
    }

    override suspend fun getStep(entityId: Int): RecipeSteps {
        return httpClient.get{
            url(HttpRoutes.STEP_ENDPOINT+"${entityId}/recipe-steps")
        }
    }

    override suspend fun getStatus(entityId: Int): RecipeStatus {
        return httpClient.get<RecipeStatusWrapper>{
            url(HttpRoutes.STATUS_ENDPOINT+"${entityId}/recipe-status")
        }.data
    }

    override suspend fun getSponsor(entityId: Int): Sponsors {
        return httpClient.get<Sponsors>{
            url(HttpRoutes.SPONSOR_ENDPOINT+"${entityId}/sponsors")
        }
    }

    override suspend fun getType(entityId: Int): RecipeType {
        return httpClient.get<RecipeTypeWrapper>{
            url(HttpRoutes.TYPE_ENDPOINT+"${entityId}/recipe-type")
        }.data
    }

    ///////// GroceriesList ///////////////

    override suspend fun getCurrent(): GroceriesList {
            return httpClient.get<GroceriesListWrapper> {
                url(HttpRoutes.GROCERIESLIST_ENDPOINT+"current")
            }.data
    }

    override suspend fun getNew(): GroceriesList {
        return httpClient.get<GroceriesListWrapper>{
            url(HttpRoutes.GROCERIESLIST_ENDPOINT+"reset")
        }.data
    }

    override suspend fun getGroceriesEntries(glId : Int): GroceriesEntries {
            return httpClient.get{
                url(HttpRoutes.GROCERIESLIST_ENDPOINT+"$glId/groceries-entries")
            }
    }

    override suspend fun updateGroceriesList(groceriesList: GroceriesList): GroceriesList {
        try {
          return  httpClient.patch<GroceriesListWrapper>{
                headers.append( HttpHeaders.ContentType, "application/vnd.api+json" )
                url(HttpRoutes.GROCERIESLIST_ENDPOINT+"${groceriesList.id}")
                body = GroceriesListWrapper(groceriesList)
            }.data
        } catch (c :Throwable) {
            print(c)
        }

        return groceriesList
    }

/////////////////////// POINT OF SALE ////////////////////////////////////////////////


    override suspend fun getPosFormExtId(extId: String, supplierId: Int): PointOfSale {
        // this filter is suppose to return a single result or nothing
      val posList =  httpClient.get<PointOfSales>{
            url(HttpRoutes.POINTOFSALE_ENDPOINT+"?filter[ext-id]=$extId&filter[supplier-id]=$supplierId")
        }
      if(posList.data.isEmpty()) throw Exception("Point of sale not found or incorrect")
        return posList.data[0]
    }

/////////////////////// BASKET ////////////////////////////////////////////////


    override suspend fun getFromListAndPos(listId: Int, posId: Int): Basket {
        val baskets = httpClient.get<Baskets>{
            url(HttpRoutes.GROCERIESLIST_ENDPOINT+"$listId/baskets?filter[point_of_sale_id]=$posId")
        }
        if(baskets.baskets.isEmpty()) throw Exception("basket not found or incorrect")
        return baskets.baskets[0]
    }

    override suspend fun getBasketEntries(basketId : Int): BasketEntries {
        return  httpClient.get<BasketEntries>{
            url(HttpRoutes.BASKET_ENDPOINT+"$basketId/basket-entries")
        }
    }
}
