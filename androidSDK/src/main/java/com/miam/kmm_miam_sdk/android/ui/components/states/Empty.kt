package com.miam.kmm_miam_sdk.android.ui.components.states

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable

@Composable
fun Empty(
    msg: String = "No data to show",
    onCheckAgain: () -> Unit = {}
) {
    Box {}
}