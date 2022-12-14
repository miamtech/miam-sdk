package com.miam.kmm_miam_sdk.android.ui.components.basketPreview.subcomponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miam.kmm_miam_sdk.android.theme.Colors.primary
import com.miam.kmm_miam_sdk.android.theme.Dimension
import com.miam.kmm_miam_sdk.android.theme.Typography
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.customization.BasketPreviewImage
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.customization.BasketPreviewStyle
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.customization.BasketPreviewStyle.headerRowModifier
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.customization.BasketPreviewStyle.headerRowVerticalAlignment
import com.miam.kmm_miam_sdk.android.ui.components.basketPreview.customization.BasketPreviewText


@Composable
fun BasketPreviewHeader(goToDetail: () -> Unit, previous: () -> Unit) {
    Row(
        modifier = headerRowModifier,
        verticalAlignment = headerRowVerticalAlignment
    )
    {
        IconButton(
            modifier = BasketPreviewStyle.headerPreviousButton,
            onClick = { previous() }
        ) {
            Image(
                painter = painterResource(BasketPreviewImage.toggleCaret),
                contentDescription = "Previous",
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = Dimension.mPadding)
                    .rotate(180f)
            )
        }
        Text(
            text = BasketPreviewText.addedRecipe,
            style = Typography.bodyBold,
            color = primary,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview
@Composable
fun BasketPreviewHeaderPreview() {
    BasketPreviewHeader({}, {})
}

