package com.miam.kmm_miam_sdk.android.ui.components.states

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.miam.kmm_miam_sdk.base.mvi.BasicUiState

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
            is BasicUiState.Empty -> Empty(onCheckAgain = {})
            is BasicUiState.Error -> Error(onTryAgain = {})
            BasicUiState.Loading -> Loading()
            BasicUiState.Idle -> Unit
            is BasicUiState.Success -> {
                successView(resourceState.data)
            }
        }
    }
}