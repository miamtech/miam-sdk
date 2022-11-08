package com.miam.kmmMiamCore.services

import com.miam.kmmMiamCore.KMMContext
import com.miam.kmmMiamCore.miam_core.data.repository.getArray
import com.miam.kmmMiamCore.miam_core.data.repository.putArray

class KMMPreference(private val context: KMMContext) {

    fun putByTagType(key: String, value: List<String>) {
        println("---------------> " + context.toString())
        context.putArray(key, value)
    }

    fun getByTagType(key: String): List<String> {
        println("---------------> " + context.toString())
        return context.getArray(key, emptySet())
    }
}
