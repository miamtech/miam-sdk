package com.miam.core.localisation.custom

import com.miam.core.localisation.i18n.I18nResource
import com.miam.core.localisation.i18n.I18nString
import dev.icerock.moko.resources.PluralsResource
import dev.icerock.moko.resources.StringResource

internal expect val I18nString.customized: StringResource?
internal expect val I18nResource.customized: PluralsResource?