package com.miam.kmmMiamCore.miam_core.data.repository

import com.miam.kmmMiamCore.KMMContext

public expect fun KMMContext.putArray(key: String, value: List<String>)

public expect fun KMMContext.getArrayOrNull(key: String): List<String>?

public expect fun KMMContext.putInt(key: String, value: Int)

public expect fun KMMContext.getIntOrNull(key: String): Int?
