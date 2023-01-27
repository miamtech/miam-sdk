package com.miam.kmmMiamCore.miam_core.model

import kotlinx.serialization.Serializable

@Serializable
public data class SupplierWrapper(
    val data: Supplier,
)

@Serializable
public data class Supplier(
    val id: Int,
    val attributes: SupplierAttributes,
)

@Serializable
public data class SupplierAttributes(
    val name: String?,
    val description: String? = null,
    val logo: String? = null,
)

@Serializable
public data class SupplierNotificationWrapper(
    val token: String,
    val status: String,
    val price: String?
)