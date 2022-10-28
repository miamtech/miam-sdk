package com.miam.kmmMiamCore.component.preferencesSearch

import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.UiEffect
import com.miam.kmmMiamCore.base.mvi.UiEvent
import com.miam.kmmMiamCore.base.mvi.UiState
import com.miam.kmmMiamCore.miam_core.model.Tag

interface PreferencesSearchContract {
    sealed class Event : UiEvent

    data class State(
        val searchProposal: BasicUiState<List<Tag>>,
    ) : UiState

    sealed class Effect : UiEffect
}