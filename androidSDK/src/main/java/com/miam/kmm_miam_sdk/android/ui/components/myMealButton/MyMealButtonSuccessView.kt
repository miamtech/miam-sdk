package com.miam.kmm_miam_sdk.android.ui.components.myMealButton


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.miam.core.sdk.localisation.Localisation
import com.miam.kmm_miam_sdk.android.theme.Colors.primary
import com.miam.kmm_miam_sdk.android.theme.Colors.white
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.theme.Typography.subtitleBold
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.customization.BasketPreviewImage
import com.miam.kmm_miam_sdk.android.ui.components.common.Clickable

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
                    .height(80.dp)
                    .graphicsLayer {
                        clip = true
                        shape = BottomRoundedArcShape()
                    }
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
                    text = Localisation.MyMeals.mealsAdded(recipeCount).localised,
                    color = white,
                    style = subtitleBold
                )
            }

        }
    }
}

class BottomRoundedArcShape: Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            path = drawArcPath(size = size)
        )
    }
}

fun drawArcPath(size: Size): Path {
    return Path().apply {
        reset()
        arcTo(
            rect =
            Rect(
                Offset(0f, 0f),
                Size(size.width, size.height * 2)
            ),
            sweepAngleDegrees = 180f,
            startAngleDegrees = -180f,
            forceMoveTo = false
        )
        lineTo(0f, size.height)
        close()
    }
}

@Preview
@Composable
fun MyMealButtonSuccessViewPreview() {
    MyMealButtonSuccessView(12) {}
}