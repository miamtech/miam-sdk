package com.miam.kmmMiamCore.miam_core.data.repository

import com.miam.kmmMiamCore.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmmMiamCore.miam_core.model.PointOfSale

internal class PointOfSaleRepositoryImp(private val pointOfSaleDataSource: MiamAPIDatasource):
    PointOfSaleRepository {
    override suspend fun getPosFormExtId(extId: String, supplierId: String): PointOfSale? {
        return pointOfSaleDataSource.getPosFormExtId(extId, supplierId)
    }
}