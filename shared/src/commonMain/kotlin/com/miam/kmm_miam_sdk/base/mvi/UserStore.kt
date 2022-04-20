package com.miam.kmm_miam_sdk.base.mvi


import com.miam.kmm_miam_sdk.miam_core.data.repository.GroceriesListRepositoryImp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

data class UserState(
    val userId: String?,
    val sessionId: String?
) : State

sealed class  UserAction : Action {
    data class RefreshUser(val idUser: String?) : UserAction()
    data class SetSessionId(val idSession:String?): UserAction()
}
sealed class  UserEffect : Effect {
    data class Error(val error: Exception) :  UserEffect()
    data class UserChanged(val idUser: String) :  UserEffect()
}

class UserStore : Store<UserState, UserAction, UserEffect>, KoinComponent,
    CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private val state = MutableStateFlow(UserState( null, null))
    private val sideEffect = MutableSharedFlow<UserEffect>()
    private val  groceriesListStore:  GroceriesListStore by inject()


    override fun observeState(): StateFlow<UserState> = state

    override fun observeSideEffect(): Flow<UserEffect> = sideEffect

    override fun dispatch(action: UserAction) {
        val oldState = state.value

        val newState = when (action) {
            is UserAction.RefreshUser -> {
                //println("Miam --> basket RefreshUser")
                if (oldState.userId == action.idUser) {
                    // println("Miam --> same user")
                    oldState
                } else {
                    groceriesListStore.dispatch(GroceriesListAction.RefreshGroceriesList)
                    oldState.copy(userId = action.idUser)
                }

            }
            is UserAction.SetSessionId -> {
                if (oldState.sessionId == action.idSession) {
                    // println("Miam --> same user")
                    oldState
                } else {
                    oldState.copy(sessionId = action.idSession)
                }
            }

        }
        if (newState != oldState) {
            state.value = newState
        }
    }
}