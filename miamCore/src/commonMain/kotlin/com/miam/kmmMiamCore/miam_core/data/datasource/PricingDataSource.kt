package com.miam.kmmMiamCore.miam_core.data.datasource

import com.miam.kmmMiamCore.miam_core.model.Pricing

public interface PricingDataSource {
    public suspend fun getRecipePrice(idRecipe: String, idPos: Int, serves: Int?): Pricing
}