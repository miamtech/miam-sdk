package com.miam.core.localisation

import android.annotation.SuppressLint
import android.content.Context
import com.miam.core.localisation.i18n.I18n
import com.miam.core.localisation.i18n.I18nResource
import com.miam.core.localisation.i18n.asStringDesc

@SuppressLint("StaticFieldLeak")
public actual object I18nResolver {

    private var registeredContext: Context? = null
    private val context: Context
        get() = registeredContext
        ?: throw IllegalStateException("Please use StringResolver.registerContext first")

    public fun registerContext(ctx: Context) {
        registeredContext = ctx.applicationContext
    }

    public actual fun localize(i18nResource: I18nResource): I18n {
        val stringDesc = when(i18nResource) {
            is I18n -> return i18nResource
            else -> i18nResource.asStringDesc()
        }

        return I18n(i18nResource.accessibilityKey, stringDesc.toString(context))
    }

}