package com.miam.kmm_miam_sdk.component.router

import com.miam.kmm_miam_sdk.base.mvi.*
import com.miam.kmm_miam_sdk.component.basketPreview.BasketPreviewViewModel
import com.miam.kmm_miam_sdk.component.recipe.RecipeViewModel
import kotlinx.coroutines.Job

enum class RouterContent {
    RECIPE_DETAIL, RECIPE_HELPER, RECIPE_SPONSOR, BASKET_PREVIEW , ITEMS_SELECTOR
}

interface RouterOutletContract {

        sealed class Event : UiEvent {
            object GoToHelper: RouterOutletContract.Event()
            object GoToSponsor: RouterOutletContract.Event()
            object OpenDialog : RouterOutletContract.Event()
            object CloseDialog : RouterOutletContract.Event()
            object CloseDialogFromPreview : RouterOutletContract.Event()
            object GoToItemSelector: RouterOutletContract.Event()
            data class GoToPreview(val recipeId: String, val vm: RecipeViewModel): RouterOutletContract.Event()
            data class GoToDetail (val vm: RecipeViewModel, val withFooter: Boolean = true) :RouterOutletContract.Event()
            data class GoToDetailFromPreview (val vm: RecipeViewModel ) :RouterOutletContract.Event()
            data class SetRouterContent(val routerContent: RouterContent) :RouterOutletContract.Event()
        }

        data class State(
            val content: RouterContent,
            val rvm: RecipeViewModel?,
            val bpvm :BasketPreviewViewModel?,
            val recipeId: String?,
            val isOpen: Boolean,
            val showFooter:Boolean
        ) : UiState

        sealed class Effect : UiEffect {
        }
}

