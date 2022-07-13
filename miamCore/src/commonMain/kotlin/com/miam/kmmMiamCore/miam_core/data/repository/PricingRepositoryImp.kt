package com.miam.kmmMiamCore.miam_core.data.repository

import com.miam.kmmMiamCore.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmmMiamCore.miam_core.model.Pricing
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PricingRepositoryImp (
    private val priceDataSource: MiamAPIDatasource
        ) : PricingRepository {

    override suspend fun getRecipePrice(recipeId: String, posId: Int): Pricing {
        return priceDataSource.getRecipePrice(recipeId, posId)
    }
}