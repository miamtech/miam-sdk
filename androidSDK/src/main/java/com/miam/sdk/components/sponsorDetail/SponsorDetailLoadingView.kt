package com.miam.sdk.components.sponsorDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.miam.kmm_miam_sdk.android.theme.Colors

@Composable
fun SponsorDetailLoadingView() {
    Column(
        Modifier
            .fillMaxSize()
            .background(color = Colors.white),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(color = Colors.primary)
    }
}
