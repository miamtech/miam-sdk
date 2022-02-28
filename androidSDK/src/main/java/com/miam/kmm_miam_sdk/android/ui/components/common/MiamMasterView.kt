package com.miam.kmm_miam_sdk.android.ui.components.common

import android.content.Context
import androidx.compose.ui.graphics.Color
import android.util.AttributeSet
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.AbstractComposeView

abstract class MiamMasterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AbstractComposeView(context, attrs, defStyleAttr) {

    companion object{
        val greenColor: Color = Color(0xff00af98)
        val grayColor: Color = Color.Gray
        val textColor: Color = Color(0xff4B555D)
        val serviceBlueColor : Color = Color(0xff116EAA)
        var whiteColor: Color = Color.White
        val darkGray: Color = Color.DarkGray
        val Primary: Color = Color(0xff007E92)
        val Secondary: Color = Color(0xffE22019)
        val lightGray: Color = Color(0xffecf1f4)
        val Grey02: Color = Color(0xff9F9F9F)
        val black25: Color = Color(0xff202020)

    }

    enum class MiamDisplayMode{
        INGREDIENT_MODE, STEPS_MODE
    }

    @Composable
    abstract override fun Content()
}

