package com.miam.kmm_miam_sdk.android.ui.components.counter

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.miam.kmm_miam_sdk.android.theme.Colors.grey
import com.miam.kmm_miam_sdk.android.theme.Colors.primary
import com.miam.kmm_miam_sdk.android.theme.Dimension.borderWidth
import com.miam.kmm_miam_sdk.android.theme.Dimension.mButtonHeight
import com.miam.kmm_miam_sdk.android.theme.Dimension.mIconHeight
import com.miam.kmm_miam_sdk.android.theme.Dimension.mPadding
import com.miam.kmm_miam_sdk.android.theme.Dimension.sPadding
import com.miam.kmm_miam_sdk.android.theme.Dimension.sRoundedCorner

object CounterStyle {

    /**
     * Main container row
     */
    var mainRowContainer: Modifier = Modifier.padding(
        horizontal = sPadding,
        vertical = sPadding
    )

    /**
     * Main container Icon
     */
    var guestIcon: Modifier = Modifier.size(0.dp)

    /**
     * Modifier for an IconButton
     * button color is handle in CounterColor.kt
     */
    var lessButton: Modifier = Modifier.clip(RoundedCornerShape(topStart = 70f, bottomStart = 70f))

    /**
     * Modifier for an Image
     * icon color is handle in CounterColor.kt
     */
    var lessButtonIcon: Modifier = Modifier.size(mIconHeight)

    /**
     * Modifier for a Row
     * parent container of countText
     */
    var countBorder: Modifier = Modifier
        .background(color = primary)
        .height(48.dp)
        .width(70.dp)


    /**
     * Modifier for a Text
     */
    var countText: Modifier = Modifier
        .padding(
            horizontal = mPadding,
            vertical = sPadding
        )

    /**
     * Modifier for an IconButton
     * button color is handle in CounterColor.kt
     */
    var plusButton: Modifier = Modifier.clip(RoundedCornerShape(topEnd = 70f, bottomEnd = 70f))

    /**
     * Modifier for an Image
     * icon color is handle in CounterColor.kt
     */
    var plusButtonIcon: Modifier = Modifier.size(mIconHeight)

    /**
     * use for smaller component
     * */
    var mainRowContainerLight: Modifier = Modifier.padding(
        horizontal = mPadding,
        vertical = mPadding
    )
    var guestIconLight: Modifier = Modifier.size(0.dp)
    var lessButtonLight: Modifier = Modifier
    var lessButtonIconLight: Modifier = Modifier.size(mButtonHeight)
    var countBorderLight: Modifier = Modifier
        .height(32.dp)
        .width(48.dp)
        .border(
            border = BorderStroke(width = borderWidth, color = grey),
            shape = RoundedCornerShape(sRoundedCorner)
        )
    var countTextLight: Modifier = Modifier
        .padding(
            horizontal = mPadding,
            vertical = sPadding
        )
    var plusButtonLight: Modifier = Modifier
    var plusButtonIconLight: Modifier = Modifier.size(mIconHeight)

}