package com.miam.kmm_miam_sdk.base.mvi

import com.miam.kmm_miam_sdk.handler.StoreHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class MiamState(

    val storeId:String,
    val userId: String,
    // val basketActionInProgress : boolean,
    // val userBasket :Basket,
) : State

sealed class MiamAction : Action {
    data class RefreshStoreId(val idStore: String) : MiamAction()
    data class RefreshUserId(val idUser :String) : MiamAction()
    /*data class AddProduct(): MiamAction()
    data class RemoveProduct(): MiamAction()*/
    object ReloadBasket : MiamAction()
    data class Error(val error: Exception) : MiamAction()
}

sealed class MiamSideEffect : Effect {
    data class Error(val error: Exception) : MiamSideEffect()
    data class StoreIdChanged(val idStore: String) : MiamSideEffect()
    data class UserLogging(val isLoginIn: Boolean ) : MiamSideEffect()
}

class MiamStore : Store<MiamState, MiamAction, MiamSideEffect>,
    CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private val state = MutableStateFlow(MiamState("",""))
    private val sideEffect = MutableSharedFlow<MiamSideEffect>()

    override fun observeState(): StateFlow<MiamState> = state

    override fun observeSideEffect(): Flow<MiamSideEffect> = sideEffect

    override fun dispatch(action: MiamAction) {
        val oldState = state.value

        val newState = when (action) {
            is MiamAction.RefreshStoreId -> {
                if(action.idStore != "" && StoreHandler.isAvailable()){
                    // init Store
                    if(oldState.storeId == ""  ){
                        println("init store id")
                    } else {
                        println("store change")
                    }
                }
                oldState.copy(storeId = action.idStore)
            }
            is MiamAction.RefreshUserId -> {
                launch { sideEffect.emit(MiamSideEffect.UserLogging(action.idUser != null && action.idUser != ""))}
                oldState.copy(userId = action.idUser)
            }
            is MiamAction.ReloadBasket -> {
                oldState
            }
            is MiamAction.Error -> {
                oldState
            }
        }
        if (newState != oldState) {
            state.value = newState
        }
    }
}