package com.miam.kmm_miam_sdk.android.ui.components.recipeDetails

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    closeDialogue: () -> Unit
) {
    val state by vmRecipeCard.uiState.collectAsState()
    ManagementResourceState(
        resourceState = state.recipeState,
        successView = { recipe ->
            requireNotNull(recipe)
            recipeDetailCard(recipe, vmRecipeCard, closeDialogue)
        },
        onTryAgain = { vmRecipeCard.setEvent(RecipeContract.Event.Retry) },
        onCheckAgain = { vmRecipeCard.setEvent(RecipeContract.Event.Retry) },
    )
}

@Composable
private fun recipeDetailCard(
    recipe: Recipe,
    vmRecipeCard: RecipeViewModel,
    closeDialogue: () -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .height(245.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = rememberImagePainter(recipe.attributes.mediaUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(245.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(32.dp, 32.dp, 64.dp, 0.dp))
            )

            FloatingActionButton(modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .size(40.dp)
                .alpha(0.5f),
                backgroundColor = Color.Gray,
                onClick = { closeDialogue() })
            {
                Text(
                    text = "X",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }

        // Temps de préparation
        PrepInfos(
            R.string.miam_total_time,
            R.drawable.ic_clock,
            recipe.totalTime
        )

        /*  PrepInfos(
              R.string.miam_prep_time,
              R.drawable.ic_clock,
              recipe.attributes.preparationTime.toString()
          )

          PrepInfos(
              R.string.miam_cook_time,
              R.drawable.ic_clock,
              recipe.attributes.cookingTime.toString()
          )

          PrepInfos(
              R.string.miam_prehat_time,
              R.drawable.ic_clock,
              recipe.attributes.preheatingTime.toString()
          )*/

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
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            CustomActionButton(
                icon = R.drawable.ic_ingredient,
                text = "Les ingredients",
                action = {
                    isIngredientChecked = MiamMasterView.MiamDisplayMode.INGREDIENT_MODE
                },
                isActive = MiamMasterView.MiamDisplayMode.INGREDIENT_MODE == isIngredientChecked
            )
            CustomActionButton(
                icon = R.drawable.ic_preparation,
                text = "Votre recette",
                action = { isIngredientChecked = MiamMasterView.MiamDisplayMode.STEPS_MODE },
                isActive = MiamMasterView.MiamDisplayMode.STEPS_MODE == isIngredientChecked
            )
        }

        Row() {
            RecipeContent(recipe = recipe, displayMode = isIngredientChecked, vmRecipeCard)
        }


        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),

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

@Composable
fun PrepInfos(title: Int, icone: Int, time: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {

        Row() {
            Text(
                text = stringResource(title),
                color = MiamMasterView.Grey02,
                fontSize = 11.sp,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .weight(400f)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
        }
        Row() {
            /*  Image(
                  painter = painterResource(icone),
                  contentDescription = null,
                  colorFilter = ColorFilter.tint(MiamMasterView.black25),
                  modifier = Modifier
                      .size(22.dp)
                      .wrapContentWidth(Alignment.CenterHorizontally)
              )*/
            Text(
                text = time,
                color = MiamMasterView.black25,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .align(Alignment.CenterVertically)
                    .weight(400f)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
        }
    }

}