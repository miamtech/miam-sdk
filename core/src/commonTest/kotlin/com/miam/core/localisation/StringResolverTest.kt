package com.miam.core.localisation

import com.miam.core.localisation.i18n.I18n
import com.miam.core.localisation.i18n.i18n
import com.miam.core.resources.MiamCoreResources
import com.miam.test.PlatformResourceTest
import dev.icerock.moko.resources.desc.StringDesc
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

abstract class StringResolverTest : PlatformResourceTest() {

    abstract val locale: String
    abstract val resultSingleAmount: String
    abstract val resultSingleCancel: String
    abstract val resultSingleContinue: String
    abstract val resultSingleVat: String
    abstract val resultFormattedString: String
    abstract val resultFormattedStringInBetween: String
    abstract val resultFormattedInt: String
    abstract val resultOnePluralInt: String
    abstract val resultSomePluralInt: String
    abstract val resultOneFormattedPlural: String
    abstract val resultSomeFormattedPlural: (Int) -> String

    @BeforeTest
    fun setup() {
        StringDesc.localeType = StringDesc.LocaleType.Custom(locale)
    }

    @Test
    fun `simple localisation resolution`() {
        val amount = I18n.string("single.amount", MiamCoreResources.strings.single_amount)
        assertEquals(I18n("single.amount", resultSingleAmount), amount.i18n)

        val cancel = I18n.string("single.cancel", MiamCoreResources.strings.single_cancel)
        assertEquals(I18n("single.cancel", resultSingleCancel), cancel.i18n)

        val cont = I18n.string("single.continue", MiamCoreResources.strings.single_continue)
        assertEquals(I18n("single.continue", resultSingleContinue), cont.i18n)

        val vat = I18n.string("single.vat", MiamCoreResources.strings.single_vat)
        assertEquals(I18n("single.vat", resultSingleVat), vat.i18n)
    }

    @Test
    fun `formatted localisation resolution`() {
        val hello = I18n.string(
            "formatted.string",
            MiamCoreResources.strings.formatted_string,
            "World!"
        )
        assertEquals(I18n("formatted.string", resultFormattedString), hello.i18n)

        val letsMeet = I18n.string(
            "formatted.string.in.between",
            MiamCoreResources.strings.formatted_string_in_between,
            "Romain"
        )
        assertEquals(I18n("formatted.string.in.between", resultFormattedStringInBetween), letsMeet.i18n)

        val formattedInt = I18n.string(
            "formatted.int",
            MiamCoreResources.strings.formatted_int,
            99
        )
        assertEquals(I18n("formatted.int", resultFormattedInt), formattedInt.i18n)
    }

    @Test
    fun `plural localisation resolution`() {
        for(qty in 1..100) {
            val i18n = I18n.plural(
                "items",
                MiamCoreResources.plurals.items,
                qty
            )

            if (qty == 1) {
                assertEquals(resultOnePluralInt, i18n.i18n.localised)
            } else {
                assertEquals(resultSomePluralInt, i18n.i18n.localised)
            }
        }
    }

    @Test
    fun `plural formatted localisation resolution`() {
        for(qty in 1..100) {
            val i18n = I18n.plural(
                "formatted.items",
                MiamCoreResources.plurals.formatted_items,
                qty,
                qty
            )

            if (qty == 1) {
                assertEquals(resultOneFormattedPlural, i18n.i18n.localised)
            } else {
                assertEquals(resultSomeFormattedPlural(qty), i18n.i18n.localised)
            }
        }
    }
}
