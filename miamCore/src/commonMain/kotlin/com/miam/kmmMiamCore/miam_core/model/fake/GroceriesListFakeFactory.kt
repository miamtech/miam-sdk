package com.miam.kmmMiamCore.miam_core.model.fake

import com.miam.kmmMiamCore.miam_core.model.GroceriesList
import com.miam.kmmMiamCore.miam_core.model.GroceriesListAttributes
import com.miam.kmmMiamCore.miam_core.model.GroceriesListRelationships

public object GroceriesListFakeFactory {

    public const val FAKE_ID: String = "groceriesListUUID"
    public const val FAKE_NAME: String = "groceriesListName"
    public fun create(
        id: String = FAKE_ID,
        attributes: GroceriesListAttributes? = createAttributes(),
        relationships: GroceriesListRelationships? = null
    ): GroceriesList = GroceriesList(
        id = id,
        attributes = attributes,
        relationships = relationships
    )

    public fun createAttributes(
        name: String = FAKE_NAME
    ) : GroceriesListAttributes = GroceriesListAttributes(
        name = name, createdAt = "", updatedAt = "", recipesInfos = mutableListOf()
        // TODO Romain: Any useful parameter
    )
}