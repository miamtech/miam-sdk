package com.miam.kmm_miam_sdk.android.ui.components.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun CustomActionButton(action: () -> Unit, icon: Int, text: String, isActive: Boolean) {
    var colorFont = MiamMasterView.serviceBlueColor
    var backgroundColor = Color.White

    if (isActive) {
        colorFont = Color.White
        backgroundColor = MiamMasterView.serviceBlueColor
    }
    ExtendedFloatingActionButton(
        text = {
            Row() {
                Image(
                    painter = painterResource(icon),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(colorFont),
                    modifier = Modifier
                        .size(30.dp)
                        .padding(horizontal = 4.dp)

                )
                Text(
                    text = text, color = colorFont
                )
            }
        },
        backgroundColor = backgroundColor,
        onClick = action
    )
}