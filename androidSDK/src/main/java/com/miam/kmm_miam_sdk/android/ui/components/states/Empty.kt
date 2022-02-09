package com.miam.kmm_miam_sdk.android.ui.components.states

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Empty(
    msg: String = "No data to show",
    onCheckAgain: () -> Unit = {}
) {
    Box{}
}