package com.miam.kmm_miam_sdk.android.ui.components.catalog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.miam.kmm_miam_sdk.android.theme.Colors
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.theme.Typography
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogImage
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogText.changeYourPref
import com.miam.kmm_miam_sdk.android.ui.components.catalog.customization.CatalogText.preferenceNoResult

@Composable
fun CatalogCategoriesEmptyView(
    action: () -> Unit
) {
    if (Template.CatalogCategoriesEmptyTemplate != null) {
        Template.CatalogCategoriesEmptyTemplate?.let { it(action) }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Colors.primary)
        ) {
            Column(
                Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painter = painterResource(CatalogImage.empty), contentDescription = null, Modifier.padding(vertical = 16.dp))
                Text(
                    text = preferenceNoResult,
                    color = Colors.white,
                    style = Typography.subtitleBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Text(text = changeYourPref, color = Colors.white)
            }
        }
    }
}

