package com.miam.kmmMiamCore.miam_core.data.datasource

import com.miam.kmmMiamCore.miam_core.model.PointOfSale

public interface PointOfSaleDataSource {
    public suspend fun getPosFormExtId(extId: String, supplierId: String): PointOfSale?
}