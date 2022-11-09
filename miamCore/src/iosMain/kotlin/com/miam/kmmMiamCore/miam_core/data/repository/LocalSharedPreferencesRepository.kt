package com.miam.kmmMiamCore.miam_core.data.repository

import com.miam.kmmMiamCore.KMMContext
import platform.Foundation.NSUserDefaults

/**
 * Ios implementation of LocalSharedPreferenceRepository from common main
 * Use NSUserDefaults to access local user preference
 * KMMContext is an NSObject and can be any reference
 */

actual fun KMMContext.putArray(key: String, value: List<String>) {
    NSUserDefaults.standardUserDefaults.setObject(value, key)
}

actual fun KMMContext.getArrayOrNull(key: String): List<String>? {
    return NSUserDefaults.standardUserDefaults.stringArrayForKey(key)?.toList()
}

actual fun KMMContext.putInt(key: String, value: Int) {
    NSUserDefaults.standardUserDefaults.setObject(value, key)
}

actual fun KMMContext.getIntOrNull(key: String): Int? {
    return NSUserDefaults.standardUserDefaults.intForKey(key)
}