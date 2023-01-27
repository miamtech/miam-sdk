package com.miam.kmmMiamCore.component.preferencesSearch

import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.UiEffect
import com.miam.kmmMiamCore.base.mvi.UiEvent
import com.miam.kmmMiamCore.base.mvi.UiState
import com.miam.kmmMiamCore.miam_core.model.Tag

public interface PreferencesSearchContract {
    public sealed class Event : UiEvent

    public data class State(
        val searchProposal: BasicUiState<List<Tag>>,
    ) : UiState

    public sealed class Effect : UiEffect
}