package com.miam.kmm_miam_sdk

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.engine.ios.*


actual fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(Ios) {
    config(this)

    engine {
        configureRequest {
            setAllowsCellularAccess(true)
        }
    }
}

actual fun initLogger() {
   
}