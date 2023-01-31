package com.miam.kmmMiamCore.miam_core.data.datasource

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.*

internal actual val defaultPlatformEngine: HttpClientEngine = Android.create()