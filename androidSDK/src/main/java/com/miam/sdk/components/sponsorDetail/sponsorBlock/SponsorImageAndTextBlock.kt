package com.miam.sdk.components.sponsorDetail.sponsorBlock

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable


@Composable
fun SponsorImageAndTextBlock(content: String, imageUrl: String, backgroundColor: String? = null) {
    Column {
        SponsorImageBlock(imageUrl)
        SponsorTextBlock(content, backgroundColor)
    }
}