package com.miam.kmm_miam_sdk.android.ui.components.recipeDetails

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
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
                onClick = { openDialog.value = false })
            {
                Text(
                    text = "X",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
            if (vmRecipeCard.currentState.isInCart) {
                Box(
                    modifier = Modifier
                        .absoluteOffset(x = 8.dp, y = 8.dp)
                        .clip(
                            RoundedCornerShape(
                                topEnd = 4.dp,
                                topStart = 4.dp,
                                bottomStart = 4.dp,
                                bottomEnd = 4.dp
                            )
                        )
                        .background(Color(0xffF47F7A))
                ) {
                    Row(
                        modifier = Modifier.padding(
                            horizontal = 5.dp,
                            vertical = 10.dp
                        ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {

                        Text(
                            text = "Déjà ajoutée", color = Color.White,
                            modifier = Modifier.padding(horizontal = 5.dp)
                        )
                    }
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .wrapContentWidth(CenterHorizontally)
                .fillMaxWidth()
        ) {
            // Temps de préparation
            PrepInfos(
                R.string.miam_total_time,
                R.drawable.ic_clock,
                recipe.totalTime
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .wrapContentWidth(CenterHorizontally)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            PrepInfos(
                R.string.miam_prep_time,
                R.drawable.ic_knife,
                recipe.attributes.preparationTime.toString()
            )

            PrepInfos(
                R.string.miam_cook_time,
                R.drawable.ic_oven,
                recipe.attributes.cookingTime.toString()
            )

            PrepInfos(
                R.string.miam_prehat_time,
                R.drawable.ic_resttime,
                recipe.attributes.preheatingTime.toString()
            )
        }
        // Titre
        Row() {
            Text(
                text = recipe.attributes.title,
                fontFamily = FontFamily.Cursive,
                fontSize = 32.sp,
                style = MaterialTheme.typography.h5.copy(
                    color = MiamMasterView.Secondary,
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
                .padding(horizontal = 16.dp)
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
        Row(
            Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
        ) {
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
                .padding(5.dp, 16.dp),

            ) {
            Price(recipeId = recipe.id).content()
            if (vmRecipeCard.currentState.isInCart) {
                CustomActionButton(
                    action = { /*TODO*/ },
                    icon = R.drawable.ic_cart,
                    text = "Voir le détail",
                    isActive = true
                )
            } else {
                CustomActionButton(
                    action = { /*TODO*/ },
                    icon = R.drawable.ic_cart,
                    text = "Sélectionner ce repas",
                    isActive = true
                )
            }
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
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(title),
            color = MiamMasterView.Grey02,
            fontSize = 11.sp,
            modifier = Modifier
                .padding(top = 4.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(icone),
                contentDescription = null,
                colorFilter = ColorFilter.tint(MiamMasterView.black25),
                modifier = Modifier
                    .size(22.dp)

            )
            Text(
                text = time,
                color = MiamMasterView.black25,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(4.dp, 6.dp)
                    .align(Alignment.CenterVertically)
                    .wrapContentHeight(Alignment.Top)
            )
        }
    }

}