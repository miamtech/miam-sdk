package com.miam.kmm_miam_sdk.miam_core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Baskets(
    @SerialName("data")
    val baskets: List<Basket>,
)

@Serializable
data class Basket(
    val id: Int,
    val attributes: BasketAttributes,
)

@Serializable
data class BasketAttributes(
    val name : String?,
    val confirmed: Boolean? = false,
    val completion : BasketCompletion? = null,
    @SerialName("total-price")
    val totalPrice: Float? = null,
    @SerialName("capacity-factor")
    val capacityFactor: String? =null,
    val token :String? = null,

    var basketEntries: BasketEntries? = null,
)

@Serializable
data class  BasketCompletion(
    val total: Int? = 0,
    val found: Int? = 0
)