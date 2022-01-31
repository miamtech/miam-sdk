package com.miam.kmm_miam_sdk.miam_core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Items(
    @SerialName("data")
    val items: List<Item>
)

@Serializable
data class Item (
    val id: Int,
    val attributes: ItemAttributes,
)

@Serializable
data class ItemAttributes (
    val name: String? = "",
    val status: String?= "",
    val description: String? = "",
    val brand: String? = "",
    @SerialName("product-page")
    val productPage: String? = "",
    val image : String? = "",
    @SerialName("unit-price")
    val unitPrice: Float? = null,
    val currency: String? = "",
    @SerialName("capacity-unit")
    val capacityUnit: String? = null,
    @SerialName("capacity-volume")
    val capacityVolume: String? = null,
    @SerialName("capacity-factor")
    val capacityFactor: Int? = 1,
    @SerialName("created-at")
    val createdAt: String? = null,
    @SerialName("updated-at")
    val updatedAt: String? = null,
    val promoted : Boolean = false,
    @SerialName("variable-capacity")
    val variableCapacity: Boolean? = false
)