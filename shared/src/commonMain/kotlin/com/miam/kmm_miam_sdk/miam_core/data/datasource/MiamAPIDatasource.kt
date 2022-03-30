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

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class MiamResponseException(response: HttpResponse, cachedResponseText: String) :
    ResponseException(response, cachedResponseText) {
    override val message: String = "Custom server error: ${response.call.request.url}. " +
            "Status: ${response.status}. Text: \"$cachedResponseText\""
}


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
    const val GROCERIES_ENTRY_ENDPOINT = "$BASE_URL/groceries-entries"
    const val POINTOFSALE_ENDPOINT = "$BASE_URL/point-of-sales/"
    const val BASKET_ENDPOINT = "$BASE_URL/baskets/"
    const val BASKET_ENTRIES_ENDPOINT = "$BASE_URL/basket-entries/"
    const val RECIPE_SUGGESTIONS= "$BASE_URL/recipes/suggestions"
    const val SUPPLIER = "$BASE_URL/suppliers/"
}

@OptIn(InternalAPI::class)
class MiamAPIDatasource: RecipeDataSource ,GroceriesListDataSource, PointOfSaleDataSource,BasketDataSource,PricingDataSource,BasketEntryDataSource, GrocerieEntryDataSource,SupplierDataSource, KoinComponent {

    private val userStore: UserStore by inject()
    private var sessionId : String? = null

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
        install(Logging){
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }

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

    override suspend fun getIngredient(entityId: Int): Ingredients {
        return this.get<Ingredients>(HttpRoutes.INGREDIENT_ENDPOINT+"${entityId}/ingredients")!!
    }

    override suspend fun getProvider(entityId: Int): RecipeProvider {
        return httpClient.get<RecipeProviderWrapper>{
            url(HttpRoutes.PROVIDER_ENDPOINT+"${entityId}/recipe-provider")
        }.data
    }

    override suspend fun getRecipeById(id: Int): Recipe {
        return this.get<RecipeWrapper>(HttpRoutes.RECIPE_ENDPOINT + "$id")!!.data
    }

    override suspend fun getRecipeSuggestions(
        supplierId: Int,
        criteria: SuggestionsCriteria
    ): List<Recipe> {
        return this.post<RecipeListWrapper>("${HttpRoutes.RECIPE_SUGGESTIONS}?supplier_id=${supplierId}",criteria)!!.data
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

    override suspend fun updateGroceriesList(groceriesList: GroceriesListWithoutRelationship): GroceriesList {
          return  httpClient.patch<GroceriesListWrapper>{
                headers.append( HttpHeaders.ContentType, "application/vnd.api+json" )
                url(HttpRoutes.GROCERIESLIST_ENDPOINT+"${groceriesList.id}")
                body = GroceriesListWithoutRelationshipWrapper(groceriesList)
            }.data
    }

    override suspend fun getRecipes(recipesInfos: List<RecipeInfos>): List<Recipe> {
        return recipesInfos.map{ ri -> getRecipeById(ri.id) }
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

        return httpClient.get<BasketEntries> {
            url(HttpRoutes.BASKET_ENDPOINT + "$basketId/basket-entries?include=groceries-entry")
        }
        BasketEntries( emptyList<BasketEntry>())
    }

    override suspend fun getBasketEntriesbyPages(basketId: Int, pageIndex: Int, pagesize: Int): List<BasketEntry> {
       val baseUrl = HttpRoutes.BASKET_ENDPOINT+"$basketId/basket-entries?include=groceries-entry"
       val pageUrl = "$baseUrl&page[number]=$pageIndex&page[size]=$pagesize"

        return  httpClient.get<BasketEntries>{
            url(pageUrl)
        }.basketEntries

    }

    override suspend fun updateBasket(basket: Basket, origin:String): Basket {
        return  httpClient.patch<BasketWrapper>{
            headers.append(HttpHeaders.Origin, origin)
            headers.append( HttpHeaders.ContentType, "application/vnd.api+json" )
            url(HttpRoutes.BASKET_ENDPOINT+"${basket.id}")
            body = BasketWrapper(basket.copy(type = "baskets"))
        }.data
    }

/////////////////////////////// PRICING ///////////////////////////////////////////////////

    override suspend fun getRecipePrice(idRecipe: Int, idPos: Int): Pricing {
        return  httpClient.get{
            url(HttpRoutes.RECIPE_ENDPOINT+"$idRecipe/pricing?point_of_sale_id=$idPos")
        }
    }

////////////////////////////////// BASKET ENTRY ////////////////////////////////////////

    override suspend fun getBasketEntryItems(basketEntryId: Int): List<Item> {
            return  this.get<Items>("${HttpRoutes.BASKET_ENTRIES_ENDPOINT}$basketEntryId/items?page[size]=30")?.data ?: emptyList()
    }

    override suspend fun getBasketEntryGrocerieEntry(groceriesEntryId: Int): GroceriesEntry? {
        return  this.get<GroceriesEntryWrapper>(
            HttpRoutes.GROCERIES_ENTRY_ENDPOINT+"/$groceriesEntryId"
        )?.data
    }

    override suspend fun updateBasketEntry(basketEntry: BasketEntry): BasketEntry {
        // println("Miam datasource updateBasketEntry $basketEntry")
        return  httpClient.patch<BasketEntryWrapper>{
            headers.append( HttpHeaders.ContentType, "application/vnd.api+json" )
            url(HttpRoutes.BASKET_ENTRIES_ENDPOINT+"/${basketEntry.id}?include=groceries-entry")
            body = BasketEntryUpdateWrapper(BasketEntryUpdate(basketEntry.id, "basket-entries",basketEntry.attributes))
        }.data
    }

    ////////////////////////////////// GROCERY ENTRY ////////////////////////////////////////

    override suspend fun updateGroceriesEntry(ge: GroceriesEntry): GroceriesEntry {
        // println("Miam datasource updateGroceriesEntry $ge")
        return  httpClient.patch<GroceriesEntryWrapper>{
         headers.append( HttpHeaders.ContentType, "application/vnd.api+json" )
         url(HttpRoutes.GROCERIES_ENTRY_ENDPOINT+"/${ge.id}")
         body =  GroceriesEntryUpdateWrapper( GroceriesEntryUpdate(ge.id,  "groceries-entries",ge.attributes) )
     }.data
    }

    /////////////////////////////////////SUPPLIER /////////////////////////////////////////////////


    override suspend fun notifyBasketUpdated(basketToken: String, supplierId: Int, status: String, price: String?) {
        return  httpClient.post{
            headers.append( HttpHeaders.ContentType, "application/vnd.api+json" )
            url("${HttpRoutes.SUPPLIER}$supplierId/webhooks/basket_updated")
            body = SupplierNotificationWrapper(basketToken,status, price)
        }
    }
}
