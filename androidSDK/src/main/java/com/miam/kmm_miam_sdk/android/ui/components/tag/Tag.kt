package com.miam.kmm_miam_sdk.android.ui.components.tag

import android.content.Context
import android.util.AttributeSet
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.window.Dialog
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.miam.kmm_miam_sdk.android.theme.Colors
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.theme.Typography.bodyBold
import com.miam.kmm_miam_sdk.android.theme.Typography.link
import com.miam.kmm_miam_sdk.android.theme.Typography.overLine
import com.miam.kmm_miam_sdk.android.ui.components.common.Clickable
import com.miam.kmm_miam_sdk.android.ui.components.routerOutlet.RouterOutlet
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsImage
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle
import com.miam.kmm_miam_sdk.android.ui.components.states.ManagementResourceState
import com.miam.kmm_miam_sdk.component.tag.TagContract
import com.miam.kmm_miam_sdk.component.tag.TagViewModel
import com.miam.kmm_miam_sdk.miam_core.model.Recipe

class Tag @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AbstractComposeView(context, attrs, defStyleAttr) {

    private val fullscreen = RouterOutlet()
    private val vmTag = TagViewModel(fullscreen.getViewModel())

    private fun goToDetail(recipe: Recipe){
        vmTag.goToDetail(recipe)
    }

    /**
     * Don't delete use by client
     */
    fun bind (ingredientId:String) {
        vmTag.setEvent(TagContract.Event.SetIngredientId(ingredientId))
    }

    @Composable
    override fun Content() {

        val state by vmTag.uiState.collectAsState()

            Column {
                // Empty at init
                fullscreen.Content()
                ManagementResourceState(
                    resourceState = state.recipeList,
                    successView = { recipes ->
                        requireNotNull(recipes)
                        RecipesTag(
                            recipes,
                            ::goToDetail
                        )
                    },
                    loadingView = {
                        if(Template.recipeLoaderTemplate != null){
                            Template.recipeLoaderTemplate?.let { it() }
                        } else {
                            RecipesTag(
                                emptyList(),
                                ::goToDetail
                            )
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
    private fun RecipesTag(
        recipes: List<Recipe>,
        goToDetails: (recipe: Recipe) -> Unit,
    ){

        val openRecipeLinkDialog = remember { mutableStateOf(false) }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if(openRecipeLinkDialog.value){
                AllRecipeLinkDialog(openRecipeLinkDialog, recipes, goToDetails)
            }
            Clickable(
                onClick = {},
                children = {
                    Box() {
                        Text(text= recipes[0].attributes!!.title, Modifier
                            .border(
                                width = 1.dp,
                                color = Colors.grey,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(
                                horizontal = 8.dp, vertical = 2.dp
                            ), color = Colors.grey ) 
                    }
                }
            )
            Spacer(modifier = Modifier.padding(horizontal = 4.dp))
            if(recipes.size > 1) {
                Clickable(
                    onClick = { openRecipeLinkDialog.value = true},
                    children = {
                        Box(
                            Modifier
                                .border(1.dp, Colors.primary, CircleShape)
                                .padding(horizontal = 4.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "+" + (recipes.size -1),
                                color= Colors.primary,
                                style= overLine,
                                modifier = Modifier.align(Alignment.Center) )
                        }
                    }
                )
            }
        }
    }

    @Composable
    private fun  AllRecipeLinkDialog(
        openRecipeLinkDialog : MutableState<Boolean>,
        recipes : List<Recipe>,
        goToDetails: (recipe: Recipe) -> Unit
    ) {
        Dialog(
            onDismissRequest = {
                openRecipeLinkDialog.value = false
            }
        ) {
            Card() {
                Column (Modifier.padding(16.dp)) {
                    Text(
                        text = "Ce produit est utilisé pour ${recipes.size} recettes",
                        style =  bodyBold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    recipes.forEach {
                        Clickable(
                            onClick = {
                                goToDetails(it)
                                openRecipeLinkDialog.value = false
                            },
                            children = {
                                Text(
                                    text = it.attributes!!.title,
                                    style = link.copy(textDecoration =  TextDecoration.Underline),
                                    color = Colors.primary,
                                    modifier = Modifier.padding(bottom = 16.dp)
                                    )
                            }
                        )
                    }
                    Divider()
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .clickable { openRecipeLinkDialog.value = false }
                    ) {
                        Image(
                            painter = painterResource(RecipeDetailsImage.close),
                            contentDescription = null,
                            modifier = RecipeDetailsStyle.headerCloseIconModifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}