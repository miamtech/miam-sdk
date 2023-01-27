package com.miam.kmmMiamCore.services

import com.miam.kmmMiamCore.handler.ContextHandler
import com.miam.kmmMiamCore.miam_core.data.repository.getArrayOrNull
import com.miam.kmmMiamCore.miam_core.data.repository.getIntOrNull
import com.miam.kmmMiamCore.miam_core.data.repository.putArray
import com.miam.kmmMiamCore.miam_core.data.repository.putInt
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@Suppress("UserPreferencesInstance used by ios and component")
public object UserPreferencesInstance: KoinComponent {
    public val instance: UserPreferences by inject()
}

public class UserPreferences: KoinComponent {

    private val contextHandler: ContextHandler by inject()

    public fun putList(key: String, value: List<String>) {
        contextHandler.getContextOrNull()?.putArray(key, value)
    }

    public fun getListOrNull(key: String): List<String>? {
        return contextHandler.getContextOrNull()?.getArrayOrNull(key)
    }

    public fun putInt(key: String, value: Int) {
        contextHandler.getContextOrNull()?.putInt(key, value)
    }

    public fun getIntOrNull(key: String): Int? {
        return contextHandler.getContextOrNull()?.getIntOrNull(key)
    }
}