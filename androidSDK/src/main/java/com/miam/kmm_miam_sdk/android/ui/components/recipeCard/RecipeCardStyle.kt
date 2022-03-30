package com.miam.kmm_miam_sdk.android.ui.components.recipeCard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.miam.kmm_miam_sdk.android.theme.Colors.black
import com.miam.kmm_miam_sdk.android.theme.Colors.danger
import com.miam.kmm_miam_sdk.android.theme.Colors.grey
import com.miam.kmm_miam_sdk.android.theme.Colors.info
import com.miam.kmm_miam_sdk.android.theme.Dimension.bigPadding
import com.miam.kmm_miam_sdk.android.theme.Dimension.largePadding
import com.miam.kmm_miam_sdk.android.theme.Dimension.smallPadding
import com.miam.kmm_miam_sdk.android.theme.Dimension.mediumPadding

/**
 * RecipeCardStyleComponent hold all RecipeCard compose Manager
 * that can be customise, you can override on or more Manager
 * by setting a new value, example:
 *
 *  RecipeCardStyleComponent.cardLayout =  Modifier.fillMaxSize().backgroundColor(Color.Blue)
 */
object RecipeCardStyle {

    /**
     * cardLayout is a Manager apply on Material Card
     */
    var cardLayout = Modifier.fillMaxWidth().padding(bigPadding)

    /**
     * imageContainer is a Manager for a Box
     * image, recipeTitle, inCartTagBox, moreInfoButton,
     */
    var imageContainer = Modifier.height(245.dp).fillMaxWidth()

    /**
     * image Manager for an Image add filter define size
     */
    var image = Modifier.height(245.dp)
        .fillMaxWidth()
        .graphicsLayer {
            alpha = 0.99f
        }.drawWithContent {
            val colors = listOf(
                Color.Transparent,
                black
            )
            drawContent()
            drawRect(
                brush = Brush.verticalGradient(colors),
            )
        }

    /**
     * recipeTitle is a Manager for a Text container
     * note that all text style is not handle here only container appearance
     */
    var recipeTitle = Modifier.wrapContentWidth(Alignment.CenterHorizontally).padding(horizontal = largePadding)

    /**
     * inCartTagBox is a Manager for a Box
     * it's for shape and position
     */
    var inCartTagBox = Modifier
        .absoluteOffset(x = mediumPadding, y = mediumPadding)
        .clip(
            RoundedCornerShape(
                topEnd = smallPadding,
                topStart = smallPadding,
                bottomStart = smallPadding,
                bottomEnd = smallPadding
            )
        ).background(danger)

    /**
     * inCartTagPadding is a Manager for a Row
     * use to handle inner padding
     */
    var inCartTagPadding = Modifier.padding( horizontal = smallPadding, vertical = mediumPadding)

    /**
     * moreInfoButton is a Manager for a FloatingActionButton
     * use for position
     */
    var moreInfoButton = Modifier.size(24.dp).absoluteOffset(x = mediumPadding, y = mediumPadding)

    /**
     * recipeMetricsRow is a Manager for a Column
     * container of a metric
     */
    var recipeMetricsRow = Modifier
        .padding(
            start = mediumPadding,
            end = mediumPadding,
            top = largePadding,
            bottom = bigPadding
        ).fillMaxWidth()

    /**
     * recipeMetricsRow is a Manager for an Image
     */
    var metricsIcon = Modifier.size(24.dp)

    /**
     * recipeMetricsRow is a Manager for a Divider between metric
     */
    var metricsDivider = Modifier.height(32.dp).width(1.dp).background(grey)

    /**
     * recipeCardFlagPositionContainer  is a Manager for a Box
     * use for position
     * recipeFlag is mainly use for retailer branding
     */
    var recipeCardFlagPositionContainer = Modifier.absoluteOffset(x = 0.dp, y = 188.dp)

    /**
     * recipeCardFlagContainer is a Manager for a Box
     * it's use for shape of the flag
     */
    var recipeCardFlagContainer = Modifier.clip(RoundedCornerShape(topEnd = mediumPadding, bottomEnd = mediumPadding)).background(info)

    /**
     * recipeMetricsRow is a Manager for an Image
     */
    var recipeCardFlagImage =  Modifier.size(20.dp)

}