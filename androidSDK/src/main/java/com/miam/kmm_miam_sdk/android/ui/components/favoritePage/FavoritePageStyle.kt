package com.miam.kmm_miam_sdk.android.ui.components.favoritePage

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.miam.kmm_miam_sdk.android.theme.Dimension.lPadding
import com.miam.kmm_miam_sdk.android.theme.Dimension.sPadding


object FavoritePageStyle {
    // Success state
    var favoriteMainContainer = Modifier.fillMaxSize()
    var spaceBetweenRecipe = sPadding
    var loadMoreContainer = Modifier
        .fillMaxWidth()
        .padding(vertical = lPadding)
    var loadMoreModifier = Modifier


    // Loading state
    var loadingStateMainContainer = Modifier.fillMaxSize()
    var loadingStateLoaderModifier = Modifier

    // Empty state
    var emptyStateMainContainer = Modifier.fillMaxSize()
    var favoriteIconModifier = Modifier.size(60.dp)
    var noFavoriteText = Modifier
    var goToDetailButton = Modifier
}