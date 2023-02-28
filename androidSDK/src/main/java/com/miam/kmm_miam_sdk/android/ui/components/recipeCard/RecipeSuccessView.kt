package com.miam.kmm_miam_sdk.android.ui.components.recipeCard

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.miam.kmmMiamCore.component.recipe.RecipeContract
import com.miam.kmmMiamCore.component.recipe.RecipeViewModel
import com.miam.kmmMiamCore.miam_core.model.Recipe
import com.miam.kmmMiamCore.miam_core.model.fake.RecipeFakeFactory
import com.miam.kmm_miam_sdk.android.theme.Colors
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.theme.Typography
import com.miam.kmm_miam_sdk.android.ui.components.common.Clickable
import com.miam.kmm_miam_sdk.android.ui.components.likeButton.LikeButton
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardText.difficultyHigh
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardText.difficultyLow
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardText.difficultyMedium
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle
import com.miam.kmm_miam_sdk.android.ui.components.routerOutlet.RouterOutlet

@Composable
fun RecipeSuccessCard(
    recipe: Recipe,
    vmRecipe: RecipeViewModel,
    modal: RouterOutlet,
    isInShelve: Boolean
) {

    @Composable
    fun TemplateView() {

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
    }

    if (Template.recipeCardTemplate != null) {
        TemplateView()
    } else {
        Column {
            Card(
                modifier = RecipeCardStyle.cardLayout,
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.height(330.dp)
                ) {
                    // relative container
                    Box {
                        RecipeCardImageView(recipe.attributes!!.mediaUrl) {
                            vmRecipe.goToDetail()
                        }
                        // absolut positioning
                        if (vmRecipe.currentState.likeIsEnable) {
                            RecipeLikeButton(recipe.id)
                        }
                        // absolut positioning
                        if (isInShelve) {
                            RecipeFloatingTag()
                        }
                    }
                    RecipeCardTitleView(recipe.attributes!!.title) {
                        vmRecipe.goToDetail()
                    }
                    RecipeCardMetricsView(recipe)
                    RecipeCardCTAView(vmRecipe.currentState.isInCart) {
                        if (vmRecipe.currentState.isInCart) {
                            modal.goToDetail(vmRecipe)
                        } else {
                            vmRecipe.setEvent(RecipeContract.Event.OnAddRecipe)
                            modal.goToPreview(recipe.id, vmRecipe)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RecipeCardImageView(recipePicture: String, goToDetail: () -> Unit) {
    Box(modifier = RecipeCardStyle.imageContainer) {
        Clickable(
            onClick = { goToDetail() },
            children = {
                Image(
                    painter = rememberImagePainter(recipePicture),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = RecipeCardStyle.image
                )
            }
        )

    }
}

@Composable
fun RecipeCardTitleView(title: String, goToDetail: () -> Unit) {
    Clickable(
        onClick = { goToDetail() }) {
        Text(
            text = title,
            modifier = RecipeDetailsStyle.titleModifier,
            textAlign = TextAlign.Center,
            style = Typography.bodySmallBold
        )
    }
}

@Composable
fun RecipeCardMetricsView(recipe: Recipe) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        MetricView(recipe.totalTime, RecipeCardImage.time)
        Divider(
            Modifier
                .background(Colors.grey)
                .height(30.dp)
                .width(1.dp)
        )
        when (recipe.attributes!!.difficulty) {
            1 -> MetricView(difficultyLow, RecipeCardImage.difficultyLow)
            2 -> MetricView(difficultyMedium, RecipeCardImage.difficultyMid)
            3 -> MetricView(difficultyHigh, RecipeCardImage.difficultyHard)
        }
    }
}

@Composable
fun MetricView(text: String, image: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Colors.grey),
            modifier = Modifier.size(18.dp)
        )
        Text(
            text = text,
            color = Colors.grey,
            style = Typography.overLine,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun RecipeLikeButton(recipeId: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, end = 8.dp),
        horizontalArrangement = Arrangement.End
    ) {
        val likeButton = LikeButton()
        likeButton.bind(recipeId)
        likeButton.Content()
    }
}

@Composable
fun RecipeFloatingTag() {
    Box(modifier = RecipeCardStyle.recipeCardFlagPositionContainer) {
        Image(
            painter = painterResource(com.miam.kmm_miam_sdk.android.ressource.Image.recipeIcon),
            contentDescription = null,
            modifier = Modifier.width(120.dp)
        )
    }
}

@Composable
fun RecipeCardCTAView(
    isInCart: Boolean,
    actionOnClick: () -> Unit
) {
    Clickable(
        onClick = {
            actionOnClick()
        }
    )
    {
        Surface(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 10.dp)
                .clip(RoundedCornerShape(80f))
                .border(
                    border = BorderStroke(
                        1.dp,
                        if (isInCart) Colors.primary else Color.Transparent
                    ),
                    shape = RoundedCornerShape(50)
                ),
            elevation = 8.dp
        ) {
            Row(
                Modifier
                    .background(
                        if (isInCart) Colors.white else Colors.primary
                    )
                    .padding(horizontal = 20.dp, vertical = 9.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isInCart) RecipeCardText.isInCart else RecipeCardText.addToCart,
                    color = if (isInCart) Colors.primary else Colors.white,
                    style = Typography.bodySmallBold
                )
                Image(
                    painter = painterResource(if (isInCart) RecipeCardImage.check else RecipeCardImage.cart),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(if (isInCart) Colors.primary else Colors.white),
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .size(12.dp)
                )
            }
        }
    }
}


@Preview
@Composable
fun RecipeSuccessCardPreview() {
    val router = RouterOutlet()
    RecipeSuccessCard(RecipeFakeFactory.create(), RecipeViewModel(routerVM = router.getViewModel()), router, true)
}