package com.miam.kmmMiamCore.handler

import com.miam.kmmMiamCore.base.mvi.UserAction
import com.miam.kmmMiamCore.base.mvi.UserStore
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

public object UserHandler : KoinComponent {

    private val store: UserStore by inject()

    public fun updateUserId(userId: String?) {
        if (store.sameUser(userId)) return

        store.dispatch(UserAction.RefreshUser(userId))
    }

    public fun setProfilingAllowed(allowance: Boolean) {
        store.setProfilingAllowed(allowance)
    }

    public fun setEnableLike(isEnable: Boolean) {
        store.setEnableLike(isEnable)
    }
}
