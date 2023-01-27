package com.miam.kmmMiamCore.miam_core.model.fake

import com.miam.kmmMiamCore.miam_core.model.RecipeType
import com.miam.kmmMiamCore.miam_core.model.RecipeTypeAttributes
import com.miam.kmmMiamCore.miam_core.model.RecipeTypeRelationships

public object RecipeTypeFakeFactory {

    public const val FAKE_ID: String = "recipeTypeUUID"
    public const val FAKE_NAME: String = "recipeTypeName"

    public fun create(
        id: String = FAKE_ID,
        attributes: RecipeTypeAttributes? = createAttributes(),
        relationships: RecipeTypeRelationships? = null
    ): RecipeType = RecipeType(
        id = id,
        attributes = attributes,
        relationships = relationships
    )

    public fun createAttributes(
        name: String = FAKE_NAME
    ): RecipeTypeAttributes = RecipeTypeAttributes(
        name = name,
        // TODO Romain: Any useful parameter
    )
}