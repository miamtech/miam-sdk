package com.miam.core.localisation

class EnStringResolverTest : StringResolverTest() {
    override val locale: String = "en"
    override val resultSingleAmount: String ="Amount"
    override val resultSingleCancel: String ="Cancel"
    override val resultSingleContinue: String ="Continue"
    override val resultSingleVat: String ="VAT"
    override val resultFormattedString: String ="Hello, World!"
    override val resultFormattedStringInBetween: String ="Hello Romain, let's meet!"
    override val resultFormattedInt: String = "It's 99 \$"
    override val resultOnePluralInt: String = "one item"
    override val resultSomePluralInt: String = "some items"
    override val resultOneFormattedPlural: String = "1 item"
    override val resultSomeFormattedPlural: (Int) -> String = { "$it items" }
}

class FrStringResolverTest : StringResolverTest() {
    override val locale: String = "fr"
    override val resultSingleAmount: String ="Montant"
    override val resultSingleCancel: String ="Annuler"
    override val resultSingleContinue: String ="Continuer"
    override val resultSingleVat: String ="TVA"
    override val resultFormattedString: String ="Bonjour, World!"
    override val resultFormattedStringInBetween: String ="Bonjour Romain, rencontrons-nous!"
    override val resultFormattedInt: String = "Cela fait 99 €"
    override val resultOnePluralInt: String = "un élément"
    override val resultSomePluralInt: String = "plusieurs éléments"
    override val resultOneFormattedPlural: String = "1 élément"
    override val resultSomeFormattedPlural: (Int) -> String = { "$it éléments" }
}
