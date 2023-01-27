package com.miam.kmmMiamCore.miam_core.model.fake

import com.miam.kmmMiamCore.miam_core.model.RecipeStep
import com.miam.kmmMiamCore.miam_core.model.RecipeStepAttributes
import com.miam.kmmMiamCore.miam_core.model.RecipeStepRelationships

public object RecipeStepFakeFactory {

    public const val FAKE_ID: String = "recipeStepUUID"
    public const val FAKE_TITLE: String = "recipeStepTitle"

    public fun create(
        id: String = FAKE_ID,
        attributes: RecipeStepAttributes? = createAttributes(),
        relationships: RecipeStepRelationships? = null
    ): RecipeStep = RecipeStep(
        id = id,
        attributes = attributes,
        relationships = relationships
    )

    public fun createAttributes(
        stepNumber : Int= 0,
        title: String = FAKE_TITLE,
        stepDescription: String? = null,
        mediaUrl: String? = null
    ): RecipeStepAttributes = RecipeStepAttributes(
        stepNumber = stepNumber,
        title = title,
        stepDescription = stepDescription,
        mediaUrl = mediaUrl
    )
}
