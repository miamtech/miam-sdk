package com.miam.kmmMiamCore.handler

import com.miam.core.sdk.di.MiamDI
import com.miam.kmmMiamCore.KMMContext
import com.miam.kmmMiamCore.base.mvi.Effect
import com.miam.kmmMiamCore.base.mvi.State
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

public object ContextHandlerInstance {
    public val instance: ContextHandler = MiamDI.contextHandler
}

public data class ContextHandlerState(
    val isInError: Boolean = false,
    val applicationContext: KMMContext? = null
): State

public sealed class ReadyEvent: Effect {
    public object isReady: ReadyEvent()
    public object isNotReady: ReadyEvent()
}

public class ContextHandler: CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        LogHandler.error("[ContextHandler] $exception ${exception.stackTraceToString()}")
    }

    // TODO By lazy allows cyclic dependencies, even if it is bad design
    private val basketHandler: BasketHandler by lazy { MiamDI.basketHandler }
    private val preference: SingletonPreferencesViewModel by lazy { MiamDI.preferencesViewModel }

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