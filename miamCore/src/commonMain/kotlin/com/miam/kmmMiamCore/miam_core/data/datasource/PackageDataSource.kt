package com.miam.kmmMiamCore.miam_core.data.datasource

import com.miam.kmmMiamCore.miam_core.model.Package

public interface PackageDataSource {
    public suspend fun getActivePackagesFromSupplierID(supplierId: String, included: List<String>): List<Package>
}