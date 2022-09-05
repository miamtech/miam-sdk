package com.miam.kmm_miam_sdk.android.ui.components.recipeCard


import android.content.Context
import android.util.AttributeSet
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.miam.kmmMiamCore.component.recipe.RecipeContract
import com.miam.kmmMiamCore.component.recipe.RecipeViewModel
import com.miam.kmmMiamCore.miam_core.model.Recipe
import com.miam.kmmMiamCore.miam_core.model.SuggestionsCriteria
import com.miam.kmm_miam_sdk.android.ressource.Image.recipeIcon
import com.miam.kmm_miam_sdk.android.theme.Colors.grey
import com.miam.kmm_miam_sdk.android.theme.Colors.primary
import com.miam.kmm_miam_sdk.android.theme.Colors.white
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.theme.Typography
import com.miam.kmm_miam_sdk.android.ui.components.common.Clickable
import com.miam.kmm_miam_sdk.android.ui.components.likeButton.LikeButton
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardImage.cart
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardImage.check
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardImage.difficulty
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardImage.time
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardStyle.cardLayout
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardStyle.image
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardStyle.imageContainer
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardStyle.recipeCardFlagPositionContainer
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle
import com.miam.kmm_miam_sdk.android.ui.components.routerOutlet.RouterOutlet
import com.miam.kmm_miam_sdk.android.ui.components.states.ManagementResourceState

class RecipeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AbstractComposeView(context, attrs, defStyleAttr) {

    private val modal = RouterOutlet()
    private var vmRecipe: RecipeViewModel = RecipeViewModel(modal.getViewModel())
    private val idRecipeState: MutableState<String?> = mutableStateOf(null)
    private var isInshelve = true

    fun bind(
        recipeId: String = "",
        criteria: SuggestionsCriteria? = null,
        recipe: Recipe? = null
    ) {
        if (recipeId != "") {
            vmRecipe.fetchRecipe(recipeId)
        } else if (criteria != null) {
            vmRecipe.setRecipeFromSuggestion(criteria)
        } else if (recipe != null) {
            vmRecipe.setRecipe(recipe)
        }

    }

    fun unbind() {
        vmRecipe.unsetRecipe()
    }

    fun isNotInShelf() {
        isInshelve = false
    }

    var idRecipe: String
        get() = idRecipeState.value ?: ""
        set(value) {
            idRecipeState.value = value
            if (value != null) {
                vmRecipe.fetchRecipe(idRecipe)
            }
        }

    @Composable
    override fun Content() {
        Column {
            modal.Content()
            UpdatableContent()
        }
    }

    @Composable
    private fun UpdatableContent() {
        val state by vmRecipe.uiState.collectAsState()

        ManagementResourceState(
            resourceState = state.recipeState,
            successView = { recipe ->
                requireNotNull(recipe)
                RecipeCard(recipe, vmRecipe)
            },
            loadingView = {
                if (Template.recipeLoaderTemplate != null) {
                    Template.recipeLoaderTemplate?.let { it() }
                } else {
                    RecipeCardLoading()
                }
            },
            emptyView = {
                if (Template.recipeEmptyTemplate != null) {
                    Template.recipeEmptyTemplate?.let { it() }
                } else {
                    Box {}
                }
            },
            onTryAgain = { },
            onCheckAgain = { },
        )
    }

    @Composable
    private fun RecipeCardLoading() {

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
            end = Offset(
                x = translateAnimation.value,
                y = translateAnimation.value
            )
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            shimmerRecipeCard(brush)
        }
    }


    @Composable
    fun shimmerRecipeCard(brush: Brush) {

        Column {
            Spacer(
                modifier = Modifier
                    .height(280.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(brush = brush)
            )
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
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Column(
                        Modifier.padding(end = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(
                            modifier = Modifier
                                .padding(vertical = 4.dp)
                                .height(12.dp)
                                .width(12.dp)
                                .clip(RoundedCornerShape(100))
                                .background(brush = brush)
                        )
                        Spacer(
                            modifier = Modifier
                                .padding(vertical = 4.dp)
                                .height(12.dp)
                                .width(30.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(brush = brush)
                        )
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
                        Spacer(
                            modifier = Modifier
                                .padding(vertical = 4.dp)
                                .height(12.dp)
                                .width(12.dp)
                                .clip(RoundedCornerShape(100))
                                .background(brush = brush)
                        )
                        Spacer(
                            modifier = Modifier
                                .padding(vertical = 4.dp)
                                .height(12.dp)
                                .width(30.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(brush = brush)
                        )

                    }
                }
                Row {
                    Spacer(
                        modifier = Modifier
                            .padding(vertical = 2.dp)
                            .height(12.dp)
                            .width(30.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(brush = brush)
                    )
                    Spacer(
                        modifier = Modifier
                            .height(12.dp)
                            .width(30.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(brush = brush)
                    )
                }

            }
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    Modifier.padding(
                        horizontal = 8.dp,
                        vertical = 8.dp,
                    ), verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .height(24.dp)
                            .width(24.dp)
                            .clip(RoundedCornerShape(100))
                            .background(brush = brush)
                    )
                    Spacer(
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .height(32.dp)
                            .width(32.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(brush = brush)
                    )
                    Spacer(
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .height(32.dp)
                            .width(48.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(brush = brush)
                    )
                    Spacer(
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .height(32.dp)
                            .width(32.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(brush = brush)
                    )
                }
                Spacer(
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .height(36.dp)
                        .width(36.dp)
                        .clip(RoundedCornerShape(100))
                        .background(brush = brush)
                )
            }
        }
    }

    @Composable
    private fun RecipeCard(
        recipe: Recipe,
        vmRecipe: RecipeViewModel
    ) {
        if (Template.recipeCardTemplate != null) {
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
            Column {
                Card(modifier = cardLayout) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.height(500.dp)
                    ) {
                        Box {
                            Column(verticalArrangement = Arrangement.SpaceBetween) {
                                Box(modifier = imageContainer) {
                                    Clickable(
                                        onClick = { vmRecipe.goToDetail() },
                                        children = {
                                            Image(
                                                painter = rememberImagePainter(recipe.attributes!!.mediaUrl),
                                                contentDescription = null,
                                                contentScale = ContentScale.Crop,
                                                modifier = image
                                            )
                                        }
                                    )

                                }
                                Clickable(
                                    onClick = { vmRecipe.goToDetail() }) {
                                    Text(
                                        text = recipe.attributes!!.title,
                                        modifier = RecipeDetailsStyle.titleModifier,
                                        textAlign = TextAlign.Center,
                                        style = Typography.subtitleBold
                                    )
                                }
                                Row(
                                    Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.SpaceBetween,
                                        modifier = Modifier.padding(horizontal = 16.dp)
                                    ) {
                                        Image(
                                            painter = painterResource(time),
                                            contentDescription = null,
                                            colorFilter = ColorFilter.tint(grey),
                                            modifier = Modifier.size(20.dp)
                                        )
                                        Text(
                                            text = recipe.totalTime, color = grey,
                                            modifier = Modifier.padding(top = 8.dp)
                                        )
                                    }
                                    Divider(
                                        Modifier
                                            .background(grey)
                                            .height(40.dp)
                                            .width(1.dp)
                                    )
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier.padding(horizontal = 16.dp)
                                    ) {
                                        Image(
                                            painter = painterResource(difficulty),
                                            contentDescription = null,
                                            colorFilter = ColorFilter.tint(grey),
                                            modifier = Modifier.size(20.dp)
                                        )
                                        Text(
                                            text = recipe.difficultyLabel,
                                            color = grey,
                                            modifier = Modifier.padding(top = 8.dp)
                                        )
                                    }
                                }
                            }
                            if (vmRecipe.currentState.likeIsEnable) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    LikeButton(vmRecipe)
                                }
                            }
                            if (isInshelve) {
                                Box(modifier = recipeCardFlagPositionContainer) {
                                    Image(
                                        painter = painterResource(recipeIcon),
                                        contentDescription = null,
                                        modifier = Modifier.width(120.dp)
                                    )
                                }
                            }
                        }
                        Clickable(
                            onClick = {
                                if (vmRecipe.currentState.isInCart) modal.goToDetail(vmRecipe) else {
                                    vmRecipe.setEvent(RecipeContract.Event.OnAddRecipe)
                                    modal.goToPreview(recipe.id, vmRecipe)
                                }
                            },
                            children = {
                                Surface(
                                    modifier = Modifier
                                        .padding(horizontal = 16.dp, vertical = 10.dp)
                                        .clip(RoundedCornerShape(80f))
                                        .border(
                                            border = BorderStroke(
                                                1.dp,
                                                if (vmRecipe.currentState.isInCart) primary else Color.Transparent
                                            ),
                                            shape = RoundedCornerShape(50)
                                        ),
                                    elevation = 8.dp
                                ) {
                                    Row(
                                        Modifier
                                            .background(
                                                if (vmRecipe.currentState.isInCart) white else primary
                                            )
                                            .padding(horizontal = 16.dp, vertical = 10.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = if (vmRecipe.currentState.isInCart) "Voir le détail" else "Ajouter les ingrédients",
                                            color = if (vmRecipe.currentState.isInCart) primary else white,
                                            style = Typography.subtitle
                                        )
                                        Image(
                                            painter = painterResource(if (vmRecipe.currentState.isInCart) check else cart),
                                            contentDescription = null,
                                            colorFilter = ColorFilter.tint(if (vmRecipe.currentState.isInCart) primary else white),
                                            modifier = Modifier
                                                .padding(start = 8.dp)
                                                .size(20.dp)
                                        )

                                    }

                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
