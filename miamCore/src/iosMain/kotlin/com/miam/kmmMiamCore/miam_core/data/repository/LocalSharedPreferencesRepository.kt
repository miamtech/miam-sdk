package com.miam.kmmMiamCore.miam_core.data.repository

import com.miam.kmmMiamCore.KMMContext
import platform.Foundation.NSUserDefaults

/**
 * Ios implementation of LocalSharedPreferenceRepository from common main
 * Use NSUserDefaults to access local user preference
 * KMMContext is an NSObject and can be any reference
 */

public actual fun KMMContext.putArray(key: String, value: List<String>) {
    NSUserDefaults.standardUserDefaults.setObject(value, key)
}

@Suppress("unchecked_cast")
public actual fun KMMContext.getArrayOrNull(key: String): List<String>? {
    return NSUserDefaults.standardUserDefaults.stringArrayForKey(key)?.toList() as List<String>?
}

public actual fun KMMContext.putInt(key: String, value: Int) {
    NSUserDefaults.standardUserDefaults.setObject(value, key)
}

public actual fun KMMContext.getIntOrNull(key: String): Int? {
    if (NSUserDefaults.standardUserDefaults().objectForKey(key) == null) return null
    return NSUserDefaults.standardUserDefaults.integerForKey(key).toInt()
}