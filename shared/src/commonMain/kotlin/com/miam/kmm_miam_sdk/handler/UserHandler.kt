package com.miam.kmm_miam_sdk.handler

import com.miam.kmm_miam_sdk.base.mvi.MiamAction
import com.miam.kmm_miam_sdk.base.mvi.MiamStore
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object UserHandler : KoinComponent {

    private val store: MiamStore by inject()

    fun updateUserId(userId: String){
        triggerAction(MiamAction.RefreshUserId(userId))
    }

    fun onLogout(){

    }

    fun onLogin(){

    }

   private fun triggerAction(action: MiamAction) {
        store.dispatch(action)
    }

}