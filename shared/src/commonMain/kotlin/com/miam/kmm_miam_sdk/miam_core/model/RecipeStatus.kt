package com.miam.kmm_miam_sdk.miam_core.model

import kotlinx.serialization.Serializable


@Serializable
data class RecipeStatusWrapper(
    val data: RecipeStatus,
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