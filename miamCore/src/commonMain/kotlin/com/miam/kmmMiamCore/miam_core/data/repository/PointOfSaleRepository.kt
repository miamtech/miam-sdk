package com.miam.kmmMiamCore.miam_core.data.repository

import com.miam.kmmMiamCore.miam_core.model.PointOfSale

public interface PointOfSaleRepository {
    public suspend fun getPosFormExtId(extId: String, supplierId: Int): PointOfSale?
}