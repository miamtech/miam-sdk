package com.miam.sdk.components.sponsorDetail.sponsorBlock

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.miam.kmm_miam_sdk.android.theme.Typography

private fun hexStringToLong(hex: String): Long {
    return ("0xFF" + hex.substring(1)).toLong()
}

@Composable
fun SponsorTextBlock(text: String, backgroundColor: String? = null) {
    Box(
        Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
            .background(if (backgroundColor != null) Color(hexStringToLong(backgroundColor)) else Color.Transparent),
    ) {
        Text(
            style = Typography.body,
            text = text
        )
    }
}