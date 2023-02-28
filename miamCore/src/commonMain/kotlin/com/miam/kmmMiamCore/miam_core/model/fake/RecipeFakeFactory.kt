package com.miam.kmmMiamCore.miam_core.model.fake

import com.miam.kmmMiamCore.miam_core.model.IngredientListRelationship
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
        title: String = FAKE_TITLE,
        mediaUrl: String = "https://lh3.googleusercontent.com/tbMNuhJ4KxReIPF_aE0yve0akEHeN6O8hauty_XNUF2agWsmyprACLg0Zw6s8gW-QCS3A0QmplLVqBKiMmGf_Ctw4SdhARvwldZqAtMG"
    ): RecipeAttributes = RecipeAttributes(
        title = title,
        mediaUrl = mediaUrl,
        // TODO Romain: Any useful parameter
    )

    public fun createRelationships(
        ingredients: IngredientListRelationship = IngredientListRelationship(listOf(IngredientFakeFactory.create()))
    ): RecipeRelationships = RecipeRelationships(
        ingredients = ingredients
    )
}
