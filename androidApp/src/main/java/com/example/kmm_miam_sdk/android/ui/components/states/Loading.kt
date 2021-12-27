package com.example.kmm_miam_sdk.android.ui.components.states

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun Loading() {
    Box(
        contentAlignment = Alignment.Center,
        //modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}