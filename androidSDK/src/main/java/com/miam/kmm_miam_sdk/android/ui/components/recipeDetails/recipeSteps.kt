package com.miam.kmm_miam_sdk.android.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.miam.kmm_miam_sdk.android.ui.components.common.MiamMasterView
import com.miam.kmm_miam_sdk.component.recipe.RecipeContract
import com.miam.kmm_miam_sdk.component.recipe.RecipeViewModel
import com.miam.kmm_miam_sdk.miam_core.model.RecipeStep


@Composable
fun RecipeSteps(steps: List<RecipeStep>,vmRecipe: RecipeViewModel) {
    val state: RecipeContract.State = vmRecipe.uiState.collectAsState().value

    val onActivestep: (currentStep: Int) -> Unit =
        { currentStep: Int -> vmRecipe.setEvent(RecipeContract.Event.SetActiveStep(currentStep)) }

    Column(modifier = Modifier.fillMaxSize()) {
        steps.forEach {
            var stepNumber = it.attributes!!.stepNumber
            Step(
                stepNumber,
                it.attributes!!.description,
                state.activeStep >= it.attributes!!.stepNumber,
                { onActivestep(stepNumber) }
            )
        }
    }
}

@Composable
fun Step(stepNumber: Int, description: String?, isActive: Boolean, onActivestep: (Int) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .clickable(onClick = { onActivestep(stepNumber) })
    ) {
        CircleChips(stepNumber.toString())

        Text(
            text = description.toString(),
            color = MiamMasterView.textColor,
            fontSize = 16.sp,
            modifier = Modifier.fillMaxWidth(0.9f)
        )
        if (isActive) {
            Icon(Icons.Rounded.CheckCircle, contentDescription = "Etape effectué", tint = Color.Red)
        }

    }
}


