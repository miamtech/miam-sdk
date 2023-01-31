package com.miam.kmmMiamCore.base.mvi


import com.miam.core.sdk.di.MiamDI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

public data class UserState(
    val userId: String?,
    val sessionId: String?,
    val profilingAllowed: Boolean = true,
    val likeIsEnable: Boolean = true
) : State

public sealed class UserAction : Action {
    public data class RefreshUser(val idUser: String?) : UserAction()
}

public sealed class UserEffect : Effect

public interface UserStore: Store<UserState, UserAction, UserEffect> {
    public fun refreshUser(userId: String?)
    public fun getSessionId(): String?
    public fun setSessionId(sessionId: String)
    public fun sameSession(sessionId: String): Boolean
    public fun sameUser(userId: String?): Boolean
    public fun setProfilingAllowed(allowance: Boolean)
    public fun ProfilingForbiden(): Boolean
    public fun setEnableLike(isEnable: Boolean)
}

public class UserStoreImpl(
    private val groceriesListStore: GroceriesListStore
) : UserStore, CoroutineScope by MainScope() {

    override val state: MutableStateFlow<UserState> = MutableStateFlow(UserState(null, null))
    private val sideEffect = MutableSharedFlow<UserEffect>()

    override fun observeState(): StateFlow<UserState> = state

    override fun observeSideEffect(): Flow<UserEffect> = sideEffect

    override fun dispatch(action: UserAction): Job = TODO("Remove the use of Store.dispatch function.")

    override fun refreshUser(userId: String?) {
        state.value = state.value.copy(userId = userId)
        // TODO Romain: Should be handled elsewhere
        if (userId != null) {
            groceriesListStore.dispatch(GroceriesListAction.RefreshGroceriesList)
        }
    }

    public override fun getSessionId(): String? {
        return state.value.sessionId
    }

    public override fun setSessionId(sessionId: String) {
        updateStateIfChanged(state.value.copy(sessionId = sessionId))
    }

    public override fun sameSession(sessionId: String): Boolean {
        return sessionId == state.value.sessionId
    }

    public override fun sameUser(userId: String?): Boolean {
        return userId == state.value.userId
    }

    public override fun setProfilingAllowed(allowance: Boolean) {
        updateStateIfChanged(state.value.copy(profilingAllowed = allowance))
    }

    public override fun ProfilingForbiden(): Boolean {
        return !state.value.profilingAllowed
    }

    public override fun setEnableLike(isEnable: Boolean) {
        updateStateIfChanged(state.value.copy(likeIsEnable = isEnable))
    }
}