package com.miam.kmm_miam_sdk.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class SponsorDTO(
    @SerialName("data")
    val sponsor: Sponsor,
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