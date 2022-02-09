package com.miam.kmm_miam_sdk.miam_core.data.datasource

import com.miam.kmm_miam_sdk.miam_core.model.Pricing

interface PricingDataSource {
    suspend fun getRecipePrice(idRecipe: Int, idPos: Int) : Pricing
}