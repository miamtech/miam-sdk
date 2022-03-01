package com.miam.kmm_miam_sdk.component.recipe

import com.miam.kmm_miam_sdk.base.mvi.BasicUiState
import com.miam.kmm_miam_sdk.base.mvi.UiEffect
import com.miam.kmm_miam_sdk.base.mvi.UiEvent
import com.miam.kmm_miam_sdk.base.mvi.UiState
import com.miam.kmm_miam_sdk.miam_core.model.Recipe


enum class TabEnum {
    INGREDIENT, STEP
}

interface RecipeContract {

    sealed class Event : UiEvent {
        data class OnGetRecipe(val idRecipe: Int) : Event()
        data class OnSetRecipe(val recipe: Recipe) : Event()
        data class SetHeader(val header: String) : Event()
        data class UpdateGuest(val nbGuest: Int): Event()
        data class SetActiveStep(val stepIndex:Int): Event()
        object OnAddRecipe: Event()
        object DecreaseGuest: Event()
        object IncreaseGuest: Event()
        object ShowIngredient: Event()
        object ShowSteps: Event()
        object Retry : Event()
    }

      data class State(
          val recipeState: BasicUiState<Recipe>,
          val headerText: String,
          val guest: Int,
          val isInCart : Boolean,
          val analyticsEventSent: Boolean,
          val isPriceDisplayed: Boolean,
          val isInViewport : Boolean,
          val tabState : TabEnum,
          val activeStep: Int,
    ) : UiState

    sealed class Effect : UiEffect {
        object HideCard : Effect()
    }
}