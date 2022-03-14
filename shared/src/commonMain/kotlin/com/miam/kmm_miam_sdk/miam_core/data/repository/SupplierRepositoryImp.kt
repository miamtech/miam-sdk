package com.miam.kmm_miam_sdk.miam_core.data.repository

import com.miam.kmm_miam_sdk.base.mvi.PointOfSaleStore
import com.miam.kmm_miam_sdk.miam_core.data.datasource.MiamAPIDatasource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SupplierRepositoryImp( private val recipeDataSource: MiamAPIDatasource) :KoinComponent {

    private val pointOfSaleStore: PointOfSaleStore by inject()

    fun notifyConfirmBasket(basketToken: String) : Flow<Unit> = flow{
        val providerId =  pointOfSaleStore.getProviderId()

        recipeDataSource.notifyBasketUpdated(basketToken,providerId,"CONFIRMED")
        emit(Unit)
    }

    fun notifyPaidBasket(basketToken: String, price: String) : Flow<Unit> = flow{
        val providerId =  pointOfSaleStore.getProviderId()
        val origin  = pointOfSaleStore.getProviderOrigin()
        recipeDataSource.notifyBasketUpdated(basketToken,providerId,"PAID",price)
        emit(Unit)
    }
}