package com.miam.kmm_miam_sdk.miam_core.data.repository

import com.miam.kmm_miam_sdk.miam_core.model.Package

interface PackageRepository {
    suspend fun getActivePackageForRetailer(retailerId: String): List<Package>
}