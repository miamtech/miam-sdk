package com.miam.sdk.components.sponsorDetail.sponsorBlock

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable


@Composable
fun SponsorTextAndImageBlock(content: String, imageUrl: String, backgroundColor: String? = null) {
    Column {
        SponsorTextBlock(content, backgroundColor)
        SponsorImageBlock(imageUrl)
    }
}