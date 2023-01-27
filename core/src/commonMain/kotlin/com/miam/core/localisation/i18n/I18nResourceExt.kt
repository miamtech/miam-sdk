@file:Suppress("MatchingDeclarationName")

package com.miam.core.localisation.i18n

import com.miam.core.localisation.I18nResolver
import com.miam.core.localisation.custom.customized
import dev.icerock.moko.resources.desc.Plural
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import dev.icerock.moko.resources.format

public object NoResourceThrowable: Throwable()

public val I18nResource.i18n: I18n
    get() = kotlin.runCatching {
        I18nResolver.localize(this)
    }.getOrDefault(I18n(accessibilityKey, accessibilityKey))

public fun I18nResource.asStringDesc(): StringDesc = when (this) {
    is I18nPlurals -> {
        val resource = customized ?: resource
        ?: throw NoResourceThrowable

        when {
            args.isEmpty() -> StringDesc.Plural(resource, quantity)
            else -> resource.format(quantity, args)
        }
    }
    is I18nString -> {
        val resource = customized ?: resource
        ?: throw NoResourceThrowable

        when {
            args.isEmpty() -> resource.desc()
            else -> resource.format(args)
        }
    }
    is I18n -> error("$this is already resolved.")
}
