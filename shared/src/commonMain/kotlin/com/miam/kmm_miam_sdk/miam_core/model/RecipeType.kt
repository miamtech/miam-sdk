package com.miam.kmm_miam_sdk.miam_core.model

import kotlinx.serialization.Serializable


@Serializable
data class RecipeTypeWrapper(
    val data: RecipeType,
)

@Serializable
data class RecipeType(
    val id: Int,
    val attributes: RecipeTypeAttributes,
)

@Serializable
data class RecipeTypeAttributes(
    val name : String?,
)