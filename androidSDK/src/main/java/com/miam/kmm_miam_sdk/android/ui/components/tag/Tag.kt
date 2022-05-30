package com.miam.kmm_miam_sdk.android.ui.components.tag

import android.content.Context
import android.telecom.Call
import android.util.AttributeSet
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.ui.window.Dialog
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.res.painterResource
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.ui.components.common.Clickable
import com.miam.kmm_miam_sdk.android.ui.components.dialog.Dialog
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsImage
import com.miam.kmm_miam_sdk.android.ui.components.recipeDetails.RecipeDetailsStyle
import com.miam.kmm_miam_sdk.android.ui.components.states.ManagementResourceState
import com.miam.kmm_miam_sdk.component.tag.TagViewModel
import com.miam.kmm_miam_sdk.miam_core.model.Recipe

class Tag @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AbstractComposeView(context, attrs, defStyleAttr) {

    private val vmTag = TagViewModel()
    private val fullscreen = Dialog()

    @Composable
    override fun Content() {

        val state by vmTag.uiState.collectAsState()

            Column {
                // Empty at intial state
                fullscreen.Content()
                ManagementResourceState(
                    resourceState = state.recipeList,
                    successView = { recipes ->
                        requireNotNull(recipes)
                        RecipesTag(recipes ?: emptyList())
                    },
                    loadingView = {
                        if(Template.recipeLoaderTemplate != null){
                            Template.recipeLoaderTemplate?.let { it() }
                        } else {
                            Box{}
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
        openDetails: () -> Unit,
        closeDetails: () -> Unit
    ){

        val openDialog = remember { mutableStateOf(true) }

        Row() {
            if(openDialog.value){

            }
            Clickable(
                onClick = {},
                children = {
                    Box() {
                        Text(text= recipes[0].attributes!!.title)
                    }
                }
            )
            if(recipes.size > 1) {
                Clickable(
                    onClick = {},
                    children = {
                        Box() {
                            Text(text = "+" + recipes.size )
                        }
                    }
                )
            }
        }
    }

    @Composable
    private fun  AllRecipeLinkDialog(){
        Dialog(
            onDismissRequest = {
                openDialog.value = false
            }
        ) {
            Column {
                Text(text = "Ce produit est utilisé pour ${recipes.size} recettes")
                recipes.forEach {
                    Clickable(
                        onClick = { },
                        children = {
                            Text(text = it.attributes!!.title)
                        }
                    )
                }
                Divider()
                Button(onClick = {}) {
                    Image(
                        painter = painterResource(RecipeDetailsImage.close),
                        contentDescription = null,
                        modifier = RecipeDetailsStyle.headerCloseIconModifier
                    )
                }
            }
        }
    }
}