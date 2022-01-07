package com.miam.kmm_miam_sdk.miam_core.model

import com.miam.kmm_miam_sdk.network.model.DAO.utils.DurationSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Duration


@Serializable
data class RecipeWrapper(val data: Recipe)

@Serializable
data class Recipe(val id: Int, val attributes: RecipeAttributes)

@Serializable
data class RecipeAttributes (

    val title: String,

    @SerialName("ext-id")
    val extId: String? = null,

    @SerialName("ext-link")
    val extLink: String? = null,

    val description: String?,

    @SerialName("number-of-guests")
    val numberOfGuests: Int?,

    @SerialName("ingredients-str")
    val ingredientsStr: List<String>?,

    @Serializable(with = DurationSerializer::class)
    @SerialName("preparation-time")
    val preparationTime: Duration?,

    @Serializable(with = DurationSerializer::class)
    @SerialName("preheating-time")
    val preheatingTime: Duration?,

    @Serializable(with = DurationSerializer::class)
    @SerialName("cooking-time")
    val cookingTime: Duration?,

    @Serializable(with = DurationSerializer::class)
    @SerialName("resting-time")
    val restingTime: Duration?,

    @SerialName("media-url")
    val mediaUrl: String?,

    val source : String?,

    @SerialName("informational-page-html")
    val informationalPageHtml: String?,

    @SerialName("filigrane-logo-url")
    val filigraneLogoUrl: String?,

    @SerialName("informational-sentence")
    val informationalSentence: String?,

    val difficulty : Int?,

    val cost : Int?,

    val suggested: Boolean?,

    val popularity: Int?,

    @SerialName("video-id")
    val videoId: String?,

    val promoted: Boolean?

    )




