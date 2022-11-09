package com.miam.kmmMiamCore.services

import com.miam.kmmMiamCore.handler.ContextHandler
import com.miam.kmmMiamCore.miam_core.data.repository.getArrayOrNull
import com.miam.kmmMiamCore.miam_core.data.repository.getIntOrNull
import com.miam.kmmMiamCore.miam_core.data.repository.putArray
import com.miam.kmmMiamCore.miam_core.data.repository.putInt
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UserPreferences: KoinComponent {

    private val contextHandler: ContextHandler by inject()

    fun putList(key: String, value: List<String>) {
        contextHandler.getContextOrNull()?.putArray(key, value)
    }

    fun getListOrNull(key: String): List<String>? {
        return contextHandler.getContextOrNull()?.getArrayOrNull(key)
    }

    fun putInt(key: String, value: Int) {
        contextHandler.getContextOrNull()?.putInt(key, value)
    }

    fun getIntOrNull(key: String): Int? {
        return contextHandler.getContextOrNull()?.getIntOrNull(key)
    }
}