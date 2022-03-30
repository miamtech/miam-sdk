package com.miam.kmm_miam_sdk.android.ui.components.Counter

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.miam.kmm_miam_sdk.android.theme.Dimension.lIconHeight
import com.miam.kmm_miam_sdk.android.theme.Dimension.mButtonHeight
import com.miam.kmm_miam_sdk.android.theme.Dimension.mPadding

object CounterStyle {
    var mainRowContainer: Modifier = Modifier.padding(
        horizontal = mPadding,
        vertical = mPadding
    )
    var guestIcon: Modifier = Modifier.size(lIconHeight)

    var lessButton : Modifier = Modifier
    var lessButtonIcon: Modifier = Modifier.size(mButtonHeight)

    var countBorderModifier: Modifier = Modifier
        .height(32.dp)
        .width(48.dp)
        .border(
            border = BorderStroke(width = 1.dp, color = Color.Gray),
            shape = RoundedCornerShape(4.dp)
        )
    var countTextModifier: Modifier = null,
    var plusButtonModifier: Modifier = null,

    var rowModifierLight: Modifier = null,
    var iconModifierLight: Modifier = null,
    var lessButtonModifierLight: Modifier = null,
    var countBorderModifierLight: Modifier = null,
    var countTextModifierLight: Modifier = null,
    var plusButtonModifierLight: Modifier = null,
}