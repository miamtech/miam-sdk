package com.miam.kmmMiamCore.miam_core.model.fake

import com.miam.kmmMiamCore.miam_core.model.Ingredient
import com.miam.kmmMiamCore.miam_core.model.IngredientAttributes
import com.miam.kmmMiamCore.miam_core.model.IngredientRelationships

public object IngredientFakeFactory {

    public const val FAKE_ID: String = "ingredientUUID"
    public const val FAKE_NAME: String = "ingredientName"

    public fun create(
        id: String = FAKE_ID,
        attributes: IngredientAttributes? = createAttributes(),
        relationships: IngredientRelationships? = null
    ): Ingredient {
        return Ingredient(
            id = id,
            attributes = attributes,
            relationships = relationships
        )
    }

    public fun createAttributes(
        name: String = FAKE_NAME
    ): IngredientAttributes = IngredientAttributes(
        name = name,
        quantity = null,
        unit = null,
        active = false,
        pictureUrl = null,
        forcedEans = listOf(),
        // TODO Romain: Any useful parameter
    )
}