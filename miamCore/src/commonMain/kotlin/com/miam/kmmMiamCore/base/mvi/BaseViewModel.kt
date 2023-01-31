package com.miam.kmmMiamCore.base.mvi

import com.miam.kmmMiamCore.base.executor.MainExecutor
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

// TODO Romain: is UiEffect necessary? side effects should be use carefully
public abstract class BaseViewModel<Event : UiEvent, State : UiState, Effect : UiEffect> : MainExecutor() {

    private val initialState: State by lazy { createInitialState() }
    public abstract fun createInitialState(): State

    // TODO Romain: This is a bad idea, a state is only a representation of a Screen (or part of it)
    //  Reading the current value only leads to wrong behaviour
    public val currentState: State
        get() = uiState.value

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    public val uiState: StateFlow<State> = _uiState.asStateFlow()

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    // TODO Romain: There is no reason to expose events outside of the MVI processor
    public val event: SharedFlow<Event> = _event.asSharedFlow()

    // TODO Romain: Shared Flow is made for this purpose
    private val _effect: Channel<Effect> = Channel()
    public val effect: Flow<Effect> = _effect.receiveAsFlow()

    init {
        // TODO Romain: Just inline the code here
        subscribeEvents()
    }

    /**
     * Start listening to Event
     */
    private fun subscribeEvents() {
        launch {
            event.collect {
                handleEvent(it)
            }
        }
    }

    /**
     * Handle each event
     */
    public abstract fun handleEvent(event: Event)

    /**
     * Set new Event
     */
    public fun setEvent(event: Event) {
        val newEvent = event
        launch { _event.emit(newEvent) }
    }

    /**
     * Set new Ui State
     */
    protected fun setState(reduce: State.() -> State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }

    /**
     * Set new Effect
     */
    protected fun setEffect(builder: () -> Effect) {
        val effectValue = builder()
        launch { _effect.send(effectValue) }
    }
}