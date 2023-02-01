package com.miam.kmmMiamCore.handler

import com.miam.core.sdk.di.MiamDI
import com.miam.kmmMiamCore.base.mvi.UserAction
import com.miam.kmmMiamCore.base.mvi.UserStore

public object UserHandler {

    private val store: UserStore = MiamDI.userStore

    public fun updateUserId(userId: String?) {
        if (store.sameUser(userId)) return
        store.refreshUser(userId)
    }

    public fun setProfilingAllowed(allowance: Boolean) {
        store.setProfilingAllowed(allowance)
    }

    public fun setEnableLike(isEnable: Boolean) {
        store.setEnableLike(isEnable)
    }
}
