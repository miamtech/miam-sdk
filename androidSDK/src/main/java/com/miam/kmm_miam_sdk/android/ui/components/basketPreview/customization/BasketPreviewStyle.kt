package com.miam.kmm_miam_sdk.android.ui.components.basketPreview.customization

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.customization.BasketPreviewColor.continueButtonColor
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.customization.BasketPreviewColor.removeButtonColor

object BasketPreviewStyle {

    //  header
    var headerRowModifier = Modifier.fillMaxWidth()
    var headerRowVerticalAlignment = Alignment.CenterVertically
    var headerPreviousButton = Modifier

    // footer
    var footerContinueButton = Modifier
        .background(continueButtonColor)
        .height(80.dp)
        .padding(horizontal = 16.dp)
    var footerRemoveButton = Modifier
        .background(removeButtonColor)
        .height(80.dp)
        .padding(horizontal = 16.dp)
}
