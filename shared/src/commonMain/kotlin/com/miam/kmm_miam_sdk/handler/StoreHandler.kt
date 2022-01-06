package com.miam.kmm_miam_sdk.handler


import com.miam.kmm_miam_sdk.base.mvi.MiamAction
import com.miam.kmm_miam_sdk.base.mvi.MiamStore
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object  StoreHandler : KoinComponent {

    var isAvailable = fun():Boolean{return true}
    private val store: MiamStore by inject()

    fun updateStoreId(storeId :String){
        triggerAction(MiamAction.RefreshStoreId(storeId))
    }

   private fun triggerAction(action: MiamAction) {
        store.dispatch(action)
    }

}

