package com.miam.kmmMiamCore.miam_core.model.fake

import com.miam.kmmMiamCore.miam_core.model.GroceriesEntry
import com.miam.kmmMiamCore.miam_core.model.GroceriesEntryAttributes
import com.miam.kmmMiamCore.miam_core.model.GroceriesEntryRelationships

public object GroceriesEntryFakeFactory {
    public const val FAKE_ID: String = "groceriesEntryUUID"
    public const val FAKE_NAME: String = "groceriesEntryName"

    public fun create(
        id: String = FAKE_ID,
        attributes: GroceriesEntryAttributes? = createGroceriesEntryAttributes(),
        relationships: GroceriesEntryRelationships? = null
    ): GroceriesEntry = GroceriesEntry(
        id = id,
        attributes = attributes,
        relationships = relationships,
    )

    public fun createGroceriesEntryAttributes(
        name : String = FAKE_NAME
    ): GroceriesEntryAttributes = GroceriesEntryAttributes(
        name = name,
        // TODO Romain: Any useful parameter
    )
}