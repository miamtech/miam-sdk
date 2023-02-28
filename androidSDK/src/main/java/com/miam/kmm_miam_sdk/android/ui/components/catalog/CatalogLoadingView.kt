package com.miam.kmm_miam_sdk.android.ui.components.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.miam.core.sdk.localisation.Localisation.Catalog.loadingText
import com.miam.kmm_miam_sdk.android.theme.Colors.primary
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.theme.Typography

@Composable
fun CatalogLoadingView() {

    if (Template.CatalogLoaderTemplate != null) {
        Template.CatalogLoaderTemplate?.let {
            it()
        }
    } else {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(loadingText.localised, style = Typography.subtitleBold, modifier = Modifier.padding(8.dp))
            CircularProgressIndicator(color = primary)
        }
    }

}