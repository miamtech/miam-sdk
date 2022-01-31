package com.miam.kmm_miam_sdk.miam_core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeSteps(
    @SerialName("data")
    val steps: List<RecipeStep>
)

@Serializable
data class RecipeStep(
    val id: Int,
    val attributes: RecipeStepAttributes,
)

@Serializable
data class RecipeStepAttributes(

    @SerialName("step-number")
    val stepNumber: Int,

    val title : String?,
    val description : String?,

    @SerialName("media-url")
    val mediaUrl: String?
)