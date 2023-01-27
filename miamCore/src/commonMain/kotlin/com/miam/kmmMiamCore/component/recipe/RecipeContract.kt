package com.miam.kmmMiamCore.component.recipe

import com.miam.kmmMiamCore.base.mvi.*
import com.miam.kmmMiamCore.component.preferences.PreferencesViewModelInstance
import com.miam.kmmMiamCore.miam_core.model.Recipe


public enum class TabEnum {
    INGREDIENT, STEP
}

public interface RecipeContract {

    public sealed class Event: UiEvent {
        public data class SetActiveStep(val stepIndex: Int): Event()
        public object OnAddRecipe: Event()
        public object ShowIngredient: Event()
        public object ShowSteps: Event()
        public object Error: Event()
    }

    public data class State(
        val recipeState: BasicUiState<Recipe>,
        val recipe: Recipe?,
        val headerText: String,
        val guest: Int,
        val guestUpdating: Boolean = false,
        val isInCart: Boolean,
        val analyticsEventSent: Boolean,
        val isPriceDisplayed: Boolean,
        val isInViewport: Boolean,
        val tabState: TabEnum,
        val activeStep: Int,
        val recipeLoaded: Boolean,
        val likeIsEnable: Boolean,
        val show_event_sent: Boolean = false
    ): UiState {
        public fun refreshFromGl(groceriesListStore: GroceriesListStore): State {
            val isInCart = retrieveIsInCart(groceriesListStore)
            return this.copy(
                isInCart = isInCart,
                guest = retrieveGuest(isInCart, groceriesListStore)
            )
        }

        private fun retrieveIsInCart(groceriesListStore: GroceriesListStore): Boolean {
            if (recipe?.id == null) return false
            return groceriesListStore.observeState().value.groceriesList?.hasRecipe(recipe.id)
                ?: false
        }

        private fun retrieveGuest(isInCart: Boolean, groceriesListStore: GroceriesListStore): Int {
            if (isInCart) {
                val currentGl = groceriesListStore.observeState().value.groceriesList
                return (currentGl?.attributes?.recipesInfos?.find { ri -> ri.id.toString() == recipe?.id })?.guests
                    ?: 4
            }
            return PreferencesViewModelInstance.instance.globalGuestCountOrDefault()
        }
    }

    public sealed class Effect: UiEffect {
        public object HideCard: Effect()
        public object Disliked: Effect()
    }
}