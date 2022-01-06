package com.miam.kmm_miam_sdk.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class RecipeStatusDTO(
    @SerialName("data")
    val recipeStatus: RecipeStatus,
)

@Serializable
data class RecipeStatus(
    val id: Int,
    val attributes: RecipeStatusAttributes,
)

@Serializable
data class RecipeStatusAttributes(
    val name: String?,
    val label:String?
)