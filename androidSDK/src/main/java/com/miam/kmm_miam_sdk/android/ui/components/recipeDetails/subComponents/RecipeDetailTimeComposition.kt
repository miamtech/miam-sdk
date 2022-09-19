package com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.subComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miam.kmm_miam_sdk.android.theme.Typography
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsText
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration


@Composable
fun RecipeDetailTimeComposition(
    preparationTime: Duration?,
    cookingTime: Duration?,
    restingTime: Duration?
) {
     val preparationTimeState by remember { mutableStateOf(preparationTime) }
     val cookingTimeState by remember { mutableStateOf(cookingTime) }
     val restingTimeState by remember { mutableStateOf(restingTime) }


    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start,
        modifier = RecipeDetailsStyle.moreInfoSection
    ) {
        Column(Modifier.padding(bottom = 16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Time(RecipeDetailsText.prepTime, preparationTimeState)
                Spacer(modifier = Modifier.width(16.dp))
                Time(RecipeDetailsText.cookTime, cookingTimeState)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Time(RecipeDetailsText.restingTime, restingTimeState)
        }
    }
}

@Composable
fun Time(timeName: String, time: Duration?) {
    if (time?.inWholeSeconds != 0.toLong()) {
        Row {
            Text(text = timeName, style = Typography.body)
            Text(
                text = "$time",
                style = Typography.bodyBold
            )
        }
    }
}

@Preview
@Composable
fun RecipeDetailTimeCompositionPreview() {
    Box(modifier = Modifier.background(color = Color.White)) {
        RecipeDetailTimeComposition(
            20.toDuration(DurationUnit.MINUTES),
            30.toDuration(DurationUnit.MINUTES),
            80.toDuration(DurationUnit.MINUTES)
        )
    }
}

