package com.miam.kmmMiamCore.base.mvi


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

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

public class UserStore : Store<UserState, UserAction, UserEffect>, KoinComponent,
    CoroutineScope by CoroutineScope(Dispatchers.Main) {

    override val state: MutableStateFlow<UserState> = MutableStateFlow(UserState(null, null))
    private val sideEffect = MutableSharedFlow<UserEffect>()
    private val groceriesListStore: GroceriesListStore by inject()


    override fun observeState(): StateFlow<UserState> = state

    override fun observeSideEffect(): Flow<UserEffect> = sideEffect

    override fun dispatch(action: UserAction): Job {
        when (action) {
            is UserAction.RefreshUser -> {
                updateStateIfChanged(state.value.copy(userId = action.idUser))
                return launch {
                    if (state.value.userId != null) {
                        groceriesListStore.dispatch(GroceriesListAction.RefreshGroceriesList)
                    }
                }
            }
        }
    }

    public fun getSessionId(): String? {
        return state.value.sessionId
    }

    public fun setSessionId(sessionId: String) {
        updateStateIfChanged(state.value.copy(sessionId = sessionId))
    }

    public fun sameSession(sessionId: String): Boolean {
        return sessionId == state.value.sessionId
    }

    public fun sameUser(userId: String?): Boolean {
        return userId == state.value.userId
    }

    public fun setProfilingAllowed(allowance: Boolean) {
        updateStateIfChanged(state.value.copy(profilingAllowed = allowance))
    }

    public fun ProfilingForbiden(): Boolean {
        return !state.value.profilingAllowed
    }

    public fun setEnableLike(isEnable: Boolean) {
        updateStateIfChanged(state.value.copy(likeIsEnable = isEnable))
    }
}