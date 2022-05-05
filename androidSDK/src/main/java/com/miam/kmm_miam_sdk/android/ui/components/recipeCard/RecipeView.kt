package com.miam.kmm_miam_sdk.android.ui.components.recipeCard

import android.content.Context
import android.util.AttributeSet
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.miam.kmm_miam_sdk.android.ui.components.common.*
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardImage.addToCartFloatingButtonIcon
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardImage.difficulty
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardImage.recipeCardFlagIcon
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardImage.showRecipeFloatingButtonIcon
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardImage.time
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardStyle.image
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardStyle.inCartTagBox
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardStyle.inCartTagPadding
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardStyle.metricsDivider
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardStyle.metricsIcon
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardStyle.moreInfoButton
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardStyle.recipeCardFlagContainer
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardStyle.recipeCardFlagImage
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardStyle.recipeCardFlagPositionContainer
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardStyle.recipeMetricsRow
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardStyle.recipeTitle
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardText.alreadyInCart
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardText.recipeFlag
import com.miam.kmm_miam_sdk.android.ui.components.states.ManagementResourceState
import com.miam.kmm_miam_sdk.android.theme.Colors.grey
import com.miam.kmm_miam_sdk.android.theme.Colors.primary
import com.miam.kmm_miam_sdk.android.theme.Colors.white
import com.miam.kmm_miam_sdk.android.theme.Dimension.lPadding
import com.miam.kmm_miam_sdk.android.theme.Dimension.mPadding
import com.miam.kmm_miam_sdk.android.theme.Dimension.sPadding
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.theme.Typography.body
import com.miam.kmm_miam_sdk.android.theme.Typography.whiteRecipeTitle
import com.miam.kmm_miam_sdk.android.ui.components.counter.Counter
import com.miam.kmm_miam_sdk.android.ui.components.price.Price
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardStyle.cardLayout
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardStyle.imageContainer
import com.miam.kmm_miam_sdk.android.ui.components.dialog.Dialog
import com.miam.kmm_miam_sdk.component.recipe.RecipeContract
import com.miam.kmm_miam_sdk.component.recipe.RecipeViewModel
import com.miam.kmm_miam_sdk.miam_core.model.Recipe
import com.miam.kmm_miam_sdk.miam_core.model.SuggestionsCriteria
import org.koin.core.component.KoinComponent


class RecipeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AbstractComposeView(context, attrs, defStyleAttr), KoinComponent  {

    private var vmRecipe: RecipeViewModel = RecipeViewModel()
    private val idRecipeState: MutableState<String?> = mutableStateOf(null)
    private val modal = Dialog()

    fun bind(recipeId: String = "",
             criteria: SuggestionsCriteria? = null) {
        if (recipeId != "") {
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

    fun unbind() {
        vmRecipe.setEvent(RecipeContract.Event.OnUnbind)
    }

    var idRecipe: String
        get() = idRecipeState.value ?: ""
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
                loadingView = {
                    if(Template.recipeLoaderTemplate != null){
                        Template.recipeLoaderTemplate?.let { it() }
                    } else {
                        RecipeCardLoading()
                    }
                },
                emptyView =  { Box{} },
                onTryAgain = { },
                onCheckAgain = {  },
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

        if(Template.recipeCardTemplate != null) {

            Template.recipeCardTemplate?.let {
                it(
                    recipe, vmRecipe,
                    { modal.goToDetail(vmRecipe) },
                    {
                        vmRecipe.setEvent(RecipeContract.Event.OnAddRecipe)
                        modal.goToPreview(recipe.id, vmRecipe)
                    }
                )
            }
        } else {

            val price = Price(recipeId = recipe.id, guestNumber = vmRecipe.uiState.value.guest)
            Column {
                Card(modifier = cardLayout) {
                    Box {
                        Column {
                            Box(modifier = imageContainer) {
                                Clickable(
                                    onClick = { modal.goToDetail(vmRecipe) },
                                    children = {
                                        Image(
                                            painter = rememberImagePainter(recipe.attributes!!.mediaUrl),
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                            modifier = image
                                        )
                                    }
                                )
                                Text(
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    textAlign = TextAlign.Center,
                                    text = recipe.attributes!!.title,
                                    style = whiteRecipeTitle,
                                    modifier = recipeTitle.align(Alignment.Center)
                                )
                                if (vmRecipe.currentState.isInCart) {
                                    Box(modifier = inCartTagBox) {
                                        Row(
                                            modifier = inCartTagPadding,
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.Center
                                        ) {
                                            Text(
                                                text = alreadyInCart,
                                                style = body,
                                                color = white,
                                                modifier = Modifier.padding(horizontal = sPadding)
                                            )
                                        }
                                    }
                                } else {
                                    FloatingActionButton(
                                        modifier = moreInfoButton,
                                        backgroundColor = grey,
                                        onClick = {
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
                                        Modifier.padding(end = lPadding),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Image(
                                            painter = painterResource(time),
                                            contentDescription = "time metric",
                                            modifier = metricsIcon
                                        )
                                        Text(
                                            text = recipe.totalTime,
                                            style = body,
                                            modifier = Modifier.padding(top = sPadding)
                                        )
                                    }
                                    Divider(
                                        modifier = metricsDivider
                                    )
                                    Column(
                                        Modifier.padding(start = lPadding),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Image(
                                            painter = painterResource(difficulty),
                                            contentDescription = "difficulty metric",
                                            modifier = metricsIcon
                                        )
                                        Text(
                                            text = recipe.difficultyLabel,
                                            style = body,
                                            modifier = Modifier.padding(top = sPadding)
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
                            )
                        }
                        Box(modifier = recipeCardFlagPositionContainer) {
                            Box(modifier = recipeCardFlagContainer) {
                                Row(
                                    modifier = Modifier.padding(
                                        horizontal = sPadding,
                                        vertical = mPadding
                                    ),
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
                                        style = body,
                                        color = white,
                                        modifier = Modifier.padding(horizontal = sPadding)
                                    )
                                }
                            }
                        }
                        if (vmRecipe.currentState.isInCart) {
                            Box(
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .padding(end = mPadding, bottom = mPadding)
                            ) {
                                FloatingActionButton(
                                    modifier = Modifier.size(36.dp),
                                    backgroundColor = primary,
                                    onClick = {
                                        modal.goToPreview(recipe.id, vmRecipe)
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
                                    .padding(end = mPadding, bottom = mPadding)
                            ) {
                                FloatingActionButton(modifier = Modifier.size(36.dp),
                                    backgroundColor = primary,
                                    onClick = {
                                        vmRecipe.setEvent(RecipeContract.Event.OnAddRecipe)
                                        modal.goToPreview(recipe.id, vmRecipe)
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
}




