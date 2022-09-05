package com.miam.kmmMiamCore.miam_core.data.datasource

interface SupplierDataSource {
    suspend fun notifyBasketUpdated(
        basketToken: String,
        supplierId: Int,
        status: String,
        price: String? = null
    ): Unit
}