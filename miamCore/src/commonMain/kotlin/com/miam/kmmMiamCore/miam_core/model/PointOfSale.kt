package com.miam.kmmMiamCore.miam_core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class PointOfSaleWrapper(
    val data: PointOfSale,
)

@Serializable
public data class PointOfSales(
    val data: List<PointOfSale>,
)

@Serializable
public data class PointOfSale(
    val id: Int,
    val attributes: PointOfSaleAttributes,
)

@Serializable
public data class PointOfSaleAttributes(
    val name: String?,

    val settings: PointOfSaleSettings? = null,
    @SerialName("active-items-count")
    val activeItemsCount: Int? = 0,
    val distance: String? = null,
    val address: String? = null,
    val latitude: String? = null,
    val longitude: String? = null,

    var supplier: Supplier? = null
)

@Serializable
public data class PointOfSaleSettings(
    val drive: PointOfSaleOption? = null,
    val delivery: PointOfSaleOption? = null,
    val mobile_warning: Boolean = false
)

@Serializable
public data class PointOfSaleOption(
    val enabled: Boolean? = true,
    val logo: String = ""
)
