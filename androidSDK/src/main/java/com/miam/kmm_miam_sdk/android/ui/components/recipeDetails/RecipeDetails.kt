package com.miam.kmm_miam_sdk.android.ui.components.recipeDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.miam.kmm_miam_sdk.android.R
import com.miam.kmm_miam_sdk.android.ui.components.*
import com.miam.kmm_miam_sdk.android.ui.components.common.Price
import com.miam.kmm_miam_sdk.android.ui.components.common.CustomActionButton
import com.miam.kmm_miam_sdk.android.ui.components.common.MiamMasterView
import com.miam.kmm_miam_sdk.android.ui.components.states.ManagementResourceState
import com.miam.kmm_miam_sdk.component.recipe.RecipeContract
import com.miam.kmm_miam_sdk.component.recipe.RecipeViewModel
import com.miam.kmm_miam_sdk.miam_core.model.Recipe

@Composable
fun recipdeDetails(
    vmRecipeCard: RecipeViewModel,
    openDialog: MutableState<Boolean>
) {
    val state by vmRecipeCard.uiState.collectAsState()
    ManagementResourceState(
        resourceState = state.recipeState,
        successView = { recipe ->
            requireNotNull(recipe)
            recipeDetailCard(recipe, vmRecipeCard, openDialog)
        },
        onTryAgain = { vmRecipeCard.setEvent(RecipeContract.Event.Retry) },
        onCheckAgain = { vmRecipeCard.setEvent(RecipeContract.Event.Retry) },
    )
}

@Composable
private fun recipeDetailCard(
    recipe: Recipe,
    vmRecipeCard: RecipeViewModel,
    openDialog: MutableState<Boolean>
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = rememberImagePainter(recipe.attributes.mediaUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            )

            FloatingActionButton(modifier = Modifier
                .align(Alignment.TopStart)
                .size(24.dp)
                .align(alignment = Alignment.TopEnd),
                backgroundColor = Color.Gray,
                onClick = { openDialog.value = false })
            {
                Text(text = "x", color = Color.White)
            }
        }

        // Temps de préparation
        Row(
            Modifier
                .padding(end = 16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_clock),
                contentDescription = null,
                colorFilter = ColorFilter.tint(MiamMasterView.greenColor),
                modifier = Modifier.size(30.dp)
            )
            Text(
                text = stringResource(id = R.string.miam_prep_time) + recipe.totalTime,
                color = MiamMasterView.greenColor,
                fontSize = 22.sp,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .align(Alignment.CenterVertically)
            )
        }

        // Titre
        Row() {
            Text(
                text = recipe.attributes.title,
                fontFamily = FontFamily.Cursive,
                fontSize = 24.sp,
                style = MaterialTheme.typography.h5.copy(
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .padding(horizontal = 30.dp)
            )
        }
        // Difficulte
        Row(
            Modifier
                .padding(top = 4.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_diflow),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .padding(horizontal = 4.dp)

            )
            Text(
                text = recipe.difficultyLabel,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        // Description
        Row() {
            Text(
                text = "${recipe.attributes.description ?: ' '}",
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        // Switcher ingredients preparation

        // TODO: utiliser une enum dans le theme avec un state
        var isIngredientChecked by remember { mutableStateOf(MiamMasterView.MiamDisplayMode.INGREDIENT_MODE) }

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            CustomActionButton(
                icon = R.drawable.ic_ingredient,
                text = "Ingredients",
                action = {
                    isIngredientChecked = MiamMasterView.MiamDisplayMode.INGREDIENT_MODE
                },
                isActive = MiamMasterView.MiamDisplayMode.INGREDIENT_MODE == isIngredientChecked
            )
            CustomActionButton(
                icon = R.drawable.ic_preparation,
                text = "Préparation",
                action = { isIngredientChecked = MiamMasterView.MiamDisplayMode.STEPS_MODE },
                isActive = MiamMasterView.MiamDisplayMode.STEPS_MODE == isIngredientChecked
            )
        }

        Row() {
            RecipeContent(recipe = recipe, displayMode = isIngredientChecked, vmRecipeCard)
        }


        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Price(recipeId = recipe.id).content()
            CustomActionButton(
                action = { /*TODO*/ },
                icon = R.drawable.ic_cart,
                text = "Sélectionner ce repas",
                isActive = true
            )
        }
    }
}

@Composable
fun RecipeContent(
    recipe: Recipe, displayMode: MiamMasterView.MiamDisplayMode, vmRecipe: RecipeViewModel
) {
    when (displayMode) {
        MiamMasterView.MiamDisplayMode.INGREDIENT_MODE -> RecipeIngredients(recipe, vmRecipe)
        MiamMasterView.MiamDisplayMode.STEPS_MODE -> RecipeSteps(
            recipe.attributes!!.steps!!.steps,
            vmRecipe
        )
    }
}