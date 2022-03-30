package com.miam.kmm_miam_sdk.miam_core.data.repository

import com.miam.kmm_miam_sdk.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmm_miam_sdk.miam_core.model.PointOfSale
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PointOfSaleRepositoryImp ( private val pointOfSaleDataSource: MiamAPIDatasource) : PointOfSaleRepository {
    override suspend fun getPosFormExtId(extId: String, supplierId: Int): PointOfSale {
       return pointOfSaleDataSource.getPosFormExtId(extId, supplierId)
    }
}