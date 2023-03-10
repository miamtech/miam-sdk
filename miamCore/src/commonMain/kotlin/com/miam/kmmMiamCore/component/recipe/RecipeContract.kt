package com.miam.kmmMiamCore.component.recipe

import com.miam.kmmMiamCore.base.mvi.*
import com.miam.kmmMiamCore.component.preferences.PreferencesViewModelInstance
import com.miam.kmmMiamCore.miam_core.model.Recipe

public interface RecipeContract {

    public sealed class Event: UiEvent {
        public data class SetRecipeId(val stepIndex: String): Event()
    }

    public data class State(
        val recipeState: BasicUiState<Recipe>,
    ): UiState {

    }

    public sealed class Effect: UiEffect {

    }
}