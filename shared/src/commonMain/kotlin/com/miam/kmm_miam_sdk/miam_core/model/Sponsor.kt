package com.miam.kmm_miam_sdk.miam_core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Sponsors(
    val sponsors:  List<Sponsor>,
)

@Serializable
data class Sponsor(
    val id: Int,
    val attributes: SponsorAttributes,
)

@Serializable
data class SponsorAttributes(
    val name : String?,
    @SerialName("logo-url")
    val logoUrl: String?
)