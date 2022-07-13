package com.miam.kmmMiamCore.miam_core.data.repository

import com.miam.kmmMiamCore.miam_core.model.PointOfSale
import kotlinx.coroutines.flow.Flow

interface PointOfSaleRepository {
   suspend fun getPosFormExtId(extId: String, supplierId: Int): PointOfSale
}