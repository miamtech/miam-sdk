package com.miam.kmmMiamCore.miam_core.data.repository

import com.miam.kmmMiamCore.KMMContext
import platform.Foundation.NSUserDefaults

actual fun KMMContext.putArray(key: String, value: List<String>) {
    NSUserDefaults.standardUserDefaults.setObject(value, key)
}

actual fun KMMContext.getArray(key: String, default: Set<String>): List<String> {
    return NSUserDefaults.standardUserDefaults.stringArrayForKey(key)?.toList() as List<String>?
        ?: emptyList()
}

actual fun KMMContext.putInt(key: String, value: Int) {
    NSUserDefaults.standardUserDefaults.setObject(value, key)
}

actual fun KMMContext.getInt(key: String): Int {
    return NSUserDefaults.standardUserDefaults.intForKey(key)
}