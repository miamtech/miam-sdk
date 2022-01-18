package com.miam.kmm_miam_sdk.miam_core.model

import kotlinx.serialization.Serializable

@Serializable
data class SupplierWrapper(
    val data: Supplier,
)

@Serializable
data class Supplier(
    val id: Int,
    val attributes: SupplierAttributes,
)

@Serializable
data class SupplierAttributes(
    val name : String?,
    val description: String? =null,
    val logo: String? =null,
)