package com.miam.kmm_miam_sdk.handler

import com.miam.kmm_miam_sdk.base.mvi.UserAction
import com.miam.kmm_miam_sdk.base.mvi.UserStore
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object UserHandler: KoinComponent {

    private val store: UserStore by inject()

    fun updateUserId(userId: String?){
        if (store.sameUser(userId)) return

        store.dispatch(UserAction.RefreshUser(userId))
    }
}
