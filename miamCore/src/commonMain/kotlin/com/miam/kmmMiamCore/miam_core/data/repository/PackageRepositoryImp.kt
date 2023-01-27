package com.miam.kmmMiamCore.miam_core.data.repository

import com.miam.kmmMiamCore.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmmMiamCore.miam_core.model.Package

public class PackageRepositoryImp(private val packageDataSource: MiamAPIDatasource): PackageRepository {

    override suspend fun getActivePackageForRetailer(retailerId: String, included: List<String>): List<Package> {
        return packageDataSource.getActivePackagesFromSupplierID(supplierId = retailerId, included)
    }

}