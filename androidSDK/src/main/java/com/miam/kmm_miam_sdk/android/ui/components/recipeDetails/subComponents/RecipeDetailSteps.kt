package com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.subComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.miam.kmmMiamCore.miam_core.model.RecipeStep
import com.miam.kmm_miam_sdk.android.theme.Colors.backgroundGrey
import com.miam.kmm_miam_sdk.android.theme.Colors.black
import com.miam.kmm_miam_sdk.android.theme.Typography
import com.miam.kmm_miam_sdk.android.ui.components.common.CircleChips
import com.miam.kmm_miam_sdk.android.ui.components.common.RoundedCheckbox
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle.stepsMainContainer
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsText

@Composable
fun RecipeDetailSteps(steps: List<RecipeStep>, activeStep: Int, onActiveStep :(currentStep: Int) -> Unit) {

    val stepActiveNumber = remember { mutableStateOf(activeStep) }

    Column(modifier = stepsMainContainer) {
        Text(
            text = RecipeDetailsText.steps,
            style = Typography.subtitleBold,
            color = black
        )
        steps.forEach {
            val stepNumber = it.attributes!!.stepNumber
            Step(
                stepNumber,
                it.attributes!!.stepDescription!!,
                stepActiveNumber.value >= it.attributes!!.stepNumber
            ) {
                stepActiveNumber.value = stepNumber
                onActiveStep(stepNumber)
            }
        }
    }
}

@Composable
fun Step(stepNumber: Int, description: String, isActive: Boolean, onActiveStep: (Int) -> Unit) {


    Surface(Modifier.clip(RoundedCornerShape(16.dp))) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .clickable(onClick = {
                    onActiveStep(stepNumber)
                })
                .then(
                    if (isActive) Modifier.background(
                        backgroundGrey,
                        RoundedCornerShape(20F)
                    ) else Modifier
                )
        ) {
            CircleChips((stepNumber + 1).toString())
            Text(
                text = description,
                fontSize = 16.sp,
                modifier = Modifier
                    .weight(1F)
                    .padding(horizontal = 8.dp, vertical = 4.dp)

            )
            Box(modifier = Modifier.padding(end = 8.dp)){
                RoundedCheckbox(isActive){
                    onActiveStep(stepNumber)
                }
            }
        }
    }
}

