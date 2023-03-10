package com.miam.kmm_miam_sdk.android.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object Typography {

    var h1: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 80.sp
    )

    var h2: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 60.sp
    )

    var subtitle: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    )

    var subtitleBold: TextStyle = subtitle.copy(fontWeight = FontWeight.Bold)

    var body = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )

    var bodyBold: TextStyle = body.copy(fontWeight = FontWeight.Bold)

    var bodySmall: TextStyle = body.copy(fontSize = 14.sp)


    var bodySmallBold: TextStyle = bodySmall.copy(fontWeight = FontWeight.Bold)

    var button: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp
    )

    var overLine: TextStyle = body.copy(fontSize = 11.sp)

    var link = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    )


}