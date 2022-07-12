package com.miam.kmm_miam_sdk.miam_core.data.datasource

import com.miam.kmm_miam_sdk.miam_core.model.Package

interface PackageDataSource {
    suspend fun getActivePackagesFromSupplierID(supplierId: String) : List<Package>
}