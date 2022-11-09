package com.miam.kmmMiamCore.handler

import com.miam.kmmMiamCore.KMMContext
import com.miam.kmmMiamCore.base.mvi.Effect
import com.miam.kmmMiamCore.base.mvi.State
import com.miam.kmmMiamCore.handler.Basket.BasketHandler
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object ContextHandlerInstance: KoinComponent {
    val instance: ContextHandler by inject()
}

data class ContextHandlerState(
    val isInError: Boolean = false,
    val applicationContext: KMMContext? = null
): State

sealed class ReadyEvent: Effect {
    object isReady: ReadyEvent()
    object isNotReady: ReadyEvent()
}

class ContextHandler: KoinComponent, CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        LogHandler.error("[ContextHandler] $exception ${exception.stackTraceToString()}")
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
        return basketHandler.isReady() && !state.value.isInError && state.value.applicationContext != null
    }

    fun observeReadyEvent(): Flow<ReadyEvent> = readyEvent

    fun onReadyEvent(callback: (it: ReadyEvent) -> Unit) {
        launch(coroutineHandler) {
            readyEvent.asSharedFlow().collect { callback(it) }
        }
    }

    fun setContext(context: KMMContext) {
        state.value = state.value.copy(applicationContext = context)
    }

    fun getContextOrNull(): KMMContext? {
        val context = state.value.applicationContext
        if (context == null) LogHandler.error("[ContextHandler] Application context must be provided")
        return context
    }
}