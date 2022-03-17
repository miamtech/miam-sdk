package com.miam.kmm_miam_sdk.miam_core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Baskets(
    @SerialName("data")
    val baskets: List<Basket>,
)

@Serializable
data class BasketWrapper(
    val data : Basket
)

@Serializable
data class Basket(
    val id: Int,
    val type: String? = null,
    val attributes: BasketAttributes,
){
    var _relationships: BasketRelationships? = null
}

@Serializable
data class BasketAttributes(
    val name : String?,
    val confirmed: Boolean? = false,
    val completion : BasketCompletion? = null,
    @SerialName("total-price")
    val totalPrice: Float? = null,
    @SerialName("capacity-factor")
    val capacityFactor: Int? =null,
    val token :String? = null,
)

@Serializable
data class BasketRelationships(
    var basketEntries: List<BasketEntry>? = emptyList<BasketEntry>(),
)

@Serializable
data class  BasketCompletion(
    val total: Int? = 0,
    val found: Int? = 0
)