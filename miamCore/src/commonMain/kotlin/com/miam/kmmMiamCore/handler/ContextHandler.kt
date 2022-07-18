package com.miam.kmmMiamCore.handler

import com.miam.kmmMiamCore.base.mvi.Effect
import com.miam.kmmMiamCore.base.mvi.State
import com.miam.kmmMiamCore.handler.Basket.BasketHandler
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
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
            _, exception -> println("Miam error in BasketStore $exception ${exception.stackTraceToString()}")
    }

    private val basketHandler: BasketHandler by inject()

    val state = MutableStateFlow(ContextHandlerState())
    private val readyEvent = MutableSharedFlow<ReadyEvent>()

    fun gotAnError() {
        state.value = state.value.copy(isInError = true)
        launch(coroutineHandler) {
            readyEvent.emit(ReadyEvent.isNotReady)
        }
    }

    fun emitReadiness() {
        launch(coroutineHandler) {
            readyEvent.emit(if (isReady()) ReadyEvent.isReady else ReadyEvent.isNotReady)

        }
    }

    /**
     * called from app
     */

    fun isReady(): Boolean {
        return basketHandler.isReady() && !state.value.isInError
    }

    fun observeReadyEvent(): Flow<ReadyEvent> = readyEvent

    @OptIn(InternalCoroutinesApi::class)
    fun onReadyEvent(callback: (it: ReadyEvent) -> Unit){
        launch(coroutineHandler) {
            readyEvent.asSharedFlow().collect { callback(it) }
        }
    }
}