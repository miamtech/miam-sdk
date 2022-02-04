package com.miam.kmm_miam_sdk.miam_core.data.repository

import com.miam.kmm_miam_sdk.miam_core.model.Pricing
import kotlinx.coroutines.flow.Flow

interface PricingRepository {
    fun getRecipePrice(recipeId: Int, posId: Int) : Flow<Pricing>
}