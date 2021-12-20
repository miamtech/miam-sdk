package com.example.kmm_miam_sdk.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable class RecipeDTO(
    @SerialName("data")
    val recipe: Recipe,
)



@Serializable
data class Recipe(

    @SerialName("id")
    val id: Int,

    @SerialName("attributes")
    val attributes: RecipeAttributes,
)

@Serializable
data class RecipeAttributes (
    @SerialName("title")
    val title: String,

    @SerialName("ext-id")
    val extId: String? = null,

    @SerialName("ext-link")
    val extLink: String? = null,

    @SerialName("description")
    val description: String,

    @SerialName("number-of-guests")
    val numberOfGuests: Int,

    @SerialName("ingredients-str")
    val ingredientsStr: Array<String>,

    @SerialName("media-url")
    val mediaUrl: String,
)


