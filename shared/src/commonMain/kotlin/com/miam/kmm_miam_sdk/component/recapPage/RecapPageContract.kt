package com.miam.kmm_miam_sdk.component.recapPage

import com.miam.kmm_miam_sdk.base.mvi.UiEffect
import com.miam.kmm_miam_sdk.base.mvi.UiEvent
import com.miam.kmm_miam_sdk.base.mvi.UiState


interface RecapPageContract {
    sealed class Event : UiEvent {

    }

    data class State(

    ) : UiState

    sealed class Effect : UiEffect {
    }
}