package com.miam.kmm_miam_sdk.miam_core.data.datasource

interface SupplierDataSource {
    suspend fun notifyBasketUpdated(basketToken: String,supplierId: Int, status: String, price: String? = null): Unit
}