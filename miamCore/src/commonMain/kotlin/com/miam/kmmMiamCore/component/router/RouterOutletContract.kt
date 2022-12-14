package com.miam.kmmMiamCore.component.router

import com.miam.kmmMiamCore.base.mvi.UiEffect
import com.miam.kmmMiamCore.base.mvi.UiEvent
import com.miam.kmmMiamCore.base.mvi.UiState
import com.miam.kmmMiamCore.component.recipe.RecipeViewModel

enum class RouterContent {
    RECIPE_DETAIL, RECIPE_HELPER, RECIPE_SPONSOR, BASKET_PREVIEW, ITEMS_SELECTOR, EMPTY
}

interface RouterOutletContract {

    sealed class Event: UiEvent {
        object GoToHelper: Event()
        object GoToSponsor: Event()
        object OpenDialog: Event()
        object CloseDialog: Event()
        object CloseDialogFromPreview: Event()
        object GoToItemSelector: Event()
        data class GoToPreview(val recipeId: String, val vm: RecipeViewModel, val pushDetailNavigationStep: Boolean = false): Event()
        data class GoToDetail(val vm: RecipeViewModel, val withFooter: Boolean = true): Event()
        object Previous: Event()
    }

    data class State(
        val content: RouterContent,
        val rvm: RecipeViewModel?,
        val recipeId: String?,
        val isOpen: Boolean,
        val showFooter: Boolean
    ): UiState

    sealed class Effect: UiEffect
}

