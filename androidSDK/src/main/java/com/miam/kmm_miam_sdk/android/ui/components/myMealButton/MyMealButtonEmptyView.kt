package com.miam.kmm_miam_sdk.android.ui.components.myMealButton

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import com.miam.kmm_miam_sdk.android.theme.Template

@Composable
fun MyMealButtonEmptyView() {
    if (Template.myMealButtonEmptyViewTemplate != null) {
        Template.myMealButtonEmptyViewTemplate?.let {
            it()
        }
    } else {
        Box {}
    }
}