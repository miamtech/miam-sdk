package com.miam.kmm_miam_sdk.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class RecipeProviderDTO(
    @SerialName("data")
    val recipeProvider: RecipeProvider,
)

@Serializable
data class RecipeProvider(
    val id: Int,
    val attributes: RecipeProviderAttributes,
)

@Serializable
data class RecipeProviderAttributes(
    val name : String?,
)