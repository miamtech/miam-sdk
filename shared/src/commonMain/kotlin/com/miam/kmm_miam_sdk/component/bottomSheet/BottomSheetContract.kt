package com.miam.kmm_miam_sdk.component.BottomSheet

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
        object GoToPreview: BottomSheetContract.Event()
    }

    data class State(
        val content: BottomSheetContent
    ) : UiState

    sealed class Effect : UiEffect {}

}