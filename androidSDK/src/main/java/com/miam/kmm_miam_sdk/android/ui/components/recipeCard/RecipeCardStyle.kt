package com.miam.kmm_miam_sdk.android.ui.components.recipeCard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.miam.kmm_miam_sdk.android.theme.Colors.unpureWhite
import com.miam.kmm_miam_sdk.android.theme.Dimension.lPadding


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
    var cardLayout = Modifier
        .fillMaxWidth()
        .padding(lPadding)
        .background(unpureWhite)

    /**
     * imageContainer is a Manager for a Box
     * image, recipeTitle, inCartTagBox, moreInfoButton,
     */
    var imageContainer = Modifier
        .height(280.dp)
        .fillMaxWidth()

    /**
     * image Manager for an Image add filter define size
     */
    var image = Modifier
        .height(280.dp)
        .fillMaxWidth()

    /**
     * recipeCardFlagPositionContainer  is a Manager for a Box
     * use for position
     * recipeFlag is mainly use for retailer branding
     */
    var recipeCardFlagPositionContainer = Modifier
        .absoluteOffset(x = 4.dp, y = 8.dp)
        .rotate(-3f)


}