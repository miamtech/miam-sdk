package com.miam.sdk.components.sponsorDetail.sponsorBlock

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberImagePainter

@Composable
fun SponsorImageBlock(imageUrl: String) {
    Image(
        painter = rememberImagePainter(imageUrl),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxWidth()
    )
}