package com.miam.kmmMiamCore.miam_core.data.repository

import com.miam.kmmMiamCore.miam_core.model.Pricing
import kotlinx.coroutines.flow.Flow

interface PricingRepository {
    suspend fun getRecipePrice(recipeId: String, posId: Int) : Pricing
}