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

    private var vmRecipe: RecipeViewModel = RecipeViewModel()

    fun bind(
        recipeId: String = "",
    ) {
        //TODO

    }

    @Composable
    override fun Content() {
        ManagementResourceState(
            resourceState = state.recipeState,
            successView = { 
                //TODO
            },
            loadingView = {
               //TODO
            },
            emptyView = {
            
            }
        )
    }

}
