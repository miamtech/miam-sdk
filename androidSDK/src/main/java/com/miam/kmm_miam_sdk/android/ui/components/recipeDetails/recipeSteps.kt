package com.miam.kmm_miam_sdk.android.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.miam.kmm_miam_sdk.android.theme.Colors
import com.miam.kmm_miam_sdk.android.theme.Colors.primary
import com.miam.kmm_miam_sdk.android.theme.Typography
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle.stepsMainContainer
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsText
import com.miam.kmm_miam_sdk.component.recipe.RecipeContract
import com.miam.kmm_miam_sdk.component.recipe.RecipeViewModel
import com.miam.kmm_miam_sdk.miam_core.model.RecipeStep

@Composable
fun RecipeSteps(steps: List<RecipeStep>,vmRecipe: RecipeViewModel) {
    val state: RecipeContract.State = vmRecipe.uiState.collectAsState().value

    val onActivestep: (currentStep: Int) -> Unit =
        { currentStep: Int -> vmRecipe.setEvent(RecipeContract.Event.SetActiveStep(currentStep)) }

    Column( modifier = stepsMainContainer ) {
        Text(
            text = RecipeDetailsText.steps ,
            style = Typography.subtitleBold,
            color = primary
        )
        Divider(Modifier.padding(8.dp))
        steps.forEach {
            var stepNumber = it.attributes!!.stepNumber
            Step(
                stepNumber,
                it.attributes!!.description!!,
                state.activeStep >= it.attributes!!.stepNumber,
                { onActivestep(stepNumber) }
            )
        }
    }
}

@Composable
fun Step(stepNumber: Int, description: String, isActive: Boolean, onActivestep: (Int) -> Unit) {

    Surface(Modifier.clip(RoundedCornerShape(16.dp))) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .clickable(onClick = { onActivestep(stepNumber) })
        ) {
            CircleChips((stepNumber+1).toString())

            Text(
                text = description,
                fontSize = 16.sp,
                modifier = Modifier.weight(1F)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )

            Checkbox(
                checked = isActive,
                colors =  CheckboxDefaults.colors(primary),
                onCheckedChange = { onActivestep(stepNumber) }
            )

        }
    }
}


