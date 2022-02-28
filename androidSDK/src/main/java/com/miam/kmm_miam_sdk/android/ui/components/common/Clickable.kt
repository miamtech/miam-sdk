package com.miam.kmm_miam_sdk.android.ui.components.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Clickable(
    onClick: (() -> Unit)? = null,
    children: @Composable() () -> Unit
) {

    Box(Modifier.clickable {
        if (onClick != null) {
            onClick()
        }
    }) {
        children()
    }

}