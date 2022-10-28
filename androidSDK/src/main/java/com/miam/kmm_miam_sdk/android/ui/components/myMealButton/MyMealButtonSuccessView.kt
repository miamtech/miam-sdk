package com.miam.kmm_miam_sdk.android.ui.components.myMealButton


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import com.miam.kmm_miam_sdk.android.theme.Colors.primary
import com.miam.kmm_miam_sdk.android.theme.Colors.white
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.theme.Typography.subtitleBold
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.customization.BasketPreviewImage
import com.miam.kmm_miam_sdk.android.ui.components.common.Clickable
import com.miam.kmm_miam_sdk.android.ui.components.myMealButton.customization.MyMealButtonText.multipleMealText
import com.miam.kmm_miam_sdk.android.ui.components.myMealButton.customization.MyMealButtonText.singleMealText

@Composable
fun MyMealButtonSuccessView(recipeCount: Int, onclick: () -> Unit) {
    if (Template.myMealButtonSuccessViewTemplate != null) {
        Template.myMealButtonSuccessViewTemplate?.let {
            it(recipeCount, onclick)
        }
    } else {
        Clickable(onClick = { onclick() }) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 100f, topEnd = 100f))
                    .background(color = primary),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(BasketPreviewImage.toggleCaret),
                    contentDescription = null,
                    modifier = Modifier.rotate(-90f),
                    colorFilter = ColorFilter.tint(Color.White)
                )
                Text(
                    text = if (recipeCount == 1) singleMealText else "$recipeCount" + multipleMealText,
                    color = white,
                    style = subtitleBold
                )
            }
        }
    }
}