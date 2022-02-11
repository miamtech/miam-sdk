package com.miam.kmm_miam_sdk.miam_core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Ingredients(
    @SerialName("data")
    val ingredients: List<Ingredient>
)

@Serializable
data class Ingredient(
    val id: Int,
    val attributes: IngredientAttributes,
)

@Serializable
data class IngredientAttributes(
    val name : String?,
    val quantity : String?,
    val unit : String?,
    val active : Boolean = true,
    var display : String?,

    @SerialName("forced-eans")
    val forcedEans: List<String>? = emptyList<String>(),
)
