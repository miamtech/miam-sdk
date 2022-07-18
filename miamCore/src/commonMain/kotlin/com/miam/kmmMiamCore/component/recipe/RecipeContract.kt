package com.miam.kmmMiamCore.component.recipe

import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.UiEffect
import com.miam.kmmMiamCore.base.mvi.UiEvent
import com.miam.kmmMiamCore.base.mvi.UiState
import com.miam.kmmMiamCore.miam_core.model.Recipe
import com.miam.kmmMiamCore.miam_core.model.SuggestionsCriteria


enum class TabEnum {
    INGREDIENT, STEP
}

interface RecipeContract {

    sealed class Event : UiEvent {
        data class OnFetchRecipe(val idRecipe: String) : Event()
        data class OnSetRecipe(val recipe: Recipe) : Event()
        data class SetHeader(val header: String) : Event()
        data class OnSetCriteria(val crieria: SuggestionsCriteria): Event()
        object OnUnbind: Event()
        data class UpdateGuest(val nbGuest: Int): Event()
        data class SetActiveStep(val stepIndex:Int): Event()
        object OnAddRecipe: Event()
        object OnToggleLike :Event()
        object DecreaseGuest: Event()
        object IncreaseGuest: Event()
        object ShowIngredient: Event()
        object ShowSteps: Event()
        object Error : Event()
    }

      data class State(
          val recipeState: BasicUiState<Recipe>,
          val recipe: Recipe?,
          val headerText: String,
          val guest: Int,
          val isInCart : Boolean,
          val analyticsEventSent: Boolean,
          val isPriceDisplayed: Boolean,
          val isInViewport : Boolean,
          val tabState : TabEnum,
          val activeStep: Int,
          val recipeLoaded: Boolean,
          val isLiked: Boolean,
          val likeIsEnable: Boolean
    ) : UiState

    sealed class Effect : UiEffect {
        object HideCard : Effect()
    }
}