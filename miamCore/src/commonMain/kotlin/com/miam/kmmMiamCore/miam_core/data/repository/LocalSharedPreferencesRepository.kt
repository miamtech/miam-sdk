package com.miam.kmmMiamCore.miam_core.data.repository

import com.miam.kmmMiamCore.KMMContext

expect fun KMMContext.putArray(key: String, value: List<String>)

expect fun KMMContext.getArray(key: String, default: Set<String>): List<String>