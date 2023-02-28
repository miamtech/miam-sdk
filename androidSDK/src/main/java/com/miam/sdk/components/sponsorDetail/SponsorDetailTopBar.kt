package com.miam.sdk.components.sponsorDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.miam.kmm_miam_sdk.android.ressource.Image
import com.miam.kmm_miam_sdk.android.theme.Colors.white
import com.miam.kmm_miam_sdk.android.theme.Dimension
import com.miam.kmm_miam_sdk.android.ui.components.common.Clickable

@Composable
fun SponsorDetailTopBar(previous: () -> Unit) {
    Row(
        Modifier
            .background(white)
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Clickable(
            onClick = previous,
        ) {
            Image(
                painter = painterResource(Image.toggleCaret),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = Dimension.mPadding)
                    .rotate(180f)
            )
        }
    }
}