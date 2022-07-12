package com.miam.kmm_miam_sdk.miam_core.data.repository

import com.miam.kmm_miam_sdk.miam_core.model.Pricing
import kotlinx.coroutines.flow.Flow

interface PricingRepository {
    suspend fun getRecipePrice(recipeId: String, posId: Int) : Pricing
}