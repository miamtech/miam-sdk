package com.miam.kmmMiamCore.miam_core.data.datasource

import com.miam.kmmMiamCore.miam_core.model.Supplier

public interface SupplierDataSource {
    public suspend fun notifyBasketUpdated(
        basketToken: String,
        supplierId: String,
        status: String,
        price: String? = null
    ): Unit

    public suspend fun getSupplier(supplierId: String): Supplier
}