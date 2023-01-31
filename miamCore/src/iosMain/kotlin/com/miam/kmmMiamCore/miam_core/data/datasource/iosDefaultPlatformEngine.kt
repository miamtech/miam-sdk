package com.miam.kmmMiamCore.miam_core.data.datasource

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.ios.*

internal actual val defaultPlatformEngine: HttpClientEngine = Ios.create()