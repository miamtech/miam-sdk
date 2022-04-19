package com.miam.kmm_miam_sdk

import io.ktor.client.*


expect fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient
expect fun initLogger()
