package com.miam.kmm_miam_sdk.miam_core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SuggestionsCriteria(
    @SerialName("shelf_ingredients_ids")
    val shelfIngredientsIds: List<String>?,

    @SerialName("current_ingredients_ids")
    val currentIngredientsIds: List<String>?,

    @SerialName("basket_ingredients_ids")
    val basketIngredientsIds: List<String>?,

    @SerialName("group_id")
    val groupId: String?
)