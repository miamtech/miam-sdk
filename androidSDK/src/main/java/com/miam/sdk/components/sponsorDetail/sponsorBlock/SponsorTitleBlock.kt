package com.miam.sdk.components.sponsorDetail.sponsorBlock

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.miam.kmm_miam_sdk.android.theme.Colors.primary
import com.miam.kmm_miam_sdk.android.theme.Typography

@Composable
fun SponsorTitleBlock(text: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 32.dp),
        text = text,
        style = Typography.h2.copy(fontSize = 32.sp),
        color = primary
    )
}