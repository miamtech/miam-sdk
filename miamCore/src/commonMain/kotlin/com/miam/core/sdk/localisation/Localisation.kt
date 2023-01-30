package com.miam.core.sdk.localisation

import com.miam.core.localisation.i18n.I18n
import com.miam.sdk.resources.MiamSdkResources

private val miamStrings = runCatching { MiamSdkResources.strings }.getOrNull()

public object Localisation {
    public object Recipe {
        public val add: I18n
            get() = I18n.string("com_miam_i18n_recipe_add", miamStrings?.com_miam_i18n_recipe_add)
    }
}