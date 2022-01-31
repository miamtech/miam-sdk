package com.miam.kmm_miam_sdk.handler


import com.miam.kmm_miam_sdk.base.mvi.PointOfSaleAction
import com.miam.kmm_miam_sdk.base.mvi.PointOfSaleStore
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object  PointOfSaleHandler : KoinComponent {

    var isAvailable = fun():Boolean{return true}
    private val store: PointOfSaleStore by inject()

    fun updateStoreId(storeId :String){
        triggerAction(PointOfSaleAction.SetExtId(storeId))
    }

    fun setSupplier(supplierId: Int){
        triggerAction(PointOfSaleAction.SetSupplierId(supplierId))
    }

   private fun triggerAction(action: PointOfSaleAction) {
        store.dispatch(action)
    }

}

