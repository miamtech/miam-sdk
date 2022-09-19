package com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.subComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.miam.kmm_miam_sdk.android.ressource.Image
import com.miam.kmm_miam_sdk.android.theme.Typography
import com.miam.kmm_miam_sdk.android.ui.components.common.Clickable
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsImage
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle

@Composable
fun RecipeDetailsHeader(title: String, scrollState: Int, closeDialogue: () -> Unit) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = RecipeDetailsStyle.headerMainContainer,
    ) {
        Clickable(
            onClick = { closeDialogue() },
        ) {
            Image(
                painter = painterResource(Image.toggleCaret),
                contentDescription = null,
                modifier = RecipeDetailsStyle.headerCloseIconModifier.rotate(180f)
            )
        }
        Image(
            painter = painterResource(RecipeDetailsImage.recipeIcon),
            contentDescription = null,
            modifier = RecipeDetailsStyle.headerRecipeIconModifier
        )
        if (scrollState > 900) {
            Text(
                modifier = Modifier.weight(1.0F),
                text = title,
                textAlign = TextAlign.Left,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = Typography.bodyBold
            )
        } else {
            Spacer(modifier = Modifier.weight(1.0F))
        }
    }
}

@Preview
@Composable
fun RecipeDetailsHeaderPreviewWithoutTitle() {
    Box(modifier = Modifier.background(color = Color.White)) {
        RecipeDetailsHeader("Welsh royal à la 3 Monts sur 2 moyennement longues lignes", 0) {}
    }
}

@Preview
@Composable
fun RecipeDetailsHeaderPreviewWithTitle() {
    Box(modifier = Modifier.background(color = Color.White)) {
    RecipeDetailsHeader("Welsh royal à la 3 Monts sur 2 moyennement longues lignes", 901) {}
    }
}



