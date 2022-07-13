package com.miam.kmmMiamCore.handler


import com.miam.kmmMiamCore.base.mvi.PointOfSaleAction
import com.miam.kmmMiamCore.base.mvi.PointOfSaleStore
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object  PointOfSaleHandler: KoinComponent {

    var isAvailable = fun():Boolean { return true }
    private val store: PointOfSaleStore by inject()

    fun updateStoreId(storeId :String?){
        if (store.samePos(storeId)) return;

        store.dispatch(PointOfSaleAction.SetExtId(storeId))
    }

    fun setSupplier(supplierId: Int){
        if (store.sameSupplier(supplierId)) return;

        store.dispatch(PointOfSaleAction.SetSupplierId(supplierId))
    }

    fun setSupplierOrigin(origin : String){
        store.setOrigin(origin)
    }
}
