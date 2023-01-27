package com.miam.kmmMiamCore.base.mvi

public interface UiEffect
public interface UiEvent
public interface UiState

public sealed class BasicUiState<out T> {
    public data class Success<T>(val data: T) : BasicUiState<T>()
    public data class Error(val message: String? = null) : BasicUiState<Nothing>()
    public object Loading : BasicUiState<Nothing>()
    public object Empty : BasicUiState<Nothing>()
    public object Idle : BasicUiState<Nothing>()
}