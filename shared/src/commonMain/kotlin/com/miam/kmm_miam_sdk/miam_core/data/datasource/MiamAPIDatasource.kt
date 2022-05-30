package com.miam.kmm_miam_sdk.miam_core.data.datasource

import com.miam.kmm_miam_sdk.base.mvi.UserStore
import com.miam.kmm_miam_sdk.handler.LogHandler
import com.miam.kmm_miam_sdk.miam_core.model.*

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

import io.ktor.http.*
import io.ktor.util.*

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject





object HttpRoutes {
    private const val BASE_URL = "https://api.miam.tech/api/v1"
    // private const val BASE_URL = "http://127.0.0.1:3000/api/v1"
    const val RECIPE_ENDPOINT = "$BASE_URL/recipes/"
    const val RECIPE_LIKE_ENDPOINT = "$BASE_URL/recipe-likes/"
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
        httpClient.receivePipeline.intercept(HttpReceivePipeline.State) {
            if(userStore.getSessionId() == null) {
                val newSessionId = "${context.response.headers["set-cookie"]}".split(';')[0]
                if (userStore.sameSession(newSessionId)) return@intercept

                userStore.setSessionId(newSessionId)
            }
        }

       httpClient.sendPipeline.intercept(HttpSendPipeline.State) {
           context.headers.append(HttpHeaders.Accept,"*/*")
           userStore.observeState().value.sessionId.let {
               context.headers.remove("Cookie")
               if (it != null ){
                   context.headers.append(HttpHeaders.Cookie, it)
               }
           }
           userStore.observeState().value.userId.let {
               context.headers.append(HttpHeaders.Authorization, "user_id $it" )
           }
           if (userStore.ProfilingForbiden()) {
               context.url.parameters["profiling"] = "off"
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
        LogHandler.info("[Miam][MiamAPIDatasource] starting getRecipeById $id")
        val returnValue = this.get<RecordWrapper>(HttpRoutes.RECIPE_ENDPOINT + id + "?" + includedToString(included))!!.toRecord() as Recipe
        LogHandler.info("[Miam][MiamAPIDatasource] end getRecipeById $id $returnValue")
        return returnValue
    }

    private suspend fun getRecipeByIdsChunck(recipesIds: List<String>, included: List<String>, pageSize: Int): List<Recipe> {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getRecipeByIdsChunck $recipesIds")
        val idFilters = "page[size]=$pageSize&filter[id]=${recipesIds.joinToString(",")}"
        val returnValue = this.get<RecordWrapper>(HttpRoutes.RECIPE_ENDPOINT + "?$idFilters&" + includedToString(included))!!.toRecords()
        LogHandler.info("[Miam][MiamAPIDatasource] end getRecipeById $recipesIds $returnValue")
        return returnValue.map { record -> record as Recipe }
    }

    override suspend fun getRecipeByIds(recipesIds: List<String>, included: List<String>, pageSize: Int): List<Recipe> {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getRecipeByIds $recipesIds")
        val returnValue: MutableList<Recipe> = mutableListOf()
        recipesIds.chunked(pageSize).forEach { recipesIdsChunck ->
            returnValue.addAll(getRecipeByIdsChunck(recipesIdsChunck, included, pageSize))
        }
        LogHandler.info("[Miam][MiamAPIDatasource] end getRecipes $recipesIds $returnValue")
        return returnValue
    }

    override suspend fun getRecipes(filters: Map<String, String>, included: List<String>, pageSize: Int, pageNumber: Int): List<Recipe> {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getRecipes $filters $included")
        val pageFilter = "page[size]=$pageSize&page[number]=$pageNumber"
        val includedStr = if(included.isEmpty()) "" else "&${includedToString(included)}"
        val filtersStr = if(filters.isEmpty()) "" else "&${filtersToString(filters)}"
        val returnValue = this.get<RecordWrapper>(HttpRoutes.RECIPE_ENDPOINT + "?$pageFilter$includedStr$filtersStr")!!.toRecords()
        LogHandler.info("[Miam][MiamAPIDatasource] end getRecipes $returnValue")
        return returnValue.map { record -> record as Recipe }
    }

    override suspend fun getRecipeSuggestions(
        supplierId: Int,
        criteria: SuggestionsCriteria,
        included: List<String>
    ): List<Recipe> {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getRecipeSuggestions $criteria")
        val url = "${HttpRoutes.RECIPE_SUGGESTIONS}?supplier_id=${supplierId}&${includedToString(included)}"
        val returnValue = this.post<RecordWrapper>(url, criteria)!!.toRecords()
        LogHandler.info("[Miam][MiamAPIDatasource] end getRecipeSuggestions $criteria $returnValue")
        return returnValue.map { record -> record as Recipe }
    }

    ///////// RecipeLike ///////////////

    private suspend fun getRecipeLikesByRecipeIds(recipesIds: List<String>, pageSize: Int): List<RecipeLike> {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getRecipeLikesByRecipeIds $recipesIds")
        val pageFilter = "page[size]=$pageSize"
        val idFilters = "filter[recipe_id]=${recipesIds.joinToString(",")}&filter[is_past]=true,false"
        val returnValue = this.get<RecordWrapper>(HttpRoutes.RECIPE_LIKE_ENDPOINT + "?$pageFilter&$idFilters")!!.toRecords()
        LogHandler.info("[Miam][MiamAPIDatasource] end getRecipeLikesByRecipeIds $recipesIds $returnValue")
        return returnValue.map { record -> record as RecipeLike }
    }

    override suspend fun getRecipeLikes(recipesIds: List<String>, pageSize: Int): List<RecipeLike> {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getRecipeLikes $recipesIds")
        val returnValue: MutableList<RecipeLike> = mutableListOf()
        recipesIds.chunked(pageSize).forEach { recipesIdsChunck ->
            returnValue.addAll(getRecipeLikesByRecipeIds(recipesIdsChunck, pageSize))
        }
        LogHandler.info("[Miam][MiamAPIDatasource] end getRecipeLikes $recipesIds $returnValue")
        return returnValue
    }

    override suspend fun createRecipeLike(recipeLike: RecipeLike): RecipeLike {
        LogHandler.info("[Miam][MiamAPIDatasource] starting createRecipeLike $recipeLike")
        LogHandler.info("[Miam][MiamAPIDatasource] starting createRecipeLike ${RecordWrapper.fromRecord(recipeLike)}")
        val returnValue = httpClient.post<RecordWrapper> {
            headers.append(HttpHeaders.ContentType, "application/vnd.api+json" )
            url(HttpRoutes.RECIPE_LIKE_ENDPOINT)
            body = RecordWrapper.fromRecord(recipeLike)
        }.toRecord()
        LogHandler.info("[Miam][MiamAPIDatasource] end createRecipeLike $returnValue")
        return returnValue as RecipeLike
    }

    override suspend fun updateRecipeLike(recipeLike: RecipeLike): RecipeLike {
        LogHandler.info("[Miam][MiamAPIDatasource] starting updateRecipeLike $recipeLike")
        val returnValue = httpClient.patch<RecordWrapper> {
            headers.append(HttpHeaders.ContentType, "application/vnd.api+json" )
            url("${HttpRoutes.RECIPE_LIKE_ENDPOINT}${recipeLike.id}")
            body = RecordWrapper.fromRecord(recipeLike)
        }.toRecord()
        LogHandler.info("[Miam][MiamAPIDatasource] end updateRecipeLike $returnValue")
        return returnValue as RecipeLike
    }

    ///////// GroceriesList ///////////////

    override suspend fun getCurrent(included: List<String>): GroceriesList {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getCurrent")
        val returnValue =  httpClient.get<RecordWrapper> {
            url(HttpRoutes.GROCERIESLIST_ENDPOINT + "current?" + includedToString(included))
        }.toRecord() as GroceriesList
        LogHandler.info("[Miam][MiamAPIDatasource] end getCurrent")
        return returnValue
    }

    override suspend fun reset(): GroceriesList {
        LogHandler.info("[Miam][MiamAPIDatasource] starting reset")
        val returnValue =   httpClient.get<RecordWrapper>{
            url(HttpRoutes.GROCERIESLIST_ENDPOINT+"reset")
        }.toRecord() as GroceriesList
        LogHandler.info("[Miam][MiamAPIDatasource] end reset")
        return returnValue
    }

    override suspend fun updateGroceriesList(groceriesList: GroceriesList, included: List<String>): GroceriesList {
        LogHandler.info("[Miam][MiamAPIDatasource] starting updateGroceriesList $groceriesList")
        val returnValue = httpClient.patch<RecordWrapper>{
                headers.append( HttpHeaders.ContentType, "application/vnd.api+json" )
                url(HttpRoutes.GROCERIESLIST_ENDPOINT + groceriesList.id + "?" + includedToString(included))
                body = RecordWrapper.fromRecord(groceriesList)
            }.toRecord() as GroceriesList
        LogHandler.info("[Miam][MiamAPIDatasource] end updateGroceriesList $groceriesList")
        return returnValue
    }

/////////////////////// POINT OF SALE ////////////////////////////////////////////////


    override suspend fun getPosFormExtId(extId: String, supplierId: Int): PointOfSale {
        // this filter is suppose to return a single result or nothing
        LogHandler.info("[Miam][MiamAPIDatasource] starting getPosFormExtId $extId $supplierId")
        val posList =  httpClient.get<PointOfSales>{
            url(HttpRoutes.POINTOFSALE_ENDPOINT+"?filter[ext-id]=$extId&filter[supplier-id]=$supplierId")
        }
        if(posList.data.isEmpty()) throw Exception("Point of sale not found or incorrect")
        LogHandler.info("[Miam][MiamAPIDatasource] end getPosFormExtId $extId $supplierId $posList")
        return posList.data[0]
    }

/////////////////////// BASKET ////////////////////////////////////////////////


    override suspend fun getFromListAndPos(listId: String, posId: Int, included: List<String>): Basket {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getFromListAndPos $listId $posId")
        val baskets = httpClient.get<RecordWrapper>{
            url(HttpRoutes.GROCERIESLIST_ENDPOINT+"$listId/baskets?filter[point_of_sale_id]=$posId&${includedToString(included)}")
        }.toRecords() as List<Basket>
        if(baskets.isEmpty()) throw Exception("basket not found or incorrect")
        LogHandler.info("[Miam][MiamAPIDatasource] end getFromListAndPos $listId $posId $baskets")
        return baskets[0]
    }

    override suspend fun updateBasket(basket: Basket, origin:String): Basket {
        LogHandler.info("[Miam][MiamAPIDatasource] starting updateBasket $basket")
        val returnValue = httpClient.patch<RecordWrapper>{
            headers.append(HttpHeaders.Origin, origin)
            headers.append( HttpHeaders.ContentType, "application/vnd.api+json" )
            url(HttpRoutes.BASKET_ENDPOINT+"${basket.id}")
            body = RecordWrapper.fromRecord(basket)
        }.toRecord() as Basket
        LogHandler.info("[Miam][MiamAPIDatasource] end updateBasket $basket $returnValue")
        return returnValue
    }

/////////////////////////////// PRICING ///////////////////////////////////////////////////

    override suspend fun getRecipePrice(idRecipe: String, idPos: Int): Pricing {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getRecipePrice $idRecipe $idPos")
        val returnValue = httpClient.get<Pricing> {
            url(HttpRoutes.RECIPE_ENDPOINT+"$idRecipe/pricing?point_of_sale_id=$idPos")
        }
        LogHandler.info("[Miam][MiamAPIDatasource] end getRecipePrice $idRecipe $idPos $returnValue")
        return returnValue
    }

////////////////////////////////// BASKET ENTRY ////////////////////////////////////////

    override suspend fun updateBasketEntry(basketEntry: BasketEntry, included: List<String>): BasketEntry {
        LogHandler.info("[Miam][MiamAPIDatasource] starting updateBasketEntry $basketEntry")
        val returnValue = httpClient.patch<RecordWrapper>{
            headers.append( HttpHeaders.ContentType, "application/vnd.api+json" )
            url(HttpRoutes.BASKET_ENTRIES_ENDPOINT+"/${basketEntry.id}?${includedToString(included)}")
            body = RecordWrapper.fromRecord(basketEntry)
        }.toRecord() as BasketEntry
        LogHandler.info("[Miam][MiamAPIDatasource] end updateBasketEntry $basketEntry $returnValue")
        return returnValue
    }

    ////////////////////////////////// GROCERY ENTRY ////////////////////////////////////////

    override suspend fun updateGroceriesEntry(ge: GroceriesEntry): GroceriesEntry {
        LogHandler.info("[Miam][MiamAPIDatasource] starting updateGroceriesEntry $ge")
        val returnValue = httpClient.patch<RecordWrapper>{
            headers.append( HttpHeaders.ContentType, "application/vnd.api+json" )
            url(HttpRoutes.GROCERIES_ENTRY_ENDPOINT+"/${ge.id}")
            body =  RecordWrapper.fromRecord((ge))
        }.toRecord() as GroceriesEntry
        LogHandler.info("[Miam][MiamAPIDatasource] end updateGroceriesEntry $ge $returnValue")
        return returnValue
    }

    /////////////////////////////////////SUPPLIER /////////////////////////////////////////////////


    override suspend fun notifyBasketUpdated(basketToken: String, supplierId: Int, status: String, price: String?) {
        LogHandler.info("[Miam][MiamAPIDatasource] starting notifyBasketUpdated $basketToken $supplierId $status")
        return httpClient.post {
            headers.append(HttpHeaders.ContentType, "application/vnd.api+json" )
            url("${HttpRoutes.SUPPLIER}$supplierId/webhooks/basket_updated")
            body = SupplierNotificationWrapper(basketToken,status, price)
        }
    }

    private fun includedToString(included: List<String>): String {
        return if (included.isEmpty()) "" else "include=" + included.joinToString(",")
    }

    private fun filtersToString(filters: Map<String, String>): String {
        return filters.toList().fold(""){ res, filter -> res + "filter[${filter.first}]=${filter.second}" }
    }

}
