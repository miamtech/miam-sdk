package com.miam.kmm_miam_sdk.android.ui.components.recipeDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.unit.dp
import com.miam.kmm_miam_sdk.android.theme.Dimension.lPadding
import com.miam.kmm_miam_sdk.android.theme.Dimension.mPadding

object RecipeDetailsStyle {

    // header
    var headerMainContainer = Modifier.padding(horizontal = lPadding)
    var headerRecipeIconModifier = Modifier.width(120.dp).padding(vertical = mPadding).padding(end = mPadding)
    var headerCloseIconModifier = Modifier.size(40.dp).padding(end = mPadding)


    // recipe Infos
    var mainColumnsContainer = Modifier.fillMaxWidth()
    var recipeImageModifier = Modifier.height(280.dp).fillMaxWidth()
    var recipeDetailsActionsContainer = Modifier.padding(8.dp)
    var titleModifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 24.dp)
    var difficultyContainer = Modifier
    var difficultyIconModifier = Modifier.height(24.dp)
    var difficultyAndTimeDivider = Modifier
        .height(16.dp)
        .width(1.dp)
    var totalTimeContainer = Modifier
    var totalTimeIcon = Modifier.size(24.dp)

    var moreInfoSection = Modifier
        .fillMaxWidth()
        .padding(40.dp)

    var moreInfoButtonShapeContainer =  Modifier.clip(RoundedCornerShape(16.dp))
    var moreInfoButton = Modifier
        .background(color = RecipeDetailsColor.moreInfosButtonBackground)
        .padding(horizontal = 8.dp, vertical = 4.dp)


    // Steps
    var stepsMainContainer = Modifier.fillMaxSize().padding(8.dp)

    // footer
    var footerMainContainer = Modifier.fillMaxWidth()
    var checkProductButton = Modifier
        .background(RecipeDetailsColor.goToPreviewBackgroundColor)
        .height(80.dp)
        .padding(horizontal = 16.dp)
    var buyRecipeButton = Modifier
        .background(RecipeDetailsColor.buyButtonBackgroundColor)
        .height(80.dp)
        .padding(horizontal = 16.dp)
    var buyRecipeButtonIcon = Modifier
        .size(24.dp)
        .padding(start = 8.dp)





}