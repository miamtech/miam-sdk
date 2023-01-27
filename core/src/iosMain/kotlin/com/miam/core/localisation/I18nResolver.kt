package com.miam.core.localisation

import com.miam.core.localisation.i18n.I18n
import com.miam.core.localisation.i18n.I18nResource
import com.miam.core.localisation.i18n.asStringDesc
import platform.Foundation.NSBundle
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
public actual object I18nResolver {

    internal var customBundle: NSBundle? = null

    public fun registerAppBundle(bundle: NSBundle) {
        customBundle = bundle
    }

    public actual fun localize(i18nResource: I18nResource): I18n {
        val stringDesc = when (i18nResource) {
            is I18n -> return i18nResource
            else -> i18nResource.asStringDesc()
        }

        return I18n(i18nResource.accessibilityKey, stringDesc.localized())
    }
}
