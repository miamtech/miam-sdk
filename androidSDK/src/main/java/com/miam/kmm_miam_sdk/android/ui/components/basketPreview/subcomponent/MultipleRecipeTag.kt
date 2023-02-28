package com.miam.kmm_miam_sdk.android.ui.components.basketPreview.subcomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miam.kmm_miam_sdk.android.theme.Colors
import com.miam.kmm_miam_sdk.android.theme.Colors.grey
import com.miam.kmm_miam_sdk.android.theme.Typography.overLine

@Composable
fun MultipleRecipeTag(sharingCount: String) {
    if (sharingCount != "null") {

        Surface(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier

                .height(20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.background(color = Colors.lightgrey)
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = sharingCount.toString(),
                    color = grey,
                    style = overLine
                )
            }

        }
    } else {
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Preview
@Composable
fun MultipleRecipeTagPreview() {
    MultipleRecipeTag("Pour 18 repas")
}