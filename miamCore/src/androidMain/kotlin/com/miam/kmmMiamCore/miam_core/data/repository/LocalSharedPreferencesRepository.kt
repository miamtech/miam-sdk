package com.miam.kmmMiamCore.miam_core.data.repository

import android.content.SharedPreferences
import com.miam.kmmMiamCore.KMMContext

const val SP_NAME = "AndroidTestApp"

actual fun KMMContext.putArray(key: String, value: List<String>) {
    getSpEditor()?.putStringSet(key, value.toSet())?.apply()
}

actual fun KMMContext.getArray(key: String, default: Set<String>): List<String> {
    val lulu = this.getSp()
    return lulu?.getStringSet(key, emptySet())?.toList() ?: emptyList()
}

private fun KMMContext.getSp(): SharedPreferences? {
    val lolo = this
    return lolo.getSharedPreferences(SP_NAME, 0)
}

private fun KMMContext.getSpEditor(): SharedPreferences.Editor? {
    val lolo = this
    return lolo.getSp()?.edit()
}

