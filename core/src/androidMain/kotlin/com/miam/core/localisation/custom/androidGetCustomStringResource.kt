package com.miam.core.localisation.custom

import com.miam.core.localisation.i18n.I18nResource
import com.miam.core.localisation.i18n.I18nString
import dev.icerock.moko.resources.PluralsResource
import dev.icerock.moko.resources.StringResource

internal actual val I18nString.customized: StringResource? get() = null
public actual val I18nResource.customized: PluralsResource? get() = null
