package com.miam.kmmMiamCore.miam_core.model.fake

import com.miam.kmmMiamCore.miam_core.model.Recipe
import com.miam.kmmMiamCore.miam_core.model.RecipeAttributes
import com.miam.kmmMiamCore.miam_core.model.RecipeRelationships

public object RecipeFakeFactory {

    public const val FAKE_ID: String = "recipeUUID"
    public const val FAKE_TITLE: String = "recipeTitle"

    public fun create(
        id: String = FAKE_ID,
        attributes: RecipeAttributes? = createAttributes(),
        relationships: RecipeRelationships? = null
    ): Recipe = Recipe(
        id = id, 
        attributes = attributes,
        relationships = relationships
    )

    public fun createAttributes(
        title: String = FAKE_TITLE
    ): RecipeAttributes = RecipeAttributes(
        title = title,
        mediaUrl = "",
        // TODO Romain: Any useful parameter
    )
}
