package com.example.kmm_miam_sdk.android.ui.components.states

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.kmm_miam_sdk.base.mvi.BasicUiState

@Composable
fun <T> ManagementResourceState(
    resourceState: BasicUiState<T>,
    successView: @Composable (data: T?) -> Unit,
    onTryAgain: () -> Unit,
    onCheckAgain: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,

    ) {
        when (resourceState) {
            is BasicUiState.Empty -> Empty(onCheckAgain = onCheckAgain)
            is BasicUiState.Error -> Error(onTryAgain = onTryAgain)
            BasicUiState.Loading -> Loading()
            BasicUiState.Idle -> Unit
            is BasicUiState.Success -> {
                successView(resourceState.data)
            }
        }
    }
}