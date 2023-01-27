package com.miam.kmmMiamCore.services

import com.miam.kmmMiamCore.base.mvi.Effect
import com.miam.kmmMiamCore.base.mvi.State
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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


public data class AnalyticEvent(val eventType: String, val path: String, val props: Analytics.PlausibleProps)

public data class AnalyticState(
    val onEventEmitted: (event: AnalyticEvent) -> Unit = {},
): State

public sealed class AnalyticEffect: Effect {
    public data class EventEmitted(val event: AnalyticEvent): AnalyticEffect()
}

@Suppress("UserPreferencesInstance used by ios and component")
public object AnalyticsInstance: KoinComponent {
    public val instance: Analytics by inject()
}

public class Analytics: KoinComponent {
    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        println("Miam error in Analytics $exception ${exception.stackTraceToString()}")
    }
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
    private val originToDomain: Map<String, String> = mapOf(
        "app.coursesu.com" to "miam.coursesu.app",
        "app.coursesu" to "miam.coursesu.app",
        "app.qualif.coursesu" to "miam.test"
    )
    public val domain: MutableStateFlow<String?> = MutableStateFlow(null)
    private val httpOrigin: MutableStateFlow<String?> = MutableStateFlow(null)
    private val analyticsState: MutableStateFlow<AnalyticState> = MutableStateFlow(AnalyticState())
    private val sideEffect = MutableSharedFlow<AnalyticEffect>()
    public fun observeSideEffect(): Flow<AnalyticEffect> = sideEffect

    public fun setOnEventEmitted(onEventEmittedCallBack: (event: AnalyticEvent) -> Unit) {
        analyticsState.value = analyticsState.value.copy(onEventEmitted = onEventEmittedCallBack)
    }

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

    public fun init(supplierOrigin: String) {
        domain.apply { value = originToDomain[supplierOrigin] ?: supplierOrigin }
        val isHttp = supplierOrigin.startsWith("https://")
        httpOrigin.apply { value = if (isHttp) supplierOrigin else "https://$supplierOrigin" }
        LogHandler.info("Analytics init for $domain")
    }

    private fun emitEvent(event: AnalyticEvent) {
        analyticsState.value.onEventEmitted(event)
        coroutineScope.launch(coroutineHandler) {
            sideEffect.emit(AnalyticEffect.EventEmitted(event))
        }
    }

    public fun sendEvent(eventType: String, path: String, props: PlausibleProps) {
        if (this.domain.value == null || this.httpOrigin.value == null) {
            LogHandler.error("Sending event without initialisation ${domain.value}")
            return
        }
        emitEvent(AnalyticEvent(eventType, path, props))
        val domain = this.domain.value!!
        val url = this.httpOrigin.value!! + path
        val event = PlausibleEvent(eventType, url, domain, props)
        coroutineScope.launch(coroutineHandler) {
            httpClient.postEvent(event)
        }
    }

    @Serializable
    public data class PlausibleEvent(
        val name: String,
        val url: String,
        val domain: String,
        val props: PlausibleProps
    )

    @Serializable
    public data class PlausibleProps(
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

    public companion object {
        public const val PLAUSIBLE_URL: String = "https://plausible.io/api/event"

        public const val EVENT_PAGEVIEW: String =
            "pageview" // GA4 is page_view -> use this one on ga to autotrack some fields
        public const val EVENT_SEARCH: String = "search"
        public const val EVENT_RECIPE_SHOW: String = "recipe.show"
        public const val EVENT_RECIPE_DISPLAY: String = "recipe.display"
        public const val EVENT_RECIPE_ADD: String = "recipe.add"
        public const val EVENT_RECIPE_REMOVE: String = "recipe.remove"
        public const val EVENT_RECIPE_RESET: String = "recipe.reset"
        public const val EVENT_RECIPE_CHANGEGUESTS: String = "recipe.change-guests"
        public const val EVENT_RECIPE_LIKE: String = "recipe.like"
        public const val EVENT_RECIPE_UNLIKE: String = "recipe.unlike"
        public const val EVENT_RECIPE_PRINT: String = "recipe.print"
        public const val EVENT_PERSONAL_RECIPE_CREATE: String = "recipe.personal.create"
        public const val EVENT_PERSONAL_RECIPE_DELETE: String = "recipe.personal.delete"
        public const val EVENT_CATEGORY_SHOW: String = "category.show"
        public const val EVENT_ENTRY_ADD: String = "entry.add"
        public const val EVENT_ENTRY_DELETE: String = "entry.delete"
        public const val EVENT_ENTRY_REPLACE: String = "entry.replace"
        public const val EVENT_ENTRY_CHANGE_QUANTITY: String = "entry.change-quantity"
        public const val EVENT_PAYMENT_STARTED: String = "payment.started"
        public const val EVENT_PAYMENT_CONFIRMED: String = "payment.confirmed"
        public const val EVENT_BASKET_CONFIRMED: String = "basket.confirmed"
    }
}