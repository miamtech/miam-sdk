package com.miam.kmm_miam_sdk.android.ui.components.common

import androidx.compose.runtime.Composable

@Composable
fun clickable(
    onClick: (() -> Unit)? = null,
    consumeDownOnStart: Boolean = false,
    children: @Composable() () -> Unit
) {
    children()
}