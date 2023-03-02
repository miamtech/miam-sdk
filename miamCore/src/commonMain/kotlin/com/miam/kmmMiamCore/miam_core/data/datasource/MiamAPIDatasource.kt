package com.miam.kmmMiamCore.miam_core.data.datasource

import com.miam.core.sdk.di.MiamDI
import com.miam.kmmMiamCore.base.mvi.PointOfSaleStore
import com.miam.kmmMiamCore.base.mvi.UserStore
import com.miam.kmmMiamCore.handler.LogHandler
import com.miam.kmmMiamCore.miam_core.model.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.cache.*
import io.ktor.client.plugins.compression.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json


public object HttpRoutes {
    private const val BASE_URL = "https://api.miam.tech/api/v1"

    // private const val BASE_URL = "http://127.0.0.1:3000/api/v1"
    public const val RECIPE_ENDPOINT: String = "$BASE_URL/recipes/"
    public const val RECIPE_LIKE_ENDPOINT: String = "$BASE_URL/recipe-likes/"
    public const val GROCERIESLIST_ENDPOINT: String = "$BASE_URL/groceries-lists/"
    public const val GROCERIES_ENTRY_ENDPOINT: String = "$BASE_URL/groceries-entries"
    public const val POINTOFSALE_ENDPOINT: String = "$BASE_URL/point-of-sales/"
    public const val BASKET_ENDPOINT: String = "$BASE_URL/baskets/"
    public const val BASKET_ENTRIES_ENDPOINT: String = "$BASE_URL/basket-entries/"
    public const val RECIPE_SUGGESTIONS: String = "$BASE_URL/recipes/suggestions"
    public const val SUPPLIER: String = "$BASE_URL/suppliers/"
    public const val PACKAGE_ENDPOINT: String = "$BASE_URL/packages"
    public const val TAGS_ENDPOINT: String = "$BASE_URL/tags"
    public const val SPONSOR_ENDPOINT: String = "$BASE_URL/sponsors"
    public const val SPONSOR_BLOCK_ENDPOINT: String = "$BASE_URL/sponsor-blocks"
}


public class MiamAPIDatasource: RecipeDataSource, GroceriesListDataSource, PointOfSaleDataSource,
    BasketDataSource, PricingDataSource, BasketEntryDataSource, GrocerieEntryDataSource,
    SupplierDataSource, PackageDataSource, TagDataSource, SponsorDataSource, SponsorBlockDataSource {

    // TODO By lazy allows cyclic dependencies, even if it is bad design
    private val userStore: UserStore by lazy { MiamDI.userStore }
    private val pointOfSaleStore: PointOfSaleStore by lazy { MiamDI.pointOfSaleStore }

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    isLenient = true
                    ignoreUnknownKeys = true // if the server sends extra fields, ignore them
                }
            )
        }
        install(HttpCache)
        /*install(ContentEncoding) {
            gzip(0.9F)
            deflate(1.0F)
        }*/
    }

    init {
        httpClient.receivePipeline.intercept(HttpReceivePipeline.State) { response ->
            if (userStore.getSessionId() == null) {
                val newSessionId = "${response.headers["set-cookie"]}".split(';')[0]
                if (userStore.sameSession(newSessionId)) return@intercept

                userStore.setSessionId(newSessionId)
            }
        }

        httpClient.sendPipeline.intercept(HttpSendPipeline.State) {
            context.headers.append(HttpHeaders.Accept, "*/*")
            userStore.observeState().value.sessionId.let {
                context.headers.remove("Cookie")
                if (it != null) {
                    context.headers.append(HttpHeaders.Cookie, it)
                }
            }
            userStore.observeState().value.userId.let {
                context.headers.append(HttpHeaders.Authorization, "user_id $it")
            }
            context.headers.append(HttpHeaders.Origin, pointOfSaleStore.getProviderOrigin())
            if (userStore.ProfilingForbiden()) {
                context.url.parameters["profiling"] = "off"
            }
        }
    }

    private suspend inline fun <reified T> get(url: String): T? {
        return try {
            httpClient.get(url) {
                method = HttpMethod.Get
                headers {
                    append(HttpHeaders.ContentType, "application/vnd.api+json")
                    append(HttpHeaders.Accept, "*/*")
                }
            }.body<T>()
        } catch (e: RedirectResponseException) {
            // 3XX
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ClientRequestException) {
            // 4xx
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ServerResponseException) {
            // 5xx
            println("Error: ${e.response.status.description}")
            throw e
        } catch (e: Exception) {
            println("Error: ${e.message}")
            throw e
        }
    }

    private suspend inline fun <reified T> post(url: String, data: Any): T? {
        return try {
            httpClient.post(url) {
                headers {
                    append(HttpHeaders.ContentType, "application/vnd.api+json")
                    append(HttpHeaders.Accept, "*/*")
                }
                setBody(data)
            }.body<T>()
        } catch (e: RedirectResponseException) {
            // 3XX
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ClientRequestException) {
            // 4xx
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ServerResponseException) {
            // 5xx
            println("Error: ${e.response.status.description}")
            null
        } catch (e: Exception) {
            println("Error: ${e.message}")
            null
        }
    }

    override suspend fun getRecipeById(id: String, included: List<String>): Recipe {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getRecipeById $id")
        val returnValue = this.get<RecordWrapper>(
            HttpRoutes.RECIPE_ENDPOINT + id + "?" + includedToString(included)
        )!!.toRecord() as Recipe
        LogHandler.info("[Miam][MiamAPIDatasource] end getRecipeById $id")
        return returnValue
    }

    private suspend fun getRecipeByIdsChunck(
        recipesIds: List<String>,
        included: List<String>,
        pageSize: Int
    ): List<Recipe> {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getRecipeByIdsChunck $recipesIds")
        val idFilters = "page[size]=$pageSize&filter[id]=${recipesIds.joinToString(",")}"
        val returnValue = this.get<RecordWrapper>(
            HttpRoutes.RECIPE_ENDPOINT + "?$idFilters&" + includedToString(included)
        )!!.toRecords()
        LogHandler.info("[Miam][MiamAPIDatasource] end getRecipeById $recipesIds ")
        return returnValue.map { record -> record as Recipe }
    }

    override suspend fun getRecipeByIds(
        recipesIds: List<String>,
        included: List<String>,
        pageSize: Int
    ): List<Recipe> {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getRecipeByIds $recipesIds")
        val returnValue: MutableList<Recipe> = mutableListOf()
        recipesIds.chunked(pageSize).forEach { recipesIdsChunck ->
            returnValue.addAll(getRecipeByIdsChunck(recipesIdsChunck, included, pageSize))
        }
        LogHandler.info("[Miam][MiamAPIDatasource] end getRecipes $recipesIds")
        return returnValue
    }

    override suspend fun getRecipes(
        filters: Map<String, String>,
        included: List<String>,
        pageSize: Int,
        pageNumber: Int
    ): List<Recipe> {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getRecipes $filters $included")
        val pageFilter = "page[size]=$pageSize&page[number]=$pageNumber"
        val includedStr = if (included.isEmpty()) "" else "&${includedToString(included)}"
        val filtersStr = if (filters.isEmpty()) "" else "&${filtersToString(filters)}"
        val returnValue =
            this.get<RecordWrapper>(HttpRoutes.RECIPE_ENDPOINT + "?$pageFilter$includedStr$filtersStr")!!
                .toRecords()
        LogHandler.info("[Miam][MiamAPIDatasource] end getRecipes $returnValue")
        return returnValue.map { record -> record as Recipe }
    }

    override suspend fun getRecipesFromStringFilter(
        filters: String,
        included: List<String>,
        pageSize: Int,
        pageNumber: Int
    ): List<Recipe> {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getRecipes $filters $included")
        val pageFilter = "page[size]=$pageSize&page[number]=$pageNumber"
        val includedStr = if (included.isEmpty()) "" else "&${includedToString(included)}"
        val filtersStr = if (filters.isEmpty()) "" else "&${filters}"
        val returnValue =
            this.get<RecordWrapper>(HttpRoutes.RECIPE_ENDPOINT + "?$pageFilter$includedStr$filtersStr")!!
                .toRecords()
        val recipeList = returnValue.map { record -> record as Recipe }
        LogHandler.info("[Miam][MiamAPIDatasource] end getRecipes ${recipeList.map { recipe -> "${recipe.id} / ${recipe.attributes?.title} " }}")
        return recipeList
    }

    override suspend fun getRecipeSuggestions(
        supplierId: Int,
        size: Int?,
        criteria: SuggestionsCriteria,
        included: List<String>
    ): List<Recipe> {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getRecipeSuggestions $criteria")
        val url = "${HttpRoutes.RECIPE_SUGGESTIONS}?supplier_id=${supplierId}&page[size]=${size}&${includedToString(included)}"
        val returnValue = this.post<RecordWrapper>(url, criteria)!!.toRecords()
        LogHandler.info("[Miam][MiamAPIDatasource] end getRecipeSuggestions $criteria ${returnValue.map { record -> { "${record.id}/ ${(record as Recipe).attributes?.title}" } }}")
        return returnValue.map { record -> record as Recipe }
    }

    override suspend fun getRecipeNumberOfResult(filter: String): Int {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getRecipeNumberOfResult $filter&page[size]=1")
        val returnValue =
            this.get<RecordCounterWrapper>(HttpRoutes.RECIPE_ENDPOINT + "?$filter&page[size]=1")!!
        LogHandler.info("[Miam][MiamAPIDatasource] end getRecipeNumberOfResult ${returnValue.getCount()}")
        return returnValue.getCount()
    }

    ///////// RecipeLike ///////////////

    private suspend fun getRecipeLikesByRecipeIds(
        recipesIds: List<String>,
        pageSize: Int
    ): List<RecipeLike> {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getRecipeLikesByRecipeIds $recipesIds")
        val pageFilter = "page[size]=$pageSize"
        val idFilters =
            "filter[recipe_id]=${recipesIds.joinToString(",")}&filter[is_past]=true,false"
        val returnValue =
            this.get<RecordWrapper>(HttpRoutes.RECIPE_LIKE_ENDPOINT + "?$pageFilter&$idFilters")!!
                .toRecords()
        LogHandler.info("[Miam][MiamAPIDatasource] end getRecipeLikesByRecipeIds ${returnValue.map { record -> "${(record as RecipeLike).attributes?.recipeId} / ${record.attributes?.isPast}" }}")
        return returnValue.map { record -> record as RecipeLike }
    }

    override suspend fun getRecipeLikes(recipesIds: List<String>, pageSize: Int): List<RecipeLike> {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getRecipeLikes $recipesIds")
        val returnValue: MutableList<RecipeLike> = mutableListOf()
        recipesIds.chunked(pageSize).forEach { recipesIdsChunck ->
            returnValue.addAll(getRecipeLikesByRecipeIds(recipesIdsChunck, pageSize))
        }
        LogHandler.info("[Miam][MiamAPIDatasource] end getRecipeLikes ${returnValue.map { record -> "${record.attributes?.recipeId} / ${record.attributes?.isPast}" }}")
        return returnValue
    }

    override suspend fun createRecipeLike(recipeLike: RecipeLike): RecipeLike {
        LogHandler.info(
            "[Miam][MiamAPIDatasource] starting createRecipeLike ${
                RecordWrapper.fromRecord(
                    recipeLike
                )
            }"
        )
        val returnValue = httpClient.post {
            headers.append(HttpHeaders.ContentType, "application/vnd.api+json")
            url(HttpRoutes.RECIPE_LIKE_ENDPOINT)
            setBody(RecordWrapper.fromRecord(recipeLike))
        }.body<RecordWrapper>().toRecord()
        LogHandler.info("[Miam][MiamAPIDatasource] end createRecipeLike $returnValue")
        return returnValue as RecipeLike
    }

    override suspend fun updateRecipeLike(recipeLike: RecipeLike): RecipeLike {
        LogHandler.info("[Miam][MiamAPIDatasource] starting updateRecipeLike $recipeLike")
        val returnValue = httpClient.patch {
            headers.append(HttpHeaders.ContentType, "application/vnd.api+json")
            url("${HttpRoutes.RECIPE_LIKE_ENDPOINT}${recipeLike.id}")
            setBody(RecordWrapper.fromRecord(recipeLike))
        }.body<RecordWrapper>().toRecord()
        LogHandler.info("[Miam][MiamAPIDatasource] end updateRecipeLike $returnValue")
        return returnValue as RecipeLike
    }

    ///////// GroceriesList ///////////////

    override suspend fun getCurrent(included: List<String>): GroceriesList {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getCurrent")
        val returnValue = httpClient.get {
            url(HttpRoutes.GROCERIESLIST_ENDPOINT + "current?" + includedToString(included))
        }.body<RecordWrapper>().toRecord() as GroceriesList
        LogHandler.info("[Miam][MiamAPIDatasource] end getCurrent")
        return returnValue
    }

    override suspend fun reset(): GroceriesList {
        LogHandler.info("[Miam][MiamAPIDatasource] starting reset")
        val returnValue = httpClient.get {
            url(HttpRoutes.GROCERIESLIST_ENDPOINT + "reset")
        }.body<RecordWrapper>().toRecord() as GroceriesList
        LogHandler.info("[Miam][MiamAPIDatasource] end reset")
        return returnValue
    }

    override suspend fun updateGroceriesList(
        groceriesList: GroceriesList,
        included: List<String>
    ): GroceriesList {
        LogHandler.info("[Miam][MiamAPIDatasource] starting updateGroceriesList $groceriesList")
        val returnValue = httpClient.patch {
            headers.append(HttpHeaders.ContentType, "application/vnd.api+json")
            url(
                HttpRoutes.GROCERIESLIST_ENDPOINT + groceriesList.id + "?" + includedToString(
                    included
                )
            )
            setBody(RecordWrapper.fromRecord(groceriesList))
        }.body<RecordWrapper>().toRecord() as GroceriesList
        LogHandler.info("[Miam][MiamAPIDatasource] end updateGroceriesList $groceriesList")
        return returnValue
    }

/////////////////////// POINT OF SALE ////////////////////////////////////////////////

    override suspend fun getPosFormExtId(extId: String, supplierId: Int): PointOfSale? {
        // this filter is suppose to return a single result or nothing
        LogHandler.info("[Miam][MiamAPIDatasource] starting getPosFormExtId $extId $supplierId")
        val posList = httpClient.get {
            url(HttpRoutes.POINTOFSALE_ENDPOINT + "?filter[ext-id]=$extId&filter[supplier-id]=$supplierId")
        }.body<PointOfSales>()
        if (posList.data.isEmpty()) {
            LogHandler.error("Point of sale not found or incorrect")
            return null
        }
        LogHandler.info("[Miam][MiamAPIDatasource] end getPosFormExtId $extId $supplierId ${posList.data.map { pos -> "${pos.id}" }}")
        return posList.data[0]
    }

/////////////////////// BASKET ////////////////////////////////////////////////

    @Suppress("unchecked_cast")
    override suspend fun getFromListAndPos(listId: String, posId: Int, included: List<String>): Basket? {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getFromListAndPos $listId $posId")
        val baskets = httpClient.get {
            url(HttpRoutes.GROCERIESLIST_ENDPOINT + "$listId/baskets?filter[point_of_sale_id]=$posId&${includedToString(included)}")
        }.body<RecordWrapper>().toRecords()
            .filterIsInstance<Basket>()
        if (baskets.isEmpty()) {
            LogHandler.error("basket not found or incorrect")
            return null
        }
        LogHandler.info("[Miam][MiamAPIDatasource] end getFromListAndPos $listId $posId $baskets")
        return baskets[0]
    }

    override suspend fun updateBasket(basket: Basket): Basket {
        LogHandler.info("[Miam][MiamAPIDatasource] starting updateBasket $basket")
        val returnValue = httpClient.patch {
            headers.append(HttpHeaders.ContentType, "application/vnd.api+json")
            url(HttpRoutes.BASKET_ENDPOINT + basket.id)
            setBody(RecordWrapper.fromRecord(basket))
        }.body<RecordWrapper>().toRecord() as Basket
        LogHandler.info("[Miam][MiamAPIDatasource] end updateBasket $basket $returnValue")
        return returnValue
    }

/////////////////////////////// PRICING ///////////////////////////////////////////////////

    override suspend fun getRecipePrice(idRecipe: String, idPos: Int, serves: Int?): Pricing {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getRecipePrice $idRecipe $idPos")
        var params = "point_of_sale_id=$idPos"
        serves?.let { params = "$params&serves=$serves" }
        val returnValue = httpClient.get {
            url(HttpRoutes.RECIPE_ENDPOINT + "$idRecipe/pricing?$params")
        }.body<Pricing>()
        LogHandler.info("[Miam][MiamAPIDatasource] end getRecipePrice $idRecipe $idPos $returnValue")
        return returnValue
    }

////////////////////////////////// BASKET ENTRY ////////////////////////////////////////

    override suspend fun updateBasketEntry(
        basketEntry: BasketEntry,
        included: List<String>
    ): BasketEntry {
        LogHandler.info("[Miam][MiamAPIDatasource] starting updateBasketEntry $basketEntry")
        val returnValue = httpClient.patch {
            headers.append(HttpHeaders.ContentType, "application/vnd.api+json")
            url(HttpRoutes.BASKET_ENTRIES_ENDPOINT + "/${basketEntry.id}?${includedToString(included)}")
            setBody(RecordWrapper.fromRecord(basketEntry))
        }.body<RecordWrapper>().toRecord() as BasketEntry
        LogHandler.info("[Miam][MiamAPIDatasource] end updateBasketEntry $basketEntry $returnValue")
        return returnValue
    }

    ////////////////////////////////// GROCERY ENTRY ////////////////////////////////////////

    override suspend fun updateGroceriesEntry(ge: GroceriesEntry): GroceriesEntry {
        LogHandler.info("[Miam][MiamAPIDatasource] starting updateGroceriesEntry $ge")
        val returnValue = httpClient.patch {
            headers.append(HttpHeaders.ContentType, "application/vnd.api+json")
            url(HttpRoutes.GROCERIES_ENTRY_ENDPOINT + "/${ge.id}")
            setBody(RecordWrapper.fromRecord((ge)))
        }.body<RecordWrapper>().toRecord() as GroceriesEntry
        LogHandler.info("[Miam][MiamAPIDatasource] end updateGroceriesEntry ${ge.attributes?.name}/ ${returnValue.attributes?.status}/  ${returnValue.attributes?.recipeIds}")
        return returnValue
    }

    /////////////////////////////////////SUPPLIER /////////////////////////////////////////////////

    override suspend fun notifyBasketUpdated(
        basketToken: String,
        supplierId: Int,
        status: String,
        price: String?
    ) {
        LogHandler.info("[Miam][MiamAPIDatasource] starting notifyBasketUpdated $basketToken $supplierId $status")
        httpClient.post {
            headers.append(HttpHeaders.ContentType, "application/vnd.api+json")
            url("${HttpRoutes.SUPPLIER}$supplierId/webhooks/basket_updated")
            setBody(SupplierNotificationWrapper(basketToken, status, price))
        }
    }

    override suspend fun getSupplier(supplierId: Int): Supplier {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getSupplier $supplierId ")

        val returnValue = httpClient.get {
            url(HttpRoutes.SUPPLIER + "$supplierId")
        }.body<RecordWrapper>().toRecord() as Supplier
        LogHandler.info("[Miam][MiamAPIDatasource] end getSupplier $supplierId $returnValue")
        return returnValue
    }

    private fun includedToString(included: List<String>): String {
        return if (included.isEmpty()) "" else "include=" + included.joinToString(",")
    }

    private fun filtersToString(filters: Map<String, String>): String {
        return filters.toList().joinToString("&") { filter -> "filter[${filter.first}]=${filter.second}" }
    }

    ///////////////////////////////////// PACKAGE /////////////////////////////////////////////////

    override suspend fun getActivePackagesFromSupplierID(supplierId: String, included: List<String>): List<Package> {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getActivePackagesFromSupplierID $supplierId")
        val params = "filter[category_for]=$supplierId&[status]=4&[user_preferences]=true&sort=catalog_position&${includedToString(included)}"
        val returnValue = httpClient.get {
            url("${HttpRoutes.PACKAGE_ENDPOINT}?$params")
        }.body<RecordWrapper>().toRecords()
        val packageList = returnValue.map { record -> record as Package }
        LogHandler.info("[Miam][MiamAPIDatasource] end getActivePackagesFromSupplierID ${packageList.map { it.attributes?.title }}")
        return packageList
    }

    ///////////////////////////////////// TAG /////////////////////////////////////////////////

    override suspend fun autocompleteTag(searchStr: String): List<Tag> {
        LogHandler.info("[Miam][MiamAPIDatasource] starting autocompleteTag $searchStr")
        val returnValue = httpClient.get {
            url(HttpRoutes.TAGS_ENDPOINT + "/autocomplete/$searchStr")
        }.body<RecordWrapper>().toRecords()
        LogHandler.info("[Miam][MiamAPIDatasource] end autocompleteTag ")
        return returnValue.map { record -> record as Tag }
    }

    override suspend fun getTagById(id: String): Tag {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getTagById $id")
        val returnValue = httpClient.get {
            url(HttpRoutes.TAGS_ENDPOINT + "/$id")
        }.body<RecordWrapper>().toRecord()
        LogHandler.info("[Miam][MiamAPIDatasource] end getTagById ")
        return returnValue as Tag
    }

    override suspend fun getTags(filters: Map<String, String>): List<Tag> {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getTags")
        val returnValue = httpClient.get {
            url(HttpRoutes.TAGS_ENDPOINT + "?${filtersToString(filters)}")
        }.body<RecordWrapper>().toRecords()
        LogHandler.info("[Miam][MiamAPIDatasource] end ${returnValue.map { record -> "${(record as Tag).attributes?.name}" }}")
        return returnValue.map { record -> record as Tag }
    }

    ///////////////////////////////////// Sponsor /////////////////////////////////////////////////

    override suspend fun getSponsorById(sponsorId: String, included: List<String>): Sponsor {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getSponsorById")
        val returnValue = httpClient.get {
            url(HttpRoutes.SPONSOR_ENDPOINT + "/$sponsorId?${includedToString(included)}")
        }.body<RecordWrapper>().toRecord()
        LogHandler.info("[Miam][MiamAPIDatasource] end getSponsorById ")
        return returnValue as Sponsor
    }

    ///////////////////////////////////// Sponsor block /////////////////////////////////////////////////

    override suspend fun getSponsorBlocksBySponsorId(sponsorId: String, included: List<String>): List<SponsorBlock> {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getSponsorBlockBySponsorId")
        val returnValue = httpClient.get {
            url(HttpRoutes.SPONSOR_BLOCK_ENDPOINT + "?filter[sponsor-id]=${sponsorId}&${includedToString(included)}")
        }.body<RecordWrapper>().toRecords()
        LogHandler.info("[Miam][MiamAPIDatasource] end ${returnValue.map { record -> "${(record as SponsorBlock).id}" }}")
        return returnValue.map { record -> record as SponsorBlock }
    }

}
