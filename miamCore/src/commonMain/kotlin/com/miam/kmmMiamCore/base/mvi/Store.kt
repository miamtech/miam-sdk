package com.miam.kmmMiamCore.base.mvi

import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

public interface State
public interface Action
public interface Effect

public interface Store<S : State, A : Action, E : Effect> {

    // TODO Romain: This should not be mutable, right?
    public val state: MutableStateFlow<S>

    // TODO Romain, This is not necessary - To be deprecated
    public fun observeState(): StateFlow<S>
    // TODO Romain: Why would we need a side effect on a Store?
    //  Use a property instead - To be deprecated
    public fun observeSideEffect(): Flow<E>
    // TODO Romain: This does not have to return a Job
    public fun dispatch(action: A): Job

    public fun updateStateIfChanged(newState: S) {
        // TODO Romain: this is not necessary, StateFlow does not emit new values if they are identical
        if (newState != state.value) {
            state.value = newState
        }
    }
}