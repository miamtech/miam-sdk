package com.miam.kmmMiamCore.miam_core.data.repository

import com.miam.kmmMiamCore.miam_core.model.Package

public interface PackageRepository {
    public suspend fun getActivePackageForRetailer(retailerId: String, included: List<String> = listOf()): List<Package>
}