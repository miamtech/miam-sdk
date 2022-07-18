package com.miam.kmmMiamCore.miam_core.data.datasource

import com.miam.kmmMiamCore.miam_core.model.Pricing

interface PricingDataSource {
    suspend fun getRecipePrice(idRecipe: String, idPos: Int) : Pricing
}