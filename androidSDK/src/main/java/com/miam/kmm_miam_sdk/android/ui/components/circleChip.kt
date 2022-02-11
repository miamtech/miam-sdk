package com.miam.kmm_miam_sdk.android.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CircleChips(label: String) {
    Box(modifier = Modifier.padding(8.dp)) {
        Surface(
            shape = CircleShape,
            color = MiamMasterView.serviceBlueColor,
            modifier = Modifier.size(40.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    label,
                    color = MiamMasterView.whiteColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W700

                )
            }
        }
    }
}