package com.miam.kmmMiamCore.component.recipeCarousel

import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.miam_core.model.Recipe


class RecipeCarouselViewModel :
    com.miam.kmmMiamCore.base.mvi.BaseViewModel<RecipeCarouselContract.Event, RecipeCarouselContract.State, RecipeCarouselContract.Effect>() {
    override fun createInitialState(): RecipeCarouselContract.State = RecipeCarouselContract.State(
        productId = BasicUiState.Loading,
        numberOfResult = 4,
    )

    override fun handleEvent(event: RecipeCarouselContract.Event) {
        when (event) {
            is RecipeCarouselContract.Event.SetProductIdAndResultNumber -> setState {
                copy(
                    productId = BasicUiState.Success(
                        event.productId
                    ), numberOfResult = event.numberOfResult
                )
            }
            is RecipeCarouselContract.Event.SetProductId -> setState {
                copy(
                    productId = BasicUiState.Success(
                        event.productId
                    )
                )
            }
        }
    }

    private fun getSuggestionsCriterias(): List<Recipe> {

    }


}
