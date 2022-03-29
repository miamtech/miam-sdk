package com.miam.kmm_miam_sdk.android.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.miam.kmm_miam_sdk.android.R
import com.miam.kmm_miam_sdk.android.ui.theme.Colors.white

object Typography {

    var body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )

    var body1White =  body1.copy(color = white)

    var button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    )

    var link = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )

    var whiteRecipeTitle = TextStyle(
        color = white,
        fontSize = 32.sp,
        fontFamily = FontFamily(
            Font(R.font.satisfy_regular)
    )
    )

}