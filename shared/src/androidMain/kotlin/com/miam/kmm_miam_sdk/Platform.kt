package com.miam.kmm_miam_sdk

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.engine.android.*


actual fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(Android) {
    config(this)

    engine {
    }
}

actual fun initLogger() {
    Napier.base(DebugAntilog())
}