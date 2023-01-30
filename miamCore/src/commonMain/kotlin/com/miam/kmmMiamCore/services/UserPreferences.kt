package com.miam.kmmMiamCore.services

import com.miam.core.sdk.di.MiamDI
import com.miam.kmmMiamCore.handler.ContextHandler
import com.miam.kmmMiamCore.miam_core.data.repository.getArrayOrNull
import com.miam.kmmMiamCore.miam_core.data.repository.getIntOrNull
import com.miam.kmmMiamCore.miam_core.data.repository.putArray
import com.miam.kmmMiamCore.miam_core.data.repository.putInt

@Suppress("UserPreferencesInstance used by ios and component")
public object UserPreferencesInstance {
    public val instance: UserPreferences by lazy { MiamDI.userPreferences }
}

public class UserPreferences {

    private val contextHandler: ContextHandler = MiamDI.contextHandler

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