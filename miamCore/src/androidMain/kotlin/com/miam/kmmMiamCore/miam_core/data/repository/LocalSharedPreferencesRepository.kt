package com.miam.kmmMiamCore.miam_core.data.repository

import android.content.SharedPreferences
import com.miam.kmmMiamCore.KMMContext

/**
 * Android implementation of LocalSharedPreferenceRepository  from common main
 * Extend Context class by adding new function that fetch data in device shared preferences
 */

// Name of miam local shard preferences on device, kind of folder for our preferences
public const val SP_NAME: String = "Miam_KMM"

public actual fun KMMContext.putArray(key: String, value: List<String>) {
    getSpEditor()?.putStringSet(key, value.toSet())?.apply()
}

public actual fun KMMContext.getArrayOrNull(key: String): List<String>? {
    return this.getSp()?.getStringSet(key, null)?.toList()
}

public actual fun KMMContext.putInt(key: String, value: Int) {
    getSpEditor()?.putInt(key, value)?.apply()
}

public actual fun KMMContext.getIntOrNull(key: String): Int? {
    if (this.getSp()?.contains(key) != true) return null
    // arbitrary -1, should never be used has we are sure key exists
    return this.getSp()?.getInt(key, -1)
}

// New Getter Android only
private fun KMMContext.getSp(): SharedPreferences? {
    return this.getSharedPreferences(SP_NAME, 0)
}

// New Getter Android only
private fun KMMContext.getSpEditor(): SharedPreferences.Editor? {
    return this.getSp()?.edit()
}
