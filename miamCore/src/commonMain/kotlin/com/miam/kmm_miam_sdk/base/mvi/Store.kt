package com.miam.kmm_miam_sdk.base.mvi

import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface State
interface Action
interface Effect

interface Store<S : State, A : Action, E : Effect> {
    val state: MutableStateFlow<S>
    fun observeState(): StateFlow<S>
    fun observeSideEffect(): Flow<E>
    fun dispatch(action: A): Job

    fun updateStateIfChanged(newState: S) {
        if (newState != state.value) {
            state.value = newState
        }
    }
}