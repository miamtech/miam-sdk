package com.miam.core.localisation

import com.miam.core.localisation.i18n.I18n
import com.miam.core.localisation.i18n.I18nResource

public expect object I18nResolver {
    public fun localize(i18nResource: I18nResource): I18n
}
