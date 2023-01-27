package com.miam.kmmMiamCore.component.router

import com.miam.kmmMiamCore.base.mvi.UiEffect
import com.miam.kmmMiamCore.base.mvi.UiEvent
import com.miam.kmmMiamCore.base.mvi.UiState
import com.miam.kmmMiamCore.component.recipe.RecipeViewModel

public enum class RouterContent {
    RECIPE_DETAIL, RECIPE_HELPER, RECIPE_SPONSOR, BASKET_PREVIEW, ITEMS_SELECTOR, EMPTY
}

public interface RouterOutletContract {

    public sealed class Event: UiEvent {
        public object GoToHelper: Event()
        public object GoToSponsor: Event()
        public object OpenDialog: Event()
        public object GoToItemSelector: Event()
        public data class GoToPreview(val recipeId: String, val vm: RecipeViewModel): Event()
        public data class GoToDetail(val vm: RecipeViewModel, val withFooter: Boolean = true): Event()
        public object Previous: Event()
    }

    public data class State(
        val content: RouterContent,
        val rvm: RecipeViewModel?,
        val recipeId: String?,
        val isOpen: Boolean,
        val showFooter: Boolean
    ): UiState

    public sealed class Effect: UiEffect
}

