package com.miam.kmm_miam_sdk.miam_core.data.repository

import com.miam.kmm_miam_sdk.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmm_miam_sdk.miam_core.model.Package

class PackageRepositoryImp (private val packageDataSource: MiamAPIDatasource) : PackageRepository{

    override suspend fun getActivePackageForRetailer(retailerId: String): List<Package> {
       return packageDataSource.getActivePackagesFromSupplierID(supplierId = retailerId)
    }

}