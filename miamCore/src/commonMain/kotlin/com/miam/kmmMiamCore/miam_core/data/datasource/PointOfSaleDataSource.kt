package com.miam.kmmMiamCore.miam_core.data.datasource

import com.miam.kmmMiamCore.miam_core.model.PointOfSale

interface PointOfSaleDataSource {
    suspend fun getPosFormExtId(extId: String, supplierId: Int): PointOfSale
}