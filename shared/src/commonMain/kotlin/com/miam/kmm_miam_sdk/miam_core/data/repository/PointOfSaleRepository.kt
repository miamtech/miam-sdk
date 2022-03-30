package com.miam.kmm_miam_sdk.miam_core.data.repository

import com.miam.kmm_miam_sdk.miam_core.model.PointOfSale
import kotlinx.coroutines.flow.Flow

interface PointOfSaleRepository {
   suspend fun getPosFormExtId(extId: String, supplierId: Int): PointOfSale
}