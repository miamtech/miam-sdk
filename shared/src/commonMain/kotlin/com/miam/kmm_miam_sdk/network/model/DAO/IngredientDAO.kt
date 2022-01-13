package com.miam.kmm_miam_sdk.network.model.DAO


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IngredientsDAO(
    @SerialName("data")
    val ingredients: List<IngredientDAO>
)

@Serializable
data class IngredientDAO(
    val id: Int,
    val attributes: IngredientAttributes,
) :MiamObjectDAO

@Serializable
data class IngredientAttributes(
    val name : String?,
    val quantity : Int?,
    val unit : String?,
    val active : Boolean = true,

    @SerialName("forced-eans")
    val forcedEans: List<String>? = emptyList<String>(),
)


