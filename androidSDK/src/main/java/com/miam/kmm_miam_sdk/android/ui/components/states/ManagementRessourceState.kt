package com.miam.kmm_miam_sdk.android.ui.components.states

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.miam.kmmMiamCore.base.mvi.BasicUiState

@Composable
fun <T> ManagementResourceState(
    resourceState: BasicUiState<T>,
    successView: @Composable (data: T?) -> Unit,
    loadingView: @Composable () -> Unit,
    emptyView: (@Composable () -> Unit)? = null,
    onTryAgain: () -> Unit,
    onCheckAgain: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,

        ) {
        when (resourceState) {
            is BasicUiState.Empty -> if (emptyView != null) emptyView() else Empty(onCheckAgain = {})
            is BasicUiState.Error -> Error(onTryAgain = {})
            BasicUiState.Loading -> loadingView()
            BasicUiState.Idle -> Unit
            is BasicUiState.Success -> {
                successView(resourceState.data)
            }
        }
    }
}