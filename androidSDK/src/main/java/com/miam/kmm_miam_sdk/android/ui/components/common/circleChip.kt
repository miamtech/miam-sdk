package com.miam.kmm_miam_sdk.android.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.miam.kmm_miam_sdk.android.theme.Colors.primary

import com.miam.kmm_miam_sdk.android.theme.Colors.ternary
import com.miam.kmm_miam_sdk.android.theme.Colors.white

@Composable
fun CircleChips(label: String) {
    Box(modifier = Modifier.padding(4.dp)) {
        Surface(
            shape = CircleShape,
            color = primary,
            modifier = Modifier.size(24.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    label,
                    color = white,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W700

                )
            }
        }
    }
}