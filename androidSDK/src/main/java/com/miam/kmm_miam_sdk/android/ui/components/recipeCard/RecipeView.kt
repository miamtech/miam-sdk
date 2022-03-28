package com.miam.kmm_miam_sdk.android.ui.components.recipeCard

import android.content.Context
import android.util.AttributeSet
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.miam.kmm_miam_sdk.android.ui.components.common.*
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardRessource.addToCartFloatingButtonIcon
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardRessource.difficulty
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardRessource.recipeCardFlagIcon
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardRessource.showRecipeFloatingButtonIcon
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardRessource.time
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardStyleComponent.cardLayout
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardStyleComponent.image
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardStyleComponent.imageContainer
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardStyleComponent.inCartTagBox
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardStyleComponent.inCartTagPadding
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardStyleComponent.metricsDivider
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardStyleComponent.metricsIcon
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardStyleComponent.moreInfoButton
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardStyleComponent.recipeCardFlagContainer
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardStyleComponent.recipeCardFlagImage
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardStyleComponent.recipeCardFlagPositionContainer
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardStyleComponent.recipeMetricsRow
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardStyleComponent.recipeTitle
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardText.alreadyInCart
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardText.recipeFlag
import com.miam.kmm_miam_sdk.android.ui.components.states.ManagementResourceState
import com.miam.kmm_miam_sdk.android.ui.theme.Colors.grey
import com.miam.kmm_miam_sdk.android.ui.theme.Colors.primary
import com.miam.kmm_miam_sdk.android.ui.theme.Colors.white
import com.miam.kmm_miam_sdk.android.ui.theme.Dimension.bigPadding
import com.miam.kmm_miam_sdk.android.ui.theme.Dimension.mediumPadding
import com.miam.kmm_miam_sdk.android.ui.theme.Dimension.smallPadding
import com.miam.kmm_miam_sdk.android.ui.theme.Typography.body1
import com.miam.kmm_miam_sdk.android.ui.theme.Typography.body1White
import com.miam.kmm_miam_sdk.android.ui.theme.Typography.whiteRecipeTitle
import com.miam.kmm_miam_sdk.component.recipe.RecipeContract
import com.miam.kmm_miam_sdk.component.recipe.RecipeViewModel
import com.miam.kmm_miam_sdk.component.router.RouterContract
import com.miam.kmm_miam_sdk.component.router.RouterViewModel
import com.miam.kmm_miam_sdk.miam_core.model.Recipe
import com.miam.kmm_miam_sdk.miam_core.model.SuggestionsCriteria
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class RecipeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AbstractComposeView(context, attrs, defStyleAttr), KoinComponent {

    private var vmRecipe: RecipeViewModel = RecipeViewModel()
    private val idRecipeState: MutableState<Int?> = mutableStateOf(null)
    private val modal = RouterModal()

    fun bind(recipeId: Int = 0, criteria: SuggestionsCriteria? = null) {
        if (recipeId != 0) {
            vmRecipe.setEvent(
                RecipeContract.Event.OnGetRecipe(
                    recipeId
                )
            )
        } else if (criteria != null) {
            vmRecipe.setEvent(
                RecipeContract.Event.OnSetCriteria(
                    criteria
                )
            )
        }
    }

    var idRecipe: Int
        get() = idRecipeState.value ?: 0
        set(value) {
            idRecipeState.value = value
            if (value != null) {
                vmRecipe.setEvent(
                    RecipeContract.Event.OnGetRecipe(
                        value
                    )
                )
            }
        }

    @Composable
    override fun Content() {

        val state by vmRecipe.uiState.collectAsState()

        Column {
            modal.Content()
            ManagementResourceState(
                resourceState = state.recipeState,
                successView = { recipe ->
                    requireNotNull(recipe)
                    RecipeCard(recipe, vmRecipe)
                },
                loadingView = { RecipeCardLoading() },
                onTryAgain = { vmRecipe.setEvent(RecipeContract.Event.Retry) },
                onCheckAgain = { vmRecipe.setEvent(RecipeContract.Event.Retry) },
            )
        }
    }

    @Composable
    private fun RecipeCardLoading(){

        val shimerColors = listOf(
            Color.LightGray.copy(alpha = 0.6F),
            Color.LightGray.copy(alpha = 0.2F),
            Color.LightGray.copy(alpha = 0.6F)
        )

        val transition = rememberInfiniteTransition()
        val translateAnimation = transition.animateFloat(
            initialValue = 0f,
            targetValue = 1000f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 1000,
                    easing = FastOutLinearInEasing
                )
            )
        )

        val brush = Brush.linearGradient(
            colors = shimerColors,
            start = Offset.Zero,
            end= Offset(
                x= translateAnimation.value,
                y=translateAnimation.value
            )
        )
       Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ){
           shimmerRecipeCard(brush)
        }
     }


    @Composable
    fun shimmerRecipeCard(brush: Brush){

        Column() {
            Spacer(modifier = Modifier
                .height(245.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(brush = brush))
            Row(
                Modifier
                    .padding(
                        start = 8.dp,
                        end = 8.dp,
                        top = 32.dp,
                        bottom = 16.dp
                    )
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {
                Row() {
                    Column(
                        Modifier.padding(end = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier
                            .padding(vertical = 4.dp)
                            .height(12.dp)
                            .width(12.dp)
                            .clip(RoundedCornerShape(100))
                            .background(brush = brush))
                        Spacer(modifier = Modifier
                            .padding(vertical = 4.dp)
                            .height(12.dp)
                            .width(30.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(brush = brush))
                    }
                    Divider(
                        color = Color.Gray,
                        modifier = Modifier
                            .height(32.dp)
                            .width(1.dp)
                    )
                    Column(
                        Modifier.padding(start = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier
                            .padding(vertical = 4.dp)
                            .height(12.dp)
                            .width(12.dp)
                            .clip(RoundedCornerShape(100))
                            .background(brush = brush))
                        Spacer(modifier = Modifier
                            .padding(vertical = 4.dp)
                            .height(12.dp)
                            .width(30.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(brush = brush))

                    }
                }
                Row() {
                    Spacer(modifier = Modifier
                        .padding(vertical = 2.dp)
                        .height(12.dp)
                        .width(30.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(brush = brush))
                    Spacer(modifier = Modifier
                        .height(12.dp)
                        .width(30.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(brush = brush))
                }

            }
            Row( Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {
                Row(Modifier.padding(
                    horizontal = 8.dp,
                    vertical = 8.dp,
                ),verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier
                        .padding(start = 4.dp)
                        .height(24.dp)
                        .width(24.dp)
                        .clip(RoundedCornerShape(100))
                        .background(brush = brush))
                    Spacer(modifier = Modifier
                        .padding(start = 4.dp)
                        .height(32.dp)
                        .width(32.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(brush = brush))
                    Spacer(modifier = Modifier
                        .padding(start = 4.dp)
                        .height(32.dp)
                        .width(48.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(brush = brush))
                    Spacer(modifier = Modifier
                        .padding(start = 4.dp)
                        .height(32.dp)
                        .width(32.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(brush = brush))
                }
                Spacer(modifier = Modifier
                    .padding(start = 4.dp)
                    .height(36.dp)
                    .width(36.dp)
                    .clip(RoundedCornerShape(100))
                    .background(brush = brush))

            }
        }
    }

    @Composable
    private fun RecipeCard(
        recipe: Recipe,
        vmRecipe: RecipeViewModel
    )  {
        val price = Price(recipeId = recipe.id, guestNumber = vmRecipe.uiState.value.guest )
        Column {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Box {
                    Column {
                        Box(
                            modifier = Modifier
                                .height(245.dp)
                                .fillMaxWidth()
                        ) {
                            Image(
                                painter = rememberImagePainter(recipe.attributes.mediaUrl),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = image
                            )
                            Text(
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Center,
                                text = recipe.attributes.title,
                                style = whiteRecipeTitle,
                                modifier = recipeTitle.align(Alignment.Center)
                            )
                            if (vmRecipe.currentState.isInCart) {
                                Box( modifier = inCartTagBox ) {
                                    Row(
                                        modifier = inCartTagPadding,
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            text = alreadyInCart,
                                            style = body1White,
                                            modifier = Modifier.padding(horizontal = smallPadding)
                                        )
                                    }
                                }
                            } else {
                                FloatingActionButton(
                                    modifier =  moreInfoButton,
                                    backgroundColor= grey,
                                    onClick = {
                                        routerModal.setEvent(
                                            RouterContract.Event.GoToDetail(
                                                vmRecipe
                                            )
                                        )
                                    }
                                ) {
                                    Text(text = "?", color = white)
                                }
                            }
                        }
                        Row(
                            modifier = recipeMetricsRow,
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row() {
                                Column(
                                    Modifier.padding(end = bigPadding),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Image(
                                        painter = painterResource(time),
                                        contentDescription = "time metric",
                                        modifier = metricsIcon
                                    )
                                    Text(
                                        text = recipe.totalTime,
                                        style = body1,
                                        modifier = Modifier.padding(top = smallPadding)
                                    )
                                }
                                Divider(
                                    modifier = metricsDivider
                                )
                                Column(
                                    Modifier.padding(start = bigPadding),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Image(
                                        painter = painterResource(difficulty),
                                        contentDescription = "difficulty metric",
                                        modifier = metricsIcon
                                    )
                                    Text(
                                        text = recipe.difficultyLabel,
                                        style = body1,
                                        modifier = Modifier.padding(top = smallPadding)
                                    )
                                }
                            }
                            price.content()
                        }
                        Counter(
                            vmRecipe.currentState.guest,
                            isDisable = false,
                            { vmRecipe.setEvent(RecipeContract.Event.IncreaseGuest) },
                            { vmRecipe.setEvent(RecipeContract.Event.DecreaseGuest) },
                            CounterModifier(),

                            )
                    }
                    Box( modifier = recipeCardFlagPositionContainer ) {
                        Box( modifier = recipeCardFlagContainer ) {
                            Row(
                                modifier = Modifier.padding(horizontal = smallPadding, vertical = mediumPadding),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painter = painterResource(recipeCardFlagIcon),
                                    contentDescription = null,
                                    modifier = recipeCardFlagImage
                                )
                                Text(
                                    text = recipeFlag,
                                    style = body1White,
                                    modifier = Modifier.padding(horizontal = smallPadding)
                                )
                            }
                        }
                    }
                    if (vmRecipe.currentState.isInCart) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(end = mediumPadding, bottom = mediumPadding)
                        ) {
                            FloatingActionButton(
                                modifier = Modifier.size(36.dp),
                                backgroundColor = primary,
                                onClick = {
                                    modal.goToPreview(recipe.id,vmRecipe)
                                }) {
                                Image(
                                    painter = painterResource(showRecipeFloatingButtonIcon),
                                    contentDescription = "Show recipe action",
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(end = mediumPadding, bottom = mediumPadding)
                        ) {
                            FloatingActionButton(modifier = Modifier.size(36.dp),
                                backgroundColor = primary,
                                onClick = {
                                    vmRecipe.setEvent(RecipeContract.Event.OnAddRecipe)
                                    modal.goToPreview(recipe.id,vmRecipe)
                                }) {
                                Image(
                                    painter = painterResource(addToCartFloatingButtonIcon),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}




