package com.miam.kmm_miam_sdk.android.ui.components.recipeDetails


import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.miam.kmmMiamCore.component.recipe.RecipeContract
import com.miam.kmmMiamCore.component.recipe.RecipeViewModel
import com.miam.kmmMiamCore.component.router.RouterOutletContract
import com.miam.kmmMiamCore.component.router.RouterOutletViewModel
import com.miam.kmmMiamCore.miam_core.model.Recipe
import com.miam.kmm_miam_sdk.android.ressource.Image.cart
import com.miam.kmm_miam_sdk.android.ressource.Image.toggleCaret
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.theme.Template.recipeDetailFooterTemplate
import com.miam.kmm_miam_sdk.android.theme.Template.recipeDetailIngredientTemplate
import com.miam.kmm_miam_sdk.android.theme.Template.recipeDetailStepsTemplate
import com.miam.kmm_miam_sdk.android.theme.Typography.body
import com.miam.kmm_miam_sdk.android.theme.Typography.bodyBold
import com.miam.kmm_miam_sdk.android.theme.Typography.button
import com.miam.kmm_miam_sdk.android.theme.Typography.subtitleBold
import com.miam.kmm_miam_sdk.android.ui.components.RecipeIngredients
import com.miam.kmm_miam_sdk.android.ui.components.RecipeSteps
import com.miam.kmm_miam_sdk.android.ui.components.common.Clickable
import com.miam.kmm_miam_sdk.android.ui.components.likeButton.LikeButton
import com.miam.kmm_miam_sdk.android.ui.components.price.Price
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsColor.buyButtonTextColor
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsColor.footerSectionBackgroundColor
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsColor.goToPreviewTextColor
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsImage.difficultyHard
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsImage.difficultyLow
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsImage.difficultyMid
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsImage.recipeIcon
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsImage.time
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle.buyRecipeButton
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle.buyRecipeButtonIcon
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle.checkProductButton
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle.difficultyAndTimeDivider
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle.difficultyContainer
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle.difficultyIconModifier
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle.footerMainContainer
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle.headerCloseIconModifier
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle.headerMainContainer
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle.headerRecipeIconModifier
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle.mainColumnsContainer
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle.moreInfoSection
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle.recipeDetailsActionsContainer
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle.recipeImageModifier
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle.titleModifier
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle.totalTimeContainer
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle.totalTimeIcon
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsText.addRecipe
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsText.checkBasketPreview
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsText.cookTime
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsText.prepTime
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsText.restingTime
import com.miam.kmm_miam_sdk.android.ui.components.states.ManagementResourceState


@Composable
fun recipdeDetails(
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
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = headerMainContainer,
                ) {
                    Clickable(
                        onClick = { closeDialogue() },
                        children = {
                            Image(
                                painter = painterResource(toggleCaret),
                                contentDescription = null,
                                modifier = headerCloseIconModifier.rotate(180f)
                            )
                        }
                    )
                    Image(
                        painter = painterResource(recipeIcon),
                        contentDescription = null,
                        modifier = headerRecipeIconModifier
                    )
                    if (scrollState.value > 900) {
                        Text(
                            modifier = Modifier.weight(1.0F),
                            text = recipe.attributes!!.title,
                            textAlign = TextAlign.Left,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = bodyBold
                        )
                    } else {
                        Spacer(modifier = Modifier.weight(1.0F))
                    }

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
                    if (vmRecipeCard.currentState.likeIsEnable) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = recipeDetailsActionsContainer
                        ) {
                            LikeButton(vmRecipeCard.currentState.isLiked) {
                                vmRecipeCard.setEvent(
                                    RecipeContract.Event.OnToggleLike
                                )
                            }
                        }
                    }
                    Text(
                        text = recipe.attributes!!.title,
                        modifier = titleModifier,
                        textAlign = TextAlign.Left,
                        style = subtitleBold
                    )
                    Row {
                        Column(
                            modifier = difficultyContainer.weight(1f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            when (recipe.attributes!!.difficulty) {
                                1 -> {
                                    Image(
                                        painter = painterResource(difficultyLow),
                                        contentDescription = null,
                                        modifier = difficultyIconModifier
                                    )
                                    Text(
                                        text = recipe.difficultyLabel
                                    )
                                }
                                2 -> {
                                    Image(
                                        painter = painterResource(difficultyMid),
                                        contentDescription = null,
                                        modifier = difficultyIconModifier
                                    )
                                    Text(
                                        text = recipe.difficultyLabel
                                    )
                                }
                                3 -> {
                                    Image(
                                        painter = painterResource(difficultyHard),
                                        contentDescription = null,
                                        modifier = difficultyIconModifier
                                    )
                                    Text(
                                        text = recipe.difficultyLabel
                                    )
                                }
                            }
                        }
                        Divider(difficultyAndTimeDivider)
                        Column(
                            modifier = totalTimeContainer.weight(1f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(time),
                                contentDescription = null,
                                modifier = totalTimeIcon
                            )
                            Text(text = recipe.totalTime)
                        }
                    }
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = moreInfoSection
                    ) {
                        Column(Modifier.padding(bottom = 16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                if (recipe.attributes!!.preparationTime!!.inWholeSeconds != 0.toLong()) {
                                    Row {
                                        Text(text = prepTime, style = body)
                                        Text(
                                            text = "${recipe.attributes!!.preparationTime}",
                                            style = bodyBold
                                        )
                                    }
                                }
                                if (recipe.attributes!!.cookingTime!!.inWholeSeconds != 0.toLong()) {
                                    Row {
                                        Text(text = cookTime, style = body)
                                        Text(
                                            text = "${recipe.attributes!!.cookingTime}",
                                            style = bodyBold
                                        )
                                    }
                                }
                            }

                            if (recipe.attributes!!.restingTime!!.inWholeSeconds != 0.toLong()) {
                                Row(Modifier.fillMaxWidth()) {
                                    Text(text = restingTime, style = body)
                                    Text(
                                        text = "${recipe.attributes!!.cookingTime}",
                                        style = bodyBold
                                    )
                                }
                            }
                        }
                    }
                }
                if (recipeDetailIngredientTemplate != null) {
                    recipeDetailIngredientTemplate!!(recipe, vmRecipeCard)
                } else {
                    RecipeIngredients(recipe, vmRecipeCard)
                }
                if (recipeDetailStepsTemplate != null) {
                    recipeDetailStepsTemplate!!(
                        recipe.sortedStep,
                        vmRecipeCard
                    )
                } else {
                    RecipeSteps(
                        recipe.sortedStep,
                        vmRecipeCard
                    )
                }
                Spacer(modifier = Modifier.padding(vertical = 50.dp))
            }
        },
        bottomBar = {
            if (withBottomBar) {
                BottomAppBar(backgroundColor = footerSectionBackgroundColor) {
                    if (recipeDetailFooterTemplate != null) {
                        recipeDetailFooterTemplate!!(
                            recipe,
                            vmRecipeCard,
                            { seeProductMatching() },
                            { buy() }
                        )
                    } else {
                        Row(
                            modifier = footerMainContainer,
                            horizontalArrangement = Arrangement.End,
                        ) {
                            Row(
                                Modifier.weight(1F),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Price(
                                    recipeId = recipe.id,
                                    vmRecipeCard.currentState.guest
                                ).content()
                            }
                            if (vmRecipeCard.currentState.isInCart) {

                                Row(
                                    modifier = checkProductButton
                                        .weight(2f)
                                        .clickable { seeProductMatching() },
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Text(
                                        text = checkBasketPreview,
                                        style = button,
                                        color = goToPreviewTextColor

                                    )
                                }

                            } else {
                                Row(
                                    modifier = buyRecipeButton
                                        .weight(2f)
                                        .clickable { buy() },
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Text(
                                        text = addRecipe,
                                        style = button,
                                        color = buyButtonTextColor
                                    )
                                    Image(
                                        painter = painterResource(cart),
                                        contentDescription = null,
                                        modifier = buyRecipeButtonIcon
                                    )
                                }
                            }
                        }
                    }
                }
            } else {
                Surface {}
            }
        }
    )

    BackHandler(true) {
        closeDialogue()
    }
}