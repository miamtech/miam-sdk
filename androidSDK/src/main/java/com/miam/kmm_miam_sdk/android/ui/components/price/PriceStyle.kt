package com.miam.kmm_miam_sdk.android.ui.components.price

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.miam.kmm_miam_sdk.android.theme.Dimension.lSpacerHeight
import com.miam.kmm_miam_sdk.android.theme.Dimension.mRoundedCorner
import com.miam.kmm_miam_sdk.android.theme.Dimension.mSpacerHeight
import com.miam.kmm_miam_sdk.android.theme.Dimension.sPadding
import com.miam.kmm_miam_sdk.android.theme.Dimension.sSpacerHeight
import com.miam.kmm_miam_sdk.android.theme.Dimension.sSpacerWidth


object PriceStyle {

    var priceEmptyState : Modifier = Modifier.padding(vertical = lSpacerHeight)

    var mainContainer : Modifier = Modifier
    var priceContainer : Modifier = Modifier

    var loaderInteger: Modifier = Modifier
        .padding(vertical = sPadding)
        .height(mSpacerHeight)
        .width(sSpacerWidth)
        .clip(RoundedCornerShape(mRoundedCorner))

    var loaderDecimal: Modifier = Modifier
        .height(sSpacerHeight)
        .width(sSpacerWidth)
        .clip(RoundedCornerShape(mRoundedCorner))

}