package com.miam.kmmMiamCore.miam_core.data.repository

import com.miam.kmmMiamCore.base.mvi.PointOfSaleStore
import com.miam.kmmMiamCore.handler.LogHandler
import com.miam.kmmMiamCore.helpers.letElse
import com.miam.kmmMiamCore.miam_core.data.datasource.MiamAPIDatasource
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

public class SupplierRepositoryImp(private val recipeDataSource: MiamAPIDatasource): KoinComponent {

    private val pointOfSaleStore: PointOfSaleStore by inject()

    public suspend fun notifyConfirmBasket(basketToken: String) {
        letElse(
            pointOfSaleStore.supplierId,
            { supplierId -> recipeDataSource.notifyBasketUpdated(basketToken, supplierId, "CONFIRMED") },
            { LogHandler.error("SupplierRepositoryImp trying to notify confirm basket with null supplier id") }
        )
    }

    public suspend fun notifyPaidBasket(basketToken: String, price: String) {
        letElse(
            pointOfSaleStore.supplierId,
            { supplierId -> recipeDataSource.notifyBasketUpdated(basketToken, supplierId, "PAID", price) },
            { LogHandler.error("SupplierRepositoryImp trying to notify paid basket with null supplier id") }
        )
    }
}