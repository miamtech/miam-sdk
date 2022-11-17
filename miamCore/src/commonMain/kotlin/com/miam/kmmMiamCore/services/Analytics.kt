package com.miam.kmmMiamCore.services

import com.miam.kmmMiamCore.handler.LogHandler
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.encodeToJsonElement
import org.koin.core.component.KoinComponent

class Analytics: KoinComponent {
    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        println("Miam error in Analytics $exception ${exception.stackTraceToString()}")
    }
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)

    private val originToDomain: Map<String, String> = mapOf(
        "app.coursesu.com" to "miam.coursesu.app",
        "app.coursesu" to "miam.coursesu.app",
        "app.qualif.coursesu" to "miam.test"
    )
    val domain: MutableStateFlow<String?> = MutableStateFlow(null)
    private val httpOrigin: MutableStateFlow<String?> = MutableStateFlow(null)

    private val httpClient = HttpClient {
        install(JsonFeature) { serializer = KotlinxSerializer(kotlinx.serialization.json.Json) }
        BrowserUserAgent()
        install(DefaultRequest)
    }

    private suspend fun HttpClient.postEvent(event: PlausibleEvent) {
        post<String>(PLAUSIBLE_URL) {
            contentType(ContentType.Application.Json)
            body = event
        }
    }

    fun init(supplierOrigin: String) {
        domain.apply { value = originToDomain[supplierOrigin] ?: supplierOrigin }
        val isHttp = supplierOrigin.startsWith("https://")
        httpOrigin.apply { value = if (isHttp) supplierOrigin else "https://$supplierOrigin" }
        LogHandler.info("Analytics init for $domain")
    }

    fun sendEvent(eventType: String, path: String, props: PlausibleProps) {
        if (this.domain.value == null || this.httpOrigin.value == null) {
            LogHandler.error("Sending event without initialisation ${domain.value}")
            return
        }
        val domain = this.domain.value!!
        val url = this.httpOrigin.value!! + path
        val event = PlausibleEvent(eventType, url, domain, props)

        LogHandler.debug("Sending event $event")
        LogHandler.debug("Sending event ${kotlinx.serialization.json.Json.encodeToJsonElement(event)}")
        coroutineScope.launch(coroutineHandler) {
            httpClient.postEvent(event)
        }
    }

    @Serializable
    data class PlausibleEvent(
        val name: String,
        val url: String,
        val domain: String,
        val props: PlausibleProps
    )

    @Serializable
    data class PlausibleProps(
        val recipe_id: String? = null,
        val category_id: String? = null,
        val entry_name: String? = null,
        val basket_id: String? = null,
        val miam_amount: Float? = null,
        val total_amount: String? = null,
        val pos_id: String? = null,
        val pos_total_amount: String? = null,
        val pos_name: String? = null,
        val search_term: String? = null,
    )

    companion object {
        const val PLAUSIBLE_URL = "https://plausible.io/api/event"

        const val EVENT_PAGEVIEW =
            "pageview" // GA4 is page_view -> use this one on ga to autotrack some fields
        const val EVENT_SEARCH = "search"
        const val EVENT_RECIPE_SHOW = "recipe.show"
        const val EVENT_RECIPE_DISPLAY = "recipe.display"
        const val EVENT_RECIPE_ADD = "recipe.add"
        const val EVENT_RECIPE_REMOVE = "recipe.remove"
        const val EVENT_RECIPE_RESET = "recipe.reset"
        const val EVENT_RECIPE_CHANGEGUESTS = "recipe.change-guests"
        const val EVENT_RECIPE_LIKE = "recipe.like"
        const val EVENT_RECIPE_UNLIKE = "recipe.unlike"
        const val EVENT_RECIPE_PRINT = "recipe.print"
        const val EVENT_PERSONAL_RECIPE_CREATE = "recipe.personal.create"
        const val EVENT_PERSONAL_RECIPE_DELETE = "recipe.personal.delete"
        const val EVENT_CATEGORY_SHOW = "category.show"
        const val EVENT_ENTRY_ADD = "entry.add"
        const val EVENT_ENTRY_DELETE = "entry.delete"
        const val EVENT_ENTRY_REPLACE = "entry.replace"
        const val EVENT_ENTRY_CHANGE_QUANTITY = "entry.change-quantity"
        const val EVENT_PAYMENT_STARTED = "payment.started"
        const val EVENT_PAYMENT_CONFIRMED = "payment.confirmed"
        const val EVENT_BASKET_CONFIRMED = "basket.confirmed"
    }
}