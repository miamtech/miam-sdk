package com.miam.kmmMiamCore.miam_core.data.repository

import com.miam.kmmMiamCore.miam_core.model.PointOfSale

interface PointOfSaleRepository {
    suspend fun getPosFormExtId(extId: String, supplierId: Int): PointOfSale?
}