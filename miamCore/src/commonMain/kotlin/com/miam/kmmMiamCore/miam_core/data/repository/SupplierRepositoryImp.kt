package com.miam.kmmMiamCore.miam_core.data.repository

import com.miam.core.sdk.di.MiamDI
import com.miam.kmmMiamCore.base.mvi.PointOfSaleStore
import com.miam.kmmMiamCore.handler.LogHandler
import com.miam.kmmMiamCore.helpers.letElse
import com.miam.kmmMiamCore.miam_core.data.datasource.MiamAPIDatasource
import com.miam.kmmMiamCore.miam_core.model.Supplier


public interface SupplierRepository {
    public suspend fun notifyConfirmBasket(basketToken: String)
    public suspend fun notifyPaidBasket(basketToken: String, price: String)
    public suspend fun getSupplier(supplierId: Int): Supplier
}

public class SupplierRepositoryImp(private val recipeDataSource: MiamAPIDatasource): SupplierRepository {

    private val pointOfSaleStore: PointOfSaleStore by lazy { MiamDI.pointOfSaleStore }

    public override suspend fun notifyConfirmBasket(basketToken: String) {
        letElse(
            pointOfSaleStore.supplierId,
            { supplierId -> recipeDataSource.notifyBasketUpdated(basketToken, supplierId, "CONFIRMED") },
            { LogHandler.error("SupplierRepositoryImp trying to notify confirm basket with null supplier id") }
        )
    }

    public override suspend fun notifyPaidBasket(basketToken: String, price: String) {
        letElse(
            pointOfSaleStore.supplierId,
            { supplierId -> recipeDataSource.notifyBasketUpdated(basketToken, supplierId, "PAID", price) },
            { LogHandler.error("SupplierRepositoryImp trying to notify paid basket with null supplier id") }
        )
    }

    override suspend fun getSupplier(supplierId: Int): Supplier {
        return recipeDataSource.getSupplier(supplierId)
    }
}