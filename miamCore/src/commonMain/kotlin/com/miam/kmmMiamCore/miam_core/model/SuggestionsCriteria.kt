package com.miam.kmmMiamCore.miam_core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SuggestionsCriteria(
    @SerialName("shelf_ingredients_ids")
    val shelfIngredientsIds: List<String>? = null,

    @SerialName("current_ingredients_ids")
    val currentIngredientsIds: List<String>? = null,

    @SerialName("basket_ingredients_ids")
    val basketIngredientsIds: List<String>? = null,

    @SerialName("group_id")
    val groupId: String? = null
)
