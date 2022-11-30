package com.miam.kmm_miam_sdk.android.ui.components.recipeDetails


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomAppBar
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.miam.kmmMiamCore.component.recipe.RecipeContract
import com.miam.kmmMiamCore.component.recipe.RecipeViewModel
import com.miam.kmmMiamCore.component.router.RouterOutletContract
import com.miam.kmmMiamCore.component.router.RouterOutletViewModel
import com.miam.kmmMiamCore.miam_core.model.Recipe
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.theme.Template.recipeDetailIngredientTemplate
import com.miam.kmm_miam_sdk.android.theme.Template.recipeDetailStepsTemplate
import com.miam.kmm_miam_sdk.android.theme.Typography.subtitleBold
import com.miam.kmm_miam_sdk.android.ui.components.likeButton.LikeButton
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsColor.footerSectionBackgroundColor
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle.mainColumnsContainer
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle.recipeDetailsActionsContainer
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle.recipeImageModifier
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle.titleModifier
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.subComponents.RecipeDetailFooter
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.subComponents.RecipeDetailSteps
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.subComponents.RecipeDetailTimeComposition
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.subComponents.RecipeDetailsHeader
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.subComponents.RecipeDifficultyAndTiming
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.subComponents.RecipeIngredients
import com.miam.kmm_miam_sdk.android.ui.components.states.ManagementResourceState


@Composable
fun RecipeDetails(
    vmRecipeCard: RecipeViewModel,
    vmRouter: RouterOutletViewModel,
    closeDialogue: () -> Unit
) {

    val state by vmRecipeCard.uiState.collectAsState()

    ManagementResourceState(
        resourceState = state.recipeState,
        successView = { recipe ->
            requireNotNull(recipe)
            recipeDetailContent(
                recipe,
                vmRecipeCard,
                vmRouter,
                closeDialogue,
                vmRouter.currentState.showFooter
            )
        },
        onTryAgain = { },
        onCheckAgain = { },
        loadingView = { CircularProgressIndicator() }
    )
}

@Composable
private fun recipeDetailContent(
    recipe: Recipe,
    vmRecipeCard: RecipeViewModel,
    vmRouter: RouterOutletViewModel,
    closeDialogue: () -> Unit,
    withBottomBar: Boolean = true
) {
    val scrollState = rememberScrollState()

    fun seeProductMatching() {
        vmRouter.setEvent(
            RouterOutletContract.Event.GoToPreview(
                recipeId = recipe.id,
                vm = vmRecipeCard
            )
        )
    }

    fun buy() {
        vmRecipeCard.setEvent(RecipeContract.Event.OnAddRecipe)
        vmRouter.setEvent(
            RouterOutletContract.Event.GoToPreview(
                recipeId = recipe.id,
                vm = vmRecipeCard
            )
        )
    }

    Scaffold(
        topBar = {
            if (Template.recipeDetailHeaderTemplate != null) {
                Template.recipeDetailHeaderTemplate?.let { it({ closeDialogue() }, recipe) }
            } else {
                RecipeDetailsHeader(recipe.attributes!!.title, scrollState.value) {
                    closeDialogue()
                }
            }
        },
        content =
        {
            Column(
                modifier = mainColumnsContainer.verticalScroll(scrollState)
            ) {
                if (Template.recipeDetailInfosTemplate != null) {
                    Template.recipeDetailInfosTemplate!!({ closeDialogue() }, recipe)
                } else {
                    Image(
                        painter = rememberImagePainter(recipe.attributes!!.mediaUrl),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = recipeImageModifier
                    )
                    ActionRow(
                        vmRecipeCard.currentState.likeIsEnable,
                        recipe.id
                    )
                    Divider()
                    Text(
                        text = recipe.attributes!!.title,
                        modifier = titleModifier,
                        textAlign = TextAlign.Left,
                        style = subtitleBold
                    )
                    RecipeDifficultyAndTiming(recipe.attributes!!.difficulty!!, recipe.totalTime)
                    RecipeDetailTimeComposition(recipe.attributes!!.preparationTime, recipe.attributes!!.cookingTime, recipe.attributes!!.restingTime)
                }

                if (recipeDetailIngredientTemplate != null) {
                    recipeDetailIngredientTemplate!!(recipe, vmRecipeCard)
                } else {
                    Divider(Modifier.padding(8.dp))
                    RecipeIngredients(recipe, vmRecipeCard)
                }
                if (recipeDetailStepsTemplate != null) {
                    recipeDetailStepsTemplate!!(
                        recipe.sortedStep,
                        vmRecipeCard
                    )
                } else {
                    RecipeDetailSteps(
                        recipe.sortedStep,
                        vmRecipeCard.currentState.activeStep
                    ) { currentStep: Int ->
                        vmRecipeCard.setEvent(RecipeContract.Event.SetActiveStep(currentStep))
                    }
                    Spacer(modifier = Modifier.padding(vertical = 50.dp))
                }

            }
        },
        bottomBar = {
            if (withBottomBar) {
                BottomAppBar(backgroundColor = footerSectionBackgroundColor) {
                    if (Template.recipeDetailFooterTemplate != null) {
                        Template.recipeDetailFooterTemplate!!(
                            recipe,
                            vmRecipeCard,
                            { seeProductMatching() },
                            { buy() }
                        )
                    } else {
                        RecipeDetailFooter(recipe.id,
                            vmRecipeCard.currentState.guest,
                            vmRecipeCard.currentState.isInCart,
                            { seeProductMatching() },
                            { buy() }
                        )
                    }
                }
            } else {
                Surface {}
            }
        }
    )
}


@Composable
fun ActionRow(likeIsEnable: Boolean, recipeId: String) {
    if (likeIsEnable) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = recipeDetailsActionsContainer
        ) {
            val likeButton = LikeButton()
            likeButton.bind(recipeId)
            likeButton.Content()
        }
    }
}



