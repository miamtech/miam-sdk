package com.miam.kmm_miam_sdk.miam_core.data.datasource

import com.miam.kmm_miam_sdk.miam_core.model.PointOfSale

interface PointOfSaleDataSource {
    suspend fun getPosFormExtId(extId: String, supplierId: Int): PointOfSale
}