package com.miam.kmmMiamCore.miam_core.data.datasource

public interface SupplierDataSource {
    public suspend fun notifyBasketUpdated(
        basketToken: String,
        supplierId: Int,
        status: String,
        price: String? = null
    ): Unit
}