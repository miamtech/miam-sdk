package com.miam.kmm_miam_sdk.component.bottomSheet

import com.miam.kmm_miam_sdk.base.mvi.UiEffect
import com.miam.kmm_miam_sdk.base.mvi.UiEvent
import com.miam.kmm_miam_sdk.base.mvi.UiState

enum class BottomSheetContent {
    RECIPE_DETAIL, RECIPE_HELPER, RECIPE_SPONSOR, BASKET_PREVIEW
}

interface BottomSheetContract {

    sealed class Event : UiEvent {
        object GoToDetail: BottomSheetContract.Event()
        object GoToHelper: BottomSheetContract.Event()
        object GoToSponsor: BottomSheetContract.Event()
        object OpenBottomSheet : BottomSheetContract.Event()
        object CloseBottomSheet : BottomSheetContract.Event()
        data class GoToPreview(val recipeId :Int): BottomSheetContract.Event()
    }

    data class State(
        val content: BottomSheetContent,
        val recipeId: Int?,
        val isOpen: Boolean
    ) : UiState

    sealed class Effect : UiEffect {
        object BottomSheetOpened : BottomSheetContract.Effect()
        object BottomSheetClosed : BottomSheetContract.Effect()
    }

}