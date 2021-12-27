package com.example.kmm_miam_sdk.android.ui

import androidx.compose.runtime.Composable
import com.example.kmm_miam_sdk.base.mvi.BasicUiState

class BaseComponentView(
    state: BasicUiState<*> = BasicUiState.Success(Any()),
    topAppBar: @Composable () -> Unit = {},
) {
}