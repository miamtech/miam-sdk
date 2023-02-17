package com.miam.sdk.components.sponsorDetail.sponsorBlock

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.miam.kmm_miam_sdk.android.theme.Colors.white

private fun hexStringToLong(hex: String): Long {
    return ("0xFF" + hex.substring(1)).toLong()
}


@Composable
fun SponsorImageWithTextBlock(content: String, imageUrl: String, backgroundColor: String? = null) {
    Box(
        Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
    ) {
        Image(
            painter = rememberImagePainter(imageUrl),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(246.dp)
        )
        Box(
            Modifier
                .background(if (backgroundColor != null) Color(hexStringToLong(backgroundColor)) else white)
                .align(Alignment.Center)
                .padding(8.dp)
        ) {
            SponsorTextBlock(content)
        }
    }
}