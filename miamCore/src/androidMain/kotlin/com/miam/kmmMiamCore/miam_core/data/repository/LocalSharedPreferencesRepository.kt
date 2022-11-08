package com.miam.kmmMiamCore.miam_core.data.repository

import android.content.SharedPreferences
import com.miam.kmmMiamCore.KMMContext


const val SP_NAME = "Miam_KMM"

actual fun KMMContext.putArray(key: String, value: List<String>) {
    getSpEditor()?.putStringSet(key, value.toSet())?.apply()
}

actual fun KMMContext.getArray(key: String, default: Set<String>): List<String> {
    return this.getSp()?.getStringSet(key, emptySet())?.toList() ?: emptyList()
}

actual fun KMMContext.putInt(key: String, value: Int) {
    getSpEditor()?.putInt(key, value)
}

actual fun KMMContext.getInt(key: String): Int {
    return this.getSp()?.getInt(key, -1) ?: -1
}

// new Getter
private fun KMMContext.getSp(): SharedPreferences? {
    return this.getSharedPreferences(SP_NAME, 0)
}

private fun KMMContext.getSpEditor(): SharedPreferences.Editor? {
    return this.getSp()?.edit()
}
