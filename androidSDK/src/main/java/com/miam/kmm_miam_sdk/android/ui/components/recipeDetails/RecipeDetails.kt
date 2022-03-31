package com.miam.kmm_miam_sdk.android.ui.components.recipeDetails

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
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
import com.miam.kmm_miam_sdk.component.pricing.PricingContract
import com.miam.kmm_miam_sdk.component.recipe.RecipeContract
import com.miam.kmm_miam_sdk.component.recipe.RecipeViewModel
import com.miam.kmm_miam_sdk.component.recipe.TabEnum
import com.miam.kmm_miam_sdk.component.router.RouterContract
import com.miam.kmm_miam_sdk.component.router.RouterViewModel
import com.miam.kmm_miam_sdk.miam_core.model.Recipe
import kotlin.time.Duration.Companion.minutes
import kotlin.time.ExperimentalTime


@Composable
fun recipdeDetails(
    vmRecipeCard: RecipeViewModel,
    vmRouter : RouterViewModel,
    closeDialogue: () -> Unit
) {

    val state by vmRecipeCard.uiState.collectAsState()
    ManagementResourceState(
        resourceState = state.recipeState,
        successView = { recipe ->
            requireNotNull(recipe)
            recipeDetailCard(recipe, vmRecipeCard,vmRouter, closeDialogue)
        },
        onTryAgain = { vmRecipeCard.setEvent(RecipeContract.Event.Retry) },
        onCheckAgain = { vmRecipeCard.setEvent(RecipeContract.Event.Retry) },
        loadingView = {   CircularProgressIndicator() }
    )
}

@Composable
private fun recipeDetailCard(
    recipe: Recipe,
    vmRecipeCard: RecipeViewModel,
    vmRouter : RouterViewModel,
    closeDialogue: () -> Unit
) {
    Scaffold(
        content = {
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
                painter = rememberImagePainter(recipe.attributes!!.mediaUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(245.dp)
                    .fillMaxWidth()
                    .graphicsLayer { alpha = 0.99f }
                    .drawWithContent {
                        val colors = listOf(
                            Color.Transparent,
                            Color.Black
                        )
                        drawContent()
                        drawRect(
                            brush = Brush.verticalGradient(colors),
                        )}
                    .clip(RoundedCornerShape(0.dp, 0.dp, 64.dp, 0.dp))
            )

            FloatingActionButton(modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .size(40.dp)
                .alpha(0.5f),
                backgroundColor = Color.Gray,
                onClick = { closeDialogue() })
            {
                Icon(
                    tint = Color.White,
                    imageVector = Icons.Default.Close,
                    contentDescription = "close"
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
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .wrapContentWidth(CenterHorizontally)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            if (recipe.attributes!!.preparationTime?.compareTo(0.minutes) != 0) {
                PrepInfos(
                    R.string.miam_prep_time,
                    R.drawable.ic_knife,
                    recipe.attributes!!.preparationTime.toString()
                )
            }
            if (recipe.attributes!!.cookingTime?.compareTo(0.minutes) != 0) {
                PrepInfos(
                    R.string.miam_cook_time,
                    R.drawable.ic_oven,
                    recipe.attributes!!.cookingTime.toString()
                )
            }
            if (recipe.attributes!!.preheatingTime?.compareTo(0.minutes) != 0) {
                PrepInfos(
                    R.string.miam_prehat_time,
                    R.drawable.ic_resttime,
                    recipe.attributes!!.preheatingTime.toString()
                )
            }
        }
        // Titre
        Row() {
            Text(
                text = recipe.attributes!!.title,
                fontFamily = FontFamily(
                    Font(R.font.satisfy_regular)),
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
                .padding(horizontal = 16.dp, vertical = 24.dp)
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

        Canvas(
            Modifier
                .fillMaxWidth()
        ) {
            drawLine(
                color = MiamMasterView.Primary,
                start = Offset(32f, 0f),
                end = Offset(size.width - 32, 0f),
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(12f, 12f), 0f),
                strokeWidth = 1.5f
            )
        }

        // Switcher ingredients preparation
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 24.dp)
        ) {
            CustomActionButton(
                icon = R.drawable.ic_ingredient,
                text = "Ingredients",
                action = { vmRecipeCard.setEvent(RecipeContract.Event.ShowIngredient) },
                isActive = vmRecipeCard.currentState.tabState == TabEnum.INGREDIENT
            )
            Spacer(Modifier.padding(horizontal = 8.dp))
            CustomActionButton(
                icon = R.drawable.ic_preparation,
                text = "Préparation",
                action = { vmRecipeCard.setEvent(RecipeContract.Event.ShowSteps) },
                isActive = vmRecipeCard.currentState.tabState == TabEnum.STEP
            )
        }

        Row() {
            RecipeContent(recipe = recipe, vmRecipeCard)
        }
        Spacer(modifier = Modifier.padding(vertical = 50.dp))
    }
},
        bottomBar = { BottomAppBar(backgroundColor = Color.White) {  Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {

                Price(recipeId = recipe.id, vmRecipeCard.currentState.guest).content()
                if (vmRecipeCard.currentState.isInCart) {
                    CustomActionButton(
                        action = { vmRouter.setEvent(
                            RouterContract.Event.GoToPreview(
                                recipeId = recipe.id,
                                vm = vmRecipeCard
                            )
                        ) },
                        icon = R.drawable.ic_cart,
                        text = "Voir le détail",
                        isActive = true
                    )
                } else {
                    CustomActionButton(
                        action = {vmRecipeCard.setEvent(RecipeContract.Event.OnAddRecipe)
                            vmRouter.setEvent(
                                RouterContract.Event.GoToPreview(
                                    recipeId = recipe.id,
                                    vm = vmRecipeCard
                                )
                            ) },
                        icon = R.drawable.ic_cart,
                        text = "Sélectionner ce repas",
                        isActive = true
                    )
                }
            }

        }
        }
    )}

@Composable
fun RecipeContent(
    recipe: Recipe, vmRecipe: RecipeViewModel
) {
    when (vmRecipe.currentState.tabState) {
        TabEnum.INGREDIENT -> RecipeIngredients(recipe, vmRecipe)
        TabEnum.STEP -> RecipeSteps(
            recipe.relationships!!.recipeSteps!!.data,
            vmRecipe
        )
    }
}

@Composable
fun PrepInfos(title: Int, icon: Int, time: String) {
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
                painter = painterResource(icon),
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