package com.miam.kmm_miam_sdk.android.ui.components.itemsSelector.customization

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.miam.kmm_miam_sdk.android.theme.Colors
import com.miam.kmm_miam_sdk.android.theme.Dimension.borderWidth
import com.miam.kmm_miam_sdk.android.theme.Dimension.lIconHeight
import com.miam.kmm_miam_sdk.android.theme.Dimension.lPadding
import com.miam.kmm_miam_sdk.android.theme.Dimension.lRoundedCorner
import com.miam.kmm_miam_sdk.android.theme.Dimension.mIconHeight
import com.miam.kmm_miam_sdk.android.theme.Dimension.mPadding
import com.miam.kmm_miam_sdk.android.theme.Dimension.mRoundedCorner
import com.miam.kmm_miam_sdk.android.theme.Dimension.sPadding
import com.miam.kmm_miam_sdk.android.theme.Dimension.xlButtonHeight
import com.miam.kmm_miam_sdk.android.theme.Dimension.xlPadding
import com.miam.kmm_miam_sdk.android.ui.components.itemsSelector.customization.ItemsSelectorColor.itemsBorderColor
import com.miam.kmm_miam_sdk.android.ui.components.itemsSelector.customization.ItemsSelectorColor.selectedItemBorderColor
import com.miam.kmm_miam_sdk.android.ui.components.itemsSelector.customization.ItemsSelectorColor.swapIconBackgroundColor

object ItemsSelectorStyle {

    /**
     * mainContainer is a Column modifier
     * wrap all other element
     */
    var mainContainer : Modifier = Modifier.fillMaxSize().padding(horizontal = lPadding, vertical = xlPadding).background(Colors.white)
    // column vertical alignment
    var mainContainerArrangement : Arrangement.Vertical = Arrangement.Top
    // column horizontal alignment
    var mainContainerAlignment : Alignment.Horizontal = Alignment.CenterHorizontally

    /**
     * Previous button container is a Row
     */
    var previousButtonContainer : Modifier = Modifier.fillMaxWidth()

    /**
     * PreviousButton is an IconButton modifier
     */
    var previousButton : Modifier =  Modifier.size(xlButtonHeight)

    /**
     * Border of the selected item wrap selected item container
     */
    var selectedItemContainerBorder: Modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = lPadding, vertical = mPadding)
        .border(
            BorderStroke(borderWidth, selectedItemBorderColor),
            RoundedCornerShape(lRoundedCorner)
        )

    /**
     * Selected item container row
     * wrap selectedItemImage, selectedItemInfosContainer
     */
    var selectedItemContainer: Modifier =  Modifier
        .fillMaxWidth()
        .padding(horizontal = lPadding, vertical = mPadding)
    //  row horizontal alignment
    var selectedItemContainerArrangement : Arrangement.Horizontal = Arrangement.SpaceEvenly
    //  row vertical alignment
    var selectedItemContainerAlignment: Alignment.Vertical =Alignment.CenterVertically

    /**
     * SelectedItemImage is an Image modifier
     */
    var selectedItemImage : Modifier = Modifier
        .height(72.dp)
        .width(72.dp)
        .clip(RoundedCornerShape(mRoundedCorner))

    /**
     * Column container that wrap info and price
     */
    var selectedItemInfosContainer : Modifier = Modifier.fillMaxWidth()
    // price alignment
    var pricePosition : Arrangement.Horizontal = Arrangement.End


    /**
     * Following are item not choose yet they are in a lazy grid
     * this is the size of each till
     */
    var itemsWidth : Int = 140

    /**
     * Border or an Item
     */
    var itemsBorder : Modifier = Modifier
        .padding(mPadding)
        .border(
            BorderStroke(borderWidth, itemsBorderColor, ),
            RoundedCornerShape(lRoundedCorner)
        )

    /**
     * Column that wrap  swapIconContainer, items Image and info
     */
    var itemColumnContainer: Modifier = Modifier.padding(vertical = mPadding, horizontal = mPadding)

    /**
     * Box that wrap swap icon
     */
    var swapIconContainer : Modifier =  Modifier
        .padding(sPadding)
        .size(lIconHeight)
        .clip(shape = CircleShape)
        .background(swapIconBackgroundColor)

    /**
     * Image for swap icon
     */
    var swapIcon : Modifier = Modifier.size(mIconHeight)

    /**
     * Image of the product
     */
    var itemsImage : Modifier = Modifier
        .height(72.dp)
        .width(72.dp)
        .clip(RoundedCornerShape(mRoundedCorner))

}