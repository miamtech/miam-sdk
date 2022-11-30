package com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.subComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miam.kmm_miam_sdk.android.theme.Typography
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsImage
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsText.difficultyHigh
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsText.difficultyLow
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsText.difficultyMedium

@Composable
fun RecipeDifficulty(ImageRef: Int, difficultyLabel: String) {
    Image(
        painter = painterResource(ImageRef),
        contentDescription = null,
        modifier = RecipeDetailsStyle.difficultyIconModifier
    )
    Spacer(
        modifier = Modifier
            .height(8.dp)
    )
    Text(
        text = difficultyLabel,
        style = Typography.bodySmallBold
    )
}

@Composable
fun RecipeDifficultyAndTiming(difficulty: Int, totalTime: String) {

    Row(
        Modifier.padding(vertical = 20.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = RecipeDetailsStyle.difficultyContainer.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (difficulty) {
                1 -> RecipeDifficulty(RecipeDetailsImage.difficultyLow, difficultyLow)
                2 -> RecipeDifficulty(RecipeDetailsImage.difficultyMid, difficultyMedium)
                3 -> RecipeDifficulty(RecipeDetailsImage.difficultyHard, difficultyHigh)
            }
        }
        Divider(modifier = RecipeDetailsStyle.difficultyAndTimeDivider)
        Column(
            modifier = RecipeDetailsStyle.totalTimeContainer
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(RecipeDetailsImage.time),
                contentDescription = null,
                modifier = RecipeDetailsStyle.totalTimeIcon
            )
            Spacer(
                modifier = Modifier
                    .height(8.dp)
            )
            Text(
                text = totalTime,
                style = Typography.bodySmallBold
            )
        }
    }
}


@Preview
@Composable
fun RecipeDifficultyAndTimingPreview() {
    Box(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .background(color = Color.White)
    ) {
        RecipeDifficultyAndTiming(1, "20 min")
    }
}



