package com.miam.kmmMiamCore.handler

import com.miam.kmmMiamCore.base.mvi.UserAction
import com.miam.kmmMiamCore.base.mvi.UserStore
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object UserHandler : KoinComponent {

    private val store: UserStore by inject()

    fun updateUserId(userId: String?) {
        if (store.sameUser(userId)) return

        store.dispatch(UserAction.RefreshUser(userId))
    }

    fun setProfilingAllowed(allowance: Boolean) {
        store.setProfilingAllowed(allowance)
    }

    fun setEnableLike(isEnable: Boolean) {
        store.setEnableLike(isEnable)
    }
}
