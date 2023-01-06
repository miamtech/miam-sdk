package com.miam.kmmMiamCore.miam_core.data.repository

import com.miam.kmmMiamCore.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmmMiamCore.miam_core.model.PointOfSale

class PointOfSaleRepositoryImp(private val pointOfSaleDataSource: MiamAPIDatasource):
    PointOfSaleRepository {
    override suspend fun getPosFormExtId(extId: String, supplierId: Int): PointOfSale? {
        return pointOfSaleDataSource.getPosFormExtId(extId, supplierId)
    }
}