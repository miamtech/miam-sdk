package com.miam.kmm_miam_sdk.miam_core.data.datasource

import com.miam.kmm_miam_sdk.base.mvi.UserStore
import com.miam.kmm_miam_sdk.miam_core.model.*

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

import io.ktor.http.*
import io.ktor.util.*
import io.ktor.utils.io.*

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class MiamResponseException(response: HttpResponse, cachedResponseText: String) :
    ResponseException(response, cachedResponseText) {
    override val message: String = "Custom server error: ${response.call.request.url}. " +
            "Status: ${response.status}. Text: \"$cachedResponseText\""
}


object HttpRoutes {
    private const val BASE_URL = "https://api.miam.tech/api/v1"
    // private const val BASE_URL = "http://192.168.1.21:3000/api/v1"
    const val RECIPE_ENDPOINT = "$BASE_URL/recipes/"
    const val GROCERIESLIST_ENDPOINT = "$BASE_URL/groceries-lists/"
    const val GROCERIES_ENTRY_ENDPOINT = "$BASE_URL/groceries-entries"
    const val POINTOFSALE_ENDPOINT = "$BASE_URL/point-of-sales/"
    const val BASKET_ENDPOINT = "$BASE_URL/baskets/"
    const val BASKET_ENTRIES_ENDPOINT = "$BASE_URL/basket-entries/"
    const val RECIPE_SUGGESTIONS= "$BASE_URL/recipes/suggestions"
    const val SUPPLIER = "$BASE_URL/suppliers/"
}


@OptIn(InternalAPI::class)
class MiamAPIDatasource: RecipeDataSource, GroceriesListDataSource, PointOfSaleDataSource,
    BasketDataSource, PricingDataSource, BasketEntryDataSource, GrocerieEntryDataSource,
    SupplierDataSource, KoinComponent {

    private val userStore: UserStore by inject()
    private var sessionId : String? = null

    private val httpClient = HttpClient{
        install(JsonFeature) {
            serializer = KotlinxSerializer(
                kotlinx.serialization.json.Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                    allowSpecialFloatingPointValues = true
                    useArrayPolymorphism = true// if the server sends extra fields, ignore them

                }
            )
            acceptContentTypes = listOf(ContentType.parse("application/vnd.api+json"),
                                        ContentType.parse("application/json"))
        }
    }.also { initLogger()}

    init {
        httpClient.receivePipeline.intercept(HttpReceivePipeline.State) {
            if(sessionId == null ){
                sessionId =   context.response.headers["set-cookie"]!!.split(';')[0]
            }
        }

       httpClient.sendPipeline.intercept(HttpSendPipeline.State) {
           context.headers.append(HttpHeaders.Accept,"*/*")
           if(sessionId != null) {
               context.headers.remove("Cookie")
               context.headers.append(HttpHeaders.Cookie, sessionId!!)
           }
         
            userStore.observeState().value.userId.let {
               context.headers.append(HttpHeaders.Authorization, "user_id $it" )
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
            throw e
        }catch(e: Exception){
            println ("Error: ${e.message}")
            throw e
        }
    }

    private suspend inline fun <reified T> post(url:String,data:Any ): T? {
        return try {
            httpClient.post<T>{
                headers {
                    append(HttpHeaders.ContentType, "application/vnd.api+json")
                    append(HttpHeaders.Accept,"*/*")
                }
                url(url)
                body=data
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

    override suspend fun getRecipeById(id: String, included: List<String>): Recipe {
        return this.get<RecordWrapper>(HttpRoutes.RECIPE_ENDPOINT + id + "?" + includedToString(included))!!.toRecord() as Recipe
    }

    override suspend fun getRecipeSuggestions(
        supplierId: Int,
        criteria: SuggestionsCriteria,
        included: List<String>
    ): List<Recipe> {
        return this.post<RecordWrapper>("${HttpRoutes.RECIPE_SUGGESTIONS}?supplier_id=${supplierId}&${includedToString(included)}", criteria)!!.toRecords() as List<Recipe>
    }

    ///////// GroceriesList ///////////////

    override suspend fun getCurrent(included: List<String>): GroceriesList {
        return httpClient.get<RecordWrapper> {
            url(HttpRoutes.GROCERIESLIST_ENDPOINT + "current?" + includedToString(included))
        }.toRecord() as GroceriesList
    }

    override suspend fun reset(): GroceriesList {
        return httpClient.get<RecordWrapper>{
            url(HttpRoutes.GROCERIESLIST_ENDPOINT+"reset")
        }.toRecord() as GroceriesList
    }

    override suspend fun updateGroceriesList(groceriesList: GroceriesList, included: List<String>): GroceriesList {
        return  httpClient.patch<RecordWrapper>{
                headers.append( HttpHeaders.ContentType, "application/vnd.api+json" )
                url(HttpRoutes.GROCERIESLIST_ENDPOINT + groceriesList.id + "?" + includedToString(included))
                body = RecordWrapper.fromRecord(groceriesList)
            }.toRecord() as GroceriesList
    }

    override suspend fun getRecipes(recipesInfos: List<RecipeInfos>): List<Recipe> {
        return recipesInfos.map{ ri -> getRecipeById(ri.id.toString()) }
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


    override suspend fun getFromListAndPos(listId: String, posId: Int, included: List<String>): Basket {
        val baskets = httpClient.get<RecordWrapper>{
            url(HttpRoutes.GROCERIESLIST_ENDPOINT+"$listId/baskets?filter[point_of_sale_id]=$posId&${includedToString(included)}")
        }.toRecords() as List<Basket>
        if(baskets.isEmpty()) throw Exception("basket not found or incorrect")
        return baskets[0]
    }

    override suspend fun updateBasket(basket: Basket, origin:String): Basket {
        return  httpClient.patch<RecordWrapper>{
            headers.append(HttpHeaders.Origin, origin)
            headers.append( HttpHeaders.ContentType, "application/vnd.api+json" )
            url(HttpRoutes.BASKET_ENDPOINT+"${basket.id}")
            body = RecordWrapper.fromRecord(basket)
        }.toRecord() as Basket
    }

/////////////////////////////// PRICING ///////////////////////////////////////////////////

    override suspend fun getRecipePrice(idRecipe: String, idPos: Int): Pricing {
        return  httpClient.get{
            url(HttpRoutes.RECIPE_ENDPOINT+"$idRecipe/pricing?point_of_sale_id=$idPos")
        }
    }

////////////////////////////////// BASKET ENTRY ////////////////////////////////////////

    override suspend fun updateBasketEntry(basketEntry: BasketEntry, included: List<String>): BasketEntry {
        // println("Miam datasource updateBasketEntry $basketEntry")
        return httpClient.patch<RecordWrapper>{
            headers.append( HttpHeaders.ContentType, "application/vnd.api+json" )
            url(HttpRoutes.BASKET_ENTRIES_ENDPOINT+"/${basketEntry.id}?${includedToString(included)}")
            body = RecordWrapper.fromRecord(basketEntry)
        }.toRecord() as BasketEntry
    }

    ////////////////////////////////// GROCERY ENTRY ////////////////////////////////////////

    override suspend fun updateGroceriesEntry(ge: GroceriesEntry): GroceriesEntry {
        // println("Miam datasource updateGroceriesEntry $ge")
        return  httpClient.patch<RecordWrapper>{
         headers.append( HttpHeaders.ContentType, "application/vnd.api+json" )
         url(HttpRoutes.GROCERIES_ENTRY_ENDPOINT+"/${ge.id}")
         body =  RecordWrapper.fromRecord((ge))
     }.toRecord() as GroceriesEntry
    }

    /////////////////////////////////////SUPPLIER /////////////////////////////////////////////////


    override suspend fun notifyBasketUpdated(basketToken: String, supplierId: Int, status: String, price: String?) {
        return  httpClient.post{
            headers.append( HttpHeaders.ContentType, "application/vnd.api+json" )
            url("${HttpRoutes.SUPPLIER}$supplierId/webhooks/basket_updated")
            body = SupplierNotificationWrapper(basketToken,status, price)
        }
    }

    private fun includedToString(included: List<String>): String {
        return if (included.isEmpty()) "" else "include=" + included.joinToString(",")
    }
}
