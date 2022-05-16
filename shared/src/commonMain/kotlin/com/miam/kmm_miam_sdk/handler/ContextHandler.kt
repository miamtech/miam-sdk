package com.miam.kmm_miam_sdk.handler

import com.miam.kmm_miam_sdk.base.mvi.BasketEffect
import com.miam.kmm_miam_sdk.base.mvi.Effect
import com.miam.kmm_miam_sdk.base.mvi.State
import com.miam.kmm_miam_sdk.handler.Basket.BasketHandler
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object ContextHandlerInstance: KoinComponent {
    val instance: ContextHandler by inject()
}

data class ContextHandlerState(
    val isInError: Boolean = false
): State

sealed class ReadyEvent: Effect {
    object isReady: ReadyEvent()
    object isNotReady: ReadyEvent()
}

class ContextHandler: KoinComponent, CoroutineScope by CoroutineScope(Dispatchers.Main) {
    private val coroutineHandler = CoroutineExceptionHandler {
            _, exception -> println("Miam error in BasketStore $exception")
    }

    private val basketHandler: BasketHandler by inject()

    val state = MutableStateFlow(ContextHandlerState())
    private val readyEvent = MutableSharedFlow<ReadyEvent>()
    fun observeReadyEvent(): Flow<ReadyEvent> = readyEvent

    fun gotAnError() {
        state.value = state.value.copy(isInError = true)
        launch(coroutineHandler) {
            readyEvent.emit(ReadyEvent.isNotReady)
        }
    }

    fun getReady() {
        if (isReady()) {
            launch(coroutineHandler) {
                readyEvent.emit(ReadyEvent.isReady)
            }
        }
    }

    /**
     * called from app
     */

    fun isReady(): Boolean {
        return basketHandler.isReady() && !state.value.isInError
    }
}