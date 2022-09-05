package com.miam.kmmMiamCore.miam_core.data.repository

import com.miam.kmmMiamCore.base.mvi.PointOfSaleStore
import com.miam.kmmMiamCore.miam_core.data.datasource.MiamAPIDatasource
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SupplierRepositoryImp(private val recipeDataSource: MiamAPIDatasource) : KoinComponent {

    private val pointOfSaleStore: PointOfSaleStore by inject()

    suspend fun notifyConfirmBasket(basketToken: String) {
        val providerId = pointOfSaleStore.getProviderId()

        recipeDataSource.notifyBasketUpdated(basketToken, providerId, "CONFIRMED")
    }

    suspend fun notifyPaidBasket(basketToken: String, price: String) {
        val providerId = pointOfSaleStore.getProviderId()
        recipeDataSource.notifyBasketUpdated(basketToken, providerId, "PAID", price)
    }
}