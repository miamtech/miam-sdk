package com.miam.kmm_miam_sdk.android.ui.components.recipeCard

import android.content.Context
import android.util.AttributeSet
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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

import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.miam.kmm_miam_sdk.android.ui.components.common.*

import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardStyle.image

import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardStyle.recipeCardFlagPositionContainer

import com.miam.kmm_miam_sdk.android.ui.components.states.ManagementResourceState

import com.miam.kmm_miam_sdk.android.theme.Colors.primary
import com.miam.kmm_miam_sdk.android.theme.Colors.white

import com.miam.kmm_miam_sdk.android.theme.Dimension.mPadding
import com.miam.kmm_miam_sdk.android.theme.Dimension.sPadding
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.theme.Typography

import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardStyle.cardLayout
import com.miam.kmm_miam_sdk.android.ui.components.recipeCard.RecipeCardStyle.imageContainer
import com.miam.kmm_miam_sdk.android.ui.components.dialog.Dialog
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsImage
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle
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
                emptyView =  {
                    if(Template.recipeEmptyTemplate !=  null){
                        Template.recipeEmptyTemplate?.let {it()}
                    } else {
                        Box{}
                    }
                },
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
            Column {
                Card(modifier = cardLayout) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
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

                                }
                                Text(
                                    text = recipe.attributes!!.title,
                                    modifier = RecipeDetailsStyle.titleModifier,
                                    textAlign = TextAlign.Left,
                                    style = Typography.subtitleBold
                                )

                            }
                            Box(modifier = recipeCardFlagPositionContainer) {
                                    Box(){
                                        Surface (

                                            Modifier
                                                .clip(
                                                    RoundedCornerShape(
                                                        topEnd = 70f,
                                                        bottomEnd = 70f
                                                    )
                                                )
                                                .height(40.dp)
                                                .align(Alignment.Center),
                                                elevation= 8.dp) 
                                        {   
                                            Row(
                                                Modifier
                                                    .background(white)
                                                    .width(180.dp)) {
                                                
                                            }
                                        }
                                        Row(
                                            modifier = Modifier.padding(
                                                horizontal = sPadding,
                                                vertical = mPadding
                                            ).align(Alignment.Center),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.Center
                                        ) {
                                            Surface(
                                                modifier = Modifier.size(64.dp),
                                                shape = CircleShape,
                                                elevation = 8.dp
                                            ) {
                                                Image(
                                                    painter = painterResource(RecipeDetailsImage.recipeIcon),
                                                    contentDescription = null,
                                                    modifier = Modifier.size(62.dp)
                                                )

                                            }

                                            Text(
                                                text = "Idée repas",
                                                style = Typography.subtitleBold,
                                                modifier = Modifier.padding(horizontal = sPadding)
                                            )
                                        }
                                    }

                            }


                        }
                            Clickable(
                                onClick = { modal.goToDetail(vmRecipe) },
                                children = {
                                Surface(
                                    modifier = Modifier
                                        .padding(horizontal = 16.dp, vertical = 10.dp)
                                        .clip(RoundedCornerShape(80f)),
                                    elevation = 8.dp
                                ) {
                                    Row(Modifier.background(
                                        primary
                                    ).padding(horizontal = 16.dp, vertical = 10.dp)) {
                                        Text(
                                            text = "Décourvir la recette",
                                            color = white,
                                            style = Typography.subtitleBold
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




