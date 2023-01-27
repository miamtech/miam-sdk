package com.miam.kmmMiamCore.miam_core.model.fake

import com.miam.kmmMiamCore.miam_core.model.Basket
import com.miam.kmmMiamCore.miam_core.model.BasketAttributes
import com.miam.kmmMiamCore.miam_core.model.BasketEntry
import com.miam.kmmMiamCore.miam_core.model.BasketEntryAttributes
import com.miam.kmmMiamCore.miam_core.model.BasketEntryRelationships
import com.miam.kmmMiamCore.miam_core.model.BasketRelationships

public object BasketFakeFactory {

    //region Basket
    public const val FAKE_BASKET_ID: String = "basketUUID"
    public const val FAKE_BASKET_NAME: String = "basketName"

    public fun createBasket(
        id: String = FAKE_BASKET_ID,
        attributes: BasketAttributes = createBasketAttributes(),
        relationships: BasketRelationships? = createBasketRelationships()
    ): Basket = Basket(
        id = id,
        attributes = attributes,
        relationships = relationships,
        recipes = emptyList(),
    )

    public fun createBasketAttributes(
        name: String = FAKE_BASKET_NAME,
    ): BasketAttributes = BasketAttributes(
        name = name,
        totalPrice = 0.0f,
        // TODO Romain: Any useful parameter
    )

    public fun createBasketRelationships(): BasketRelationships = BasketRelationships(
        basketEntries = null
        // TODO Romain: Any useful parameter
    )
    //endregion

    //region Entry
    public const val FAKE_ENTRY_ID: String = "basketEntryUUID"

    public fun createEntry(
        id: String = FAKE_ENTRY_ID,
        attributes: BasketEntryAttributes? = createEntryAttributes(),
        relationships: BasketEntryRelationships? = createEntryRelationships()
    ): BasketEntry = BasketEntry(
        id = id,
        attributes = attributes,
        relationships = relationships
    )

    public fun createEntryAttributes(
    ): BasketEntryAttributes = BasketEntryAttributes(
        learningFactor = null,
        quantity = null,
        recipeIds = emptyList(),
        groceriesEntryStatus = null,
        // TODO Romain: Any useful parameter
    )

    public fun createEntryRelationships(): BasketEntryRelationships = BasketEntryRelationships(
        // TODO Romain: Any useful parameter
    )
    //endregion

}
