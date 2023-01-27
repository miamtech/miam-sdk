package com.miam.kmmMiamCore.miam_core.model.fake

import com.miam.kmmMiamCore.miam_core.model.RecipeLike
import com.miam.kmmMiamCore.miam_core.model.RecipeLikeAttributes
import com.miam.kmmMiamCore.miam_core.model.RecipeLikeRelationships

public object RecipeLikeFakeFactory {

    public const val FAKE_ID: String = "recipeLikeUUID"
    public const val FAKE_TITLE: String = "recipeLikeTitle"

    public fun create(
        id: String = FAKE_ID,
        attributes: RecipeLikeAttributes? = createAttributes(),
        relationships: RecipeLikeRelationships? = null
    ): RecipeLike = RecipeLike(
        id = id,
        attributes = attributes,
        relationships = relationships
    )

    public fun createAttributes(
        isPast: Boolean = false, recipeId: Int = 0
    ): RecipeLikeAttributes = RecipeLikeAttributes(
        isPast = isPast, recipeId = recipeId
    )
}
