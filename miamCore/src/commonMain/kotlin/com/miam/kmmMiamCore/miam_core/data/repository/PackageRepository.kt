package com.miam.kmmMiamCore.miam_core.data.repository

import com.miam.kmmMiamCore.miam_core.model.Package

interface PackageRepository {
    suspend fun getActivePackageForRetailer(retailerId: String): List<Package>
}