package com.miam.kmm_miam_sdk.android.ui.components.recipeCard


import android.content.Context
import android.util.AttributeSet
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.AbstractComposeView
import com.miam.kmmMiamCore.component.recipe.RecipeViewModel
import com.miam.kmmMiamCore.miam_core.model.Recipe
import com.miam.kmmMiamCore.miam_core.model.SuggestionsCriteria
import com.miam.kmm_miam_sdk.android.theme.Template
import com.miam.kmm_miam_sdk.android.ui.components.routerOutlet.RouterOutlet
import com.miam.kmm_miam_sdk.android.ui.components.states.ManagementResourceState

class RecipeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): AbstractComposeView(context, attrs, defStyleAttr) {

    private val modal = RouterOutlet()
    private var vmRecipe: RecipeViewModel = RecipeViewModel(modal.getViewModel())
    private val idRecipeState: MutableState<String?> = mutableStateOf(null)
    private var isInShelve = true

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
        isInShelve = false
    }

    var idRecipe: String
        get() = idRecipeState.value ?: ""
        set(value) {
            idRecipeState.value = value
            vmRecipe.fetchRecipe(idRecipe)
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
                RecipeSuccessCard(recipe, vmRecipe, modal, isInShelve)
            },
            loadingView = {
                if (Template.recipeLoaderTemplate != null) {
                    Template.recipeLoaderTemplate?.let { it() }
                } else {
                    RecipeLoadingView()
                }
            },
            emptyView = {
                if (Template.recipeEmptyTemplate != null) {
                    Template.recipeEmptyTemplate?.let { it() }
                } else {
                    Box {}
                }
            }
        )
    }
}
