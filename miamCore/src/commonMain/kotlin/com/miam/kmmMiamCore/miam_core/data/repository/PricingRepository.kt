package com.miam.kmmMiamCore.miam_core.data.repository

import com.miam.kmmMiamCore.miam_core.model.Pricing

public interface PricingRepository {
    public suspend fun getRecipePrice(recipeId: String, posId: Int, serves: Int?): Pricing
}