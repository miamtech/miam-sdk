package com.miam.kmmMiamCore.services

import com.miam.kmmMiamCore.KMMContext
import com.miam.kmmMiamCore.miam_core.data.repository.getArray
import com.miam.kmmMiamCore.miam_core.data.repository.putArray

class KMMPreference(private val context: KMMContext) {

    fun put(key: String, value: List<String>) {
        println(context)
        context.putArray(key, value)
    }

    fun getList(key: String): List<String> {
        println(context)
        return context.getArray(key, emptySet())
    }
}
