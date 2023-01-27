package com.miam.kmmMiamCore.handler

import com.miam.kmmMiamCore.KMMContext
import com.miam.kmmMiamCore.base.mvi.Effect
import com.miam.kmmMiamCore.base.mvi.State
import com.miam.kmmMiamCore.component.preferences.PreferencesViewModelInstance
import com.miam.kmmMiamCore.component.preferences.SingletonPreferencesViewModel
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

public object ContextHandlerInstance: KoinComponent {
    public val instance: ContextHandler by inject()
}

public data class ContextHandlerState(
    val isInError: Boolean = false,
    val applicationContext: KMMContext? = null
): State

public sealed class ReadyEvent: Effect {
    public object isReady: ReadyEvent()
    public object isNotReady: ReadyEvent()
}

public class ContextHandler: KoinComponent, CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        LogHandler.error("[ContextHandler] $exception ${exception.stackTraceToString()}")
    }

    private val basketHandler: BasketHandler by inject()
    private val preference: SingletonPreferencesViewModel by inject()

    public val state: MutableStateFlow<ContextHandlerState> = MutableStateFlow(ContextHandlerState())
    private val readyEvent = MutableSharedFlow<ReadyEvent>()

    public fun gotAnError() {
        state.value = state.value.copy(isInError = true)
        launch(coroutineHandler) {
            readyEvent.emit(ReadyEvent.isNotReady)
        }
    }

    public fun emitReadiness() {
        launch(coroutineHandler) {
            readyEvent.emit(if (isReady()) ReadyEvent.isReady else ReadyEvent.isNotReady)
        }
    }

    /**
     * called from app
     */

    public fun isReady(): Boolean {
        return basketHandler.isReady() && !state.value.isInError && preference.isInit
    }

    public fun observeReadyEvent(): Flow<ReadyEvent> = readyEvent

    public fun onReadyEvent(callback: (it: ReadyEvent) -> Unit) {
        launch(coroutineHandler) {
            readyEvent.asSharedFlow().collect { callback(it) }
        }
    }

    public fun setContext(context: KMMContext) {
        state.value = state.value.copy(applicationContext = context)
    }

    public fun getContextOrNull(): KMMContext? {
        val context = state.value.applicationContext
        if (context == null) LogHandler.error("[ContextHandler] Application context must be provided")
        return context
    }
}