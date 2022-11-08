package com.miam.kmmMiamCore.services

import com.miam.kmmMiamCore.handler.ContextHandler
import com.miam.kmmMiamCore.miam_core.data.repository.getArray
import com.miam.kmmMiamCore.miam_core.data.repository.getInt
import com.miam.kmmMiamCore.miam_core.data.repository.putArray
import com.miam.kmmMiamCore.miam_core.data.repository.putInt
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UserPreferences : KoinComponent {

    private val contextHandler: ContextHandler by inject()

    fun putList(key: String, value: List<String>) {
        val context = contextHandler.state.value.applicationContext
        if (context == null) {
            println(" [ERROR][Miam][UserPreferences] Application context must be provided")
            return
        }
        context.putArray(key, value)
    }

    fun getList(key: String): List<String> {
        val context = contextHandler.state.value.applicationContext
        if (context == null) {
            println(" [ERROR][Miam][UserPreferences] Application context must be provided")
            return emptyList()
        }
        return context.getArray(key, emptySet())
    }

    fun putInt(key: String, value: Int) {
        val context = contextHandler.state.value.applicationContext
        if (context == null) {
            println(" [ERROR][Miam][UserPreferences] Application context must be provided")
            return
        }
        context.putInt(key, value)
    }

    fun getInt(key: String): Int {
        val context = contextHandler.state.value.applicationContext
        if (context == null) {
            println(" [ERROR][Miam][UserPreferences] Application context must be provided")
            return -1
        }
        return context.getInt(key)
    }
}