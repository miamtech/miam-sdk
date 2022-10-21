package com.miam.kmm_miam_sdk.android.ui.components.preferences

import androidx.compose.runtime.Composable
import com.miam.kmm_miam_sdk.android.theme.Template

@Composable
fun PreferencesLoadingView() {
    if (Template.PreferencesLoadingTemplate != null) {
        Template.PreferencesLoadingTemplate?.let { it() }
    } else {

    }
}