package com.miam.core.localisation.custom

import com.miam.core.localisation.I18nResolver
import com.miam.core.localisation.i18n.I18nResource
import com.miam.core.localisation.i18n.I18nString
import dev.icerock.moko.resources.PluralsResource
import dev.icerock.moko.resources.StringResource

internal actual val I18nString.customized: StringResource?
    get() = I18nResolver.customBundle?.let {
        StringResource(resourceId = accessibilityKey, bundle = it)
    }

public actual val I18nResource.customized: PluralsResource?
    get() = I18nResolver.customBundle?.let {
        PluralsResource(resourceId = accessibilityKey, bundle = it)
    }
