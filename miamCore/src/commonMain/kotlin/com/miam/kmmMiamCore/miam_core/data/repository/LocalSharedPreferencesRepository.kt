package com.miam.kmmMiamCore.miam_core.data.repository

import com.miam.kmmMiamCore.KMMContext

expect fun KMMContext.putArray(key: String, value: List<String>)

expect fun KMMContext.getArrayOrNull(key: String): List<String>?

expect fun KMMContext.putInt(key: String, value: Int)

expect fun KMMContext.getIntOrNull(key: String): Int?