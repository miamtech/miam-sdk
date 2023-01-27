package com.miam.core.localisation.i18n

import dev.icerock.moko.resources.PluralsResource
import dev.icerock.moko.resources.StringResource

public sealed interface I18nResource {
    public val accessibilityKey: String
}

internal data class I18nString(
    override val accessibilityKey: String,
    val resource: StringResource?,
    val args: List<Any> = emptyList()
) : I18nResource

internal data class I18nPlurals(
    override val accessibilityKey: String,
    val resource: PluralsResource?,
    val quantity: Int,
    val args: List<Any> = emptyList()
) : I18nResource

public data class I18n(
    override val accessibilityKey: String,
    val localised: String,
) : I18nResource {
    public companion object {
        public fun empty(): I18n = I18n("", "")
        public fun string(
            accessibilityKey: String,
            res: StringResource?,
            vararg args: Any,
        ): I18n = I18nString(accessibilityKey, res, args.toList()).i18n

        public fun plural(
            accessibilityKey: String,
            res: PluralsResource?,
            quantity: Int,
            vararg args: Any,
        ): I18n = I18nPlurals(accessibilityKey, res, quantity, args.toList()).i18n
    }
}
