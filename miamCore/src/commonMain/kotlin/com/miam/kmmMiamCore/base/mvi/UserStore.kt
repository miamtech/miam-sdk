package com.miam.kmmMiamCore.base.mvi


import com.miam.kmmMiamCore.handler.LogHandler
import com.miam.kmmMiamCore.miam_core.data.repository.GroceriesListRepositoryImp
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

data class UserState(
    val userId: String?,
    val sessionId: String?,
    val profilingAllowed: Boolean = true,
    val likeIsEnable: Boolean = true
) : State

sealed class  UserAction : Action {
    data class RefreshUser(val idUser: String?) : UserAction()
}
sealed class UserEffect: Effect

class UserStore : Store<UserState, UserAction, UserEffect>, KoinComponent,
    CoroutineScope by CoroutineScope(Dispatchers.Main) {

    override val state = MutableStateFlow(UserState( null, null))
    private val sideEffect = MutableSharedFlow<UserEffect>()
    private val groceriesListStore:  GroceriesListStore by inject()


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

    fun getSessionId(): String? {
        return state.value.sessionId
    }

    fun setSessionId(sessionId: String) {
        updateStateIfChanged(state.value.copy(sessionId = sessionId))
    }

    fun sameSession(sessionId: String): Boolean {
        return sessionId == state.value.sessionId;
    }

    fun sameUser(userId: String?): Boolean {
        return userId == state.value.userId;
    }

    fun setProfilingAllowed(allowance: Boolean) {
        updateStateIfChanged(state.value.copy(profilingAllowed = allowance))
    }

    fun ProfilingForbiden(): Boolean {
        return !state.value.profilingAllowed
    }

    fun setEnableLike(isEnable: Boolean){
        updateStateIfChanged(state.value.copy(likeIsEnable= isEnable))
    }
}