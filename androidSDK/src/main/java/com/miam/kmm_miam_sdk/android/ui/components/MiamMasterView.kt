package com.miam.kmm_miam_sdk.android.ui.components

import android.content.Context
import androidx.compose.ui.graphics.Color
import android.util.AttributeSet
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.AbstractComposeView

abstract class MiamMasterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AbstractComposeView(context, attrs, defStyleAttr) {

    companion object{
        val greenColor: Color = Color(0xff00af98)
    }

    @Composable
    abstract override fun Content()
}

