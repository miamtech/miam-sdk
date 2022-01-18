package com.miam.kmm_miam_sdk.miam_core.model

import kotlinx.serialization.Serializable


@Serializable
data class RecipeProviderWrapper(val data: RecipeProvider)

@Serializable
data class RecipeProvider(
    val id: String,
    val attributes: RecipeProviderAttributes,
)

@Serializable
data class RecipeProviderAttributes(
    val name : String?,
)