package com.miam.kmmMiamCore.miam_core.data.datasource

import com.miam.kmmMiamCore.miam_core.model.Package

interface PackageDataSource {
    suspend fun getActivePackagesFromSupplierID(supplierId: String) : List<Package>
}