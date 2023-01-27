package com.miam.kmmMiamCore.miam_core.model.fake

import com.miam.kmmMiamCore.miam_core.model.Item
import com.miam.kmmMiamCore.miam_core.model.ItemAttributes
import com.miam.kmmMiamCore.miam_core.model.ItemRelationships

public object ItemFakeFactory {
    public const val FAKE_ID: String = "itemUUID"
    public const val FAKE_NAME: String = "itemName"

    public fun create(
        id: String = FAKE_ID,
        attributes: ItemAttributes? = createAttributes(),
        relationships: ItemRelationships? = null
    ): Item {
        return Item(
            id = id,
            attributes = attributes,
            relationships = relationships
        )
    }

    public fun createAttributes(
        name: String = FAKE_NAME
    ): ItemAttributes = ItemAttributes(
        name = name,
        unitPrice = "",
        createdAt = "",
        updatedAt = "",
        variableCapacity = false,
        // TODO Romain: Any useful parameter
    )

}