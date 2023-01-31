package com.miam.kmmMiamCore.miam_core.data.datasource

import io.ktor.client.engine.*

// Workaround to avoid
//      kotlin.IllegalStateException:
//          Failed to find HttpClientEngineContainer.
//          Consider adding [HttpClientEngine] implementation in dependencies.
// https://youtrack.jetbrains.com/issue/KTOR-3517/iOS-Failed-to-find-HttpClientEngineContainer-with-new-native-memory-model
internal expect val defaultPlatformEngine: HttpClientEngine