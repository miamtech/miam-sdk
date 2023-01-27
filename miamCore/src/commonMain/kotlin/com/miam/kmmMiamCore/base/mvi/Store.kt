package com.miam.kmmMiamCore.base.mvi

import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

public interface State
public interface Action
public interface Effect

public interface Store<S : State, A : Action, E : Effect> {

    // TODO This should not be mutable, right?
    public val state: MutableStateFlow<S>
    public fun observeState(): StateFlow<S>
    public fun observeSideEffect(): Flow<E>
    public fun dispatch(action: A): Job

    public fun updateStateIfChanged(newState: S) {
        if (newState != state.value) {
            state.value = newState
        }
    }
}