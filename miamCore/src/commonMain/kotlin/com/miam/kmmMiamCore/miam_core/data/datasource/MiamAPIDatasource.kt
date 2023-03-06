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
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.*
import kotlinx.serialization.json.Json



private enum class MiamAPIEndpoint(val path: String) {
    RECIPE("recipes"),
    RECIPE_LIKE("recipe-like"),
    GROCERIESLIST("groceries-lists"),
    GROCERIES_ENTRY("groceries-entries"),
    POINTOFSALE("point-of-sales"),
    BASKET("baskets"),
    BASKET_ENTRIES("basket-entries"),
    RECIPE_SUGGESTIONS("recipes/suggestions"),
    SUPPLIER("suppliers"),
    PACKAGE("packages"),
    TAGS("tags"),
    SPONSOR("sponsors"),
    SPONSOR_BLOCK("sponsor-blocks")
}

public class MiamAPIDatasource: RecipeDataSource, GroceriesListDataSource, PointOfSaleDataSource,
    BasketDataSource, PricingDataSource, BasketEntryDataSource, GrocerieEntryDataSource,
    SupplierDataSource, PackageDataSource, TagDataSource, SponsorDataSource, SponsorBlockDataSource {

    // TODO By lazy allows cyclic dependencies, even if it is bad design
    private val userStore: UserStore by lazy { MiamDI.userStore }
    private val pointOfSaleStore: PointOfSaleStore by lazy { MiamDI.pointOfSaleStore }
    private val baseURL = "https://api.miam.tech/api/v1/"
    private val httpClient = HttpClient {
        install(DefaultRequest)

        defaultRequest {
            headers.append(HttpHeaders.ContentType, "application/vnd.api+json")
            headers.append(HttpHeaders.Accept, "*/*")
        }

        // Configure response validation
        expectSuccess = true

        install(ContentNegotiation) {
            json(
                Json {
                    isLenient = true
                    ignoreUnknownKeys = true // if the server sends extra fields, ignore them
                }
            )
        }
        install(HttpCache)
        install(Logging) {
            LogLevel.ALL
        }
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

    private suspend inline fun <reified T> get(url: String): T {
        return try {
            httpClient.get(url) {
                method = HttpMethod.Get
                headers {
//                    append(HttpHeaders.ContentType, "application/vnd.api+json")
                    append(HttpHeaders.Accept, "*/*")
                }
            }.body<T>()
        } catch (e: RedirectResponseException) {
            // 3XX
            println("Error: ${e.response.status.description}")
            throw e
        } catch (e: ClientRequestException) {
            // 4xx
            println("Error: ${e.response.status.description}")
            throw e
        } catch (e: ServerResponseException) {
            // 5xx
            println("Error: ${e.response.status.description}")
            throw e
        } catch (e: Exception) {
            println("Error: ${e.message}")
            throw e
        }
    }

    private suspend inline fun <reified T> post(url: String, data: Any): T {
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
            throw e
        } catch (e: ClientRequestException) {
            // 4xx
            println("Error: ${e.response.status.description}")
            throw e
        } catch (e: ServerResponseException) {
            // 5xx
            println("Error: ${e.response.status.description}")
            throw e
        } catch (e: Exception) {
            println("Error: ${e.message}")
            throw e
        }
    }

    override suspend fun getRecipeById(id: String, included: List<String>): Recipe {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getRecipeById $id")
        val url = URLBuilder(baseURL).run {
            appendPathSegments(MiamAPIEndpoint.RECIPE.path, id)
            val recipeAttributes = RecipeAttributeName.values().map { it.attributeName }.joinToString(",")
            parameters.append("fields[recipe]", recipeAttributes)
            parameters.append("include", included.joinToString(","))
            buildString()
        }
        val returnValue = this.get<RecordWrapper>(url).toRecord() as Recipe
        LogHandler.info("[Miam][MiamAPIDatasource] end getRecipeById $id")
        return returnValue
    }

    private suspend fun getRecipeByIdsChunck(
        recipesIds: List<String>,
        included: List<String>,
        pageSize: Int
    ): List<Recipe> {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getRecipeByIdsChunck $recipesIds")
        val url = URLBuilder(baseURL).run {
            appendPathSegments(MiamAPIEndpoint.RECIPE.path)
            parameters.append("page[size]", pageSize.toString())
            parameters.append("filter[id]", recipesIds.joinToString(","))
            parameters.append("include", included.joinToString(","))
            buildString()
        }

        val returnValue = this.get<RecordWrapper>(url).toRecords()
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
        included: Array<RecipeRelationshipName>,
        pageSize: Int,
        pageNumber: Int
    ): List<Recipe> {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getRecipes $filters $included")
        val url = URLBuilder(baseURL).run {
            appendPathSegments(MiamAPIEndpoint.RECIPE.path)
            val recipeAttributes = RecipeAttributeName.recipeCardAttributes().map { it.attributeName }.joinToString(",")
            parameters.append("page[size]", pageSize.toString())
            parameters.append("page[number]", pageNumber.toString())
            parameters.append("fields[${MiamAPIEndpoint.RECIPE.path}]", recipeAttributes)
            parameters.append("include", included.joinToString(","))
            filters.forEach {
                parameters.append("filter[${it.key}]", it.value)
            }
            buildString()
        }

        val recipesListWrapper = this.get<RecipesListWrapper>(url)
        LogHandler.info("[Miam][MiamAPIDatasource] end getRecipes $recipesListWrapper")
        return recipesListWrapper.data
    }

    override suspend fun getRecipesFromStringFilter(
        filters: String,
        included: List<String>,
        pageSize: Int,
        pageNumber: Int
    ): List<Recipe> {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getRecipes $filters $included")
        val url = URLBuilder(baseURL).run {
            appendPathSegments(MiamAPIEndpoint.RECIPE.path)
            val recipeAttributes = RecipeAttributeName.recipeCardAttributes().map { it.attributeName }.joinToString(",")
            parameters.append("page[size]", pageSize.toString())
            parameters.append("page[number]", pageNumber.toString())
            parameters.append("fields[${MiamAPIEndpoint.RECIPE.path}]", recipeAttributes)
            parameters.append("include", included.joinToString(","))
            buildString()
        }

        val returnValue = this.get<RecordWrapper>(url).toRecords()
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

        val url = URLBuilder(baseURL).run {
            appendPathSegments(MiamAPIEndpoint.RECIPE_SUGGESTIONS.path)
            parameters.append("supplier_id", supplierId.toString())
            parameters.append("page[size]", size.toString())
            parameters.append("include", included.joinToString(","))
            buildString()
        }
            //"${HttpRoutes.RECIPE_SUGGESTIONS}?supplier_id=${supplierId}&page[size]=${size}&${includedToString(included)}"
        val returnValue = this.post<RecordWrapper>(url, criteria).toRecords()
        LogHandler.info("[Miam][MiamAPIDatasource] end getRecipeSuggestions $criteria ${returnValue.map { record -> { "${record.id}/ ${(record as Recipe).attributes?.title}" } }}")
        return returnValue.map { record -> record as Recipe }
    }

    override suspend fun getRecipeNumberOfResult(filters: Map<String, String>): Int {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getRecipeNumberOfResult")
        val url = URLBuilder(baseURL).run {
            appendPathSegments(MiamAPIEndpoint.RECIPE.path)
            parameters.append("page[size]", 1.toString())
            filters.forEach {
                parameters.append("filter[${it.key}]", it.value)
            }
            buildString()
        }
        val returnValue = this.get<RecordCounterWrapper>(url)
        LogHandler.info("[Miam][MiamAPIDatasource] end getRecipeNumberOfResult ${returnValue.getCount()}")
        return returnValue.getCount()
    }

    ///////// RecipeLike ///////////////

    private suspend fun getRecipeLikesByRecipeIds(
        recipesIds: List<String>,
        pageSize: Int
    ): List<RecipeLike> {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getRecipeLikesByRecipeIds $recipesIds")
        val url = URLBuilder(baseURL).run {
            appendPathSegments(MiamAPIEndpoint.RECIPE.path)
            parameters.append("page[size]", pageSize.toString())
            parameters.append("filter[recipe_id]", recipesIds.joinToString(","))
            parameters.append("filter[is_past]", "true,false")
            buildString()
        }
        val returnValue = this.get<RecordWrapper>(url).toRecords()
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
            url {
                appendPathSegments(MiamAPIEndpoint.RECIPE_LIKE.path)
            }
            setBody(RecordWrapper.fromRecord(recipeLike))
        }.body<RecordWrapper>().toRecord()
        LogHandler.info("[Miam][MiamAPIDatasource] end createRecipeLike $returnValue")
        return returnValue as RecipeLike
    }

    override suspend fun updateRecipeLike(recipeLike: RecipeLike): RecipeLike {
        if (recipeLike.id == null) {
            throw NullPointerException("missing recipe like ID.")
        }

        LogHandler.info("[Miam][MiamAPIDatasource] starting updateRecipeLike $recipeLike")
        val returnValue = httpClient.patch {
            url {
                appendPathSegments(MiamAPIEndpoint.RECIPE_LIKE.path, recipeLike.id)
            }
            setBody(RecordWrapper.fromRecord(recipeLike))
        }.body<RecordWrapper>().toRecord()
        LogHandler.info("[Miam][MiamAPIDatasource] end updateRecipeLike $returnValue")
        return returnValue as RecipeLike
    }

    ///////// GroceriesList ///////////////

    override suspend fun getCurrent(included: List<String>): GroceriesList {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getCurrent")
        val url = URLBuilder(baseURL).run {
            appendPathSegments(MiamAPIEndpoint.GROCERIESLIST.path, "current")
            parameters.append("include", included.joinToString(","))
            buildString()
        }
        val returnValue = get<RecordWrapper>(url).toRecord() as GroceriesList
        LogHandler.info("[Miam][MiamAPIDatasource] end getCurrent")
        return returnValue
    }

    override suspend fun reset(): GroceriesList {
        LogHandler.info("[Miam][MiamAPIDatasource] starting reset")
        val url = URLBuilder(baseURL).run {
            appendPathSegments(MiamAPIEndpoint.GROCERIESLIST.path, "reset")
            buildString()
        }
        val returnValue = get<RecordWrapper>(url).toRecord() as GroceriesList
        LogHandler.info("[Miam][MiamAPIDatasource] end reset")
        return returnValue
    }

    override suspend fun updateGroceriesList(
        groceriesList: GroceriesList,
        included: List<String>
    ): GroceriesList {
        LogHandler.info("[Miam][MiamAPIDatasource] starting updateGroceriesList $groceriesList")
        val returnValue = httpClient.patch {
            url {
                appendPathSegments(MiamAPIEndpoint.GROCERIESLIST.path, groceriesList.id)
                parameters.append("include", included.joinToString(","))
            }
            setBody(RecordWrapper.fromRecord(groceriesList))
        }.body<RecordWrapper>().toRecord() as GroceriesList
        LogHandler.info("[Miam][MiamAPIDatasource] end updateGroceriesList $groceriesList")
        return returnValue
    }

/////////////////////// POINT OF SALE ////////////////////////////////////////////////

    override suspend fun getPosFormExtId(extId: String, supplierId: Int): PointOfSale? {
        // this filter is suppose to return a single result or nothing
        LogHandler.info("[Miam][MiamAPIDatasource] starting getPosFormExtId $extId $supplierId")
        val url = URLBuilder(baseURL).run {
            appendPathSegments(MiamAPIEndpoint.POINTOFSALE.path)
            parameters.append("filter[ext-id]", extId)
            parameters.append("filter[supplier-id]", supplierId.toString())
            buildString()
        }
        val posList = get<PointOfSales>(url)
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
        val url = URLBuilder(baseURL).run {
            appendPathSegments(MiamAPIEndpoint.GROCERIESLIST.path, listId, "baskets")
            parameters.append("filter[point_of_sale_id]", posId.toString())
            parameters.append("include", included.joinToString(","))
            buildString()
        }
        val baskets = get<RecordWrapper>(url).toRecords().filterIsInstance<Basket>()
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
            url {
                appendPathSegments(MiamAPIEndpoint.BASKET.path, basket.id)
            }
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
        val url = URLBuilder(baseURL).run {
            appendPathSegments(MiamAPIEndpoint.RECIPE.path, idRecipe, "pricing")
            parameters.append("point_of_sale_id", idPos.toString())
            if (serves != null) {
                parameters.append("serves", serves.toString())
            }
            buildString()
        }
        val returnValue = get<Pricing>(url)
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
            url {
                appendPathSegments(MiamAPIEndpoint.BASKET_ENTRIES.path, basketEntry.id)
                parameters.append("include", included.joinToString(","))
            }
            setBody(RecordWrapper.fromRecord(basketEntry))
        }.body<RecordWrapper>().toRecord() as BasketEntry
        LogHandler.info("[Miam][MiamAPIDatasource] end updateBasketEntry $basketEntry $returnValue")
        return returnValue
    }

    ////////////////////////////////// GROCERY ENTRY ////////////////////////////////////////

    override suspend fun updateGroceriesEntry(ge: GroceriesEntry): GroceriesEntry {
        LogHandler.info("[Miam][MiamAPIDatasource] starting updateGroceriesEntry $ge")
        val returnValue = httpClient.patch {
            url {
                appendPathSegments(MiamAPIEndpoint.GROCERIES_ENTRY.path, ge.id)
            }
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
            url {
                appendPathSegments(MiamAPIEndpoint.SUPPLIER.path, supplierId.toString(), "webhooks", "basket_updated")
            }
            setBody(SupplierNotificationWrapper(basketToken, status, price))
        }
    }

    override suspend fun getSupplier(supplierId: Int): Supplier {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getSupplier $supplierId ")
        val supplierFields = "?fields[suppliers]=language-id"
        val returnValue = httpClient.get { url(HttpRoutes.SUPPLIER + "$supplierId$supplierFields") }
            .body<RecordWrapper>()
            .toRecord() as Supplier
        LogHandler.info("[Miam][MiamAPIDatasource] end getSupplier $supplierId $returnValue")
        return returnValue
    }

    ///////////////////////////////////// PACKAGE /////////////////////////////////////////////////

    override suspend fun getActivePackagesFromSupplierID(supplierId: String, included: List<String>): List<Package> {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getActivePackagesFromSupplierID $supplierId")
        val url = URLBuilder(baseURL).run {
            appendPathSegments(MiamAPIEndpoint.PACKAGE.path)
            parameters.append("filter[category_for]", supplierId)
            parameters.append("[status]", "4")
            parameters.append("[user_preferences]", "true")
            parameters.append("sort", "catalog_positions")
            parameters.append("include", included.joinToString(","))
            buildString()
        }
        val returnValue = get<RecordWrapper>(url).toRecords()
        val packageList = returnValue.map { record -> record as Package }
        LogHandler.info("[Miam][MiamAPIDatasource] end getActivePackagesFromSupplierID ${packageList.map { it.attributes?.title }}")
        return packageList
    }

    ///////////////////////////////////// TAG /////////////////////////////////////////////////

    override suspend fun autocompleteTag(searchStr: String): List<Tag> {
        LogHandler.info("[Miam][MiamAPIDatasource] starting autocompleteTag $searchStr")
        val url = URLBuilder(baseURL).run {
            appendPathSegments(MiamAPIEndpoint.TAGS.path, "autocomplete", searchStr)
            buildString()
        }
        val returnValue = get<RecordWrapper>(url).toRecords()
        LogHandler.info("[Miam][MiamAPIDatasource] end autocompleteTag ")
        return returnValue.map { record -> record as Tag }
    }

    override suspend fun getTagById(id: String): Tag {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getTagById $id")
        val url = URLBuilder(baseURL).run {
            appendPathSegments(MiamAPIEndpoint.TAGS.path, id)
            buildString()
        }
        val returnValue = get<RecordWrapper>(url).toRecord()
        LogHandler.info("[Miam][MiamAPIDatasource] end getTagById ")
        return returnValue as Tag
    }

    override suspend fun getTags(filters: Map<String, String>): List<Tag> {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getTags")
        val url = URLBuilder(baseURL).run {
            appendPathSegments(MiamAPIEndpoint.TAGS.path)
            filters.forEach {
                parameters.append("filter[${it.key}]", it.value)
            }
            buildString()
        }
        val returnValue = get<RecordWrapper>(url).toRecords()
        LogHandler.info("[Miam][MiamAPIDatasource] end ${returnValue.map { record -> "${(record as Tag).attributes?.name}" }}")
        return returnValue.map { record -> record as Tag }
    }

    ///////////////////////////////////// Sponsor /////////////////////////////////////////////////

    override suspend fun getSponsorById(sponsorId: String, included: List<String>): Sponsor {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getSponsorById")
        val url = URLBuilder(baseURL).run {
            appendPathSegments(MiamAPIEndpoint.SPONSOR.path, sponsorId)
            parameters.append("include", included.joinToString(","))
            buildString()
        }
        val returnValue = get<RecordWrapper>(url).toRecord()
        LogHandler.info("[Miam][MiamAPIDatasource] end getSponsorById ")
        return returnValue as Sponsor
    }

    ///////////////////////////////////// Sponsor block /////////////////////////////////////////////////

    override suspend fun getSponsorBlocksBySponsorId(sponsorId: String, included: List<String>): List<SponsorBlock> {
        LogHandler.info("[Miam][MiamAPIDatasource] starting getSponsorBlockBySponsorId")
        val url = URLBuilder(baseURL).run {
            appendPathSegments(MiamAPIEndpoint.SPONSOR_BLOCK.path)
            parameters.append("filter[sponsor-id]", sponsorId.toString())
            parameters.append("include", included.joinToString(","))
            buildString()
        }
        val returnValue = get<RecordWrapper>(url).toRecords()
        LogHandler.info("[Miam][MiamAPIDatasource] end ${returnValue.map { record -> "${(record as SponsorBlock).id}" }}")
        return returnValue.map { record -> record as SponsorBlock }
    }

}
