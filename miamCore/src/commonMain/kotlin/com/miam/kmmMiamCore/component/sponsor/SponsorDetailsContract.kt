package com.miam.kmmMiamCore.component.sponsor

import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.UiEffect
import com.miam.kmmMiamCore.base.mvi.UiEvent
import com.miam.kmmMiamCore.base.mvi.UiState
import com.miam.kmmMiamCore.miam_core.model.Sponsor
import com.miam.kmmMiamCore.miam_core.model.SponsorBlock

public interface SponsorDetailsContract {
    public sealed class Event: UiEvent {

    }
    public sealed class Effect: UiEffect {

    }
    public data class State(
        val sponsorBlocks: BasicUiState<List<SponsorBlock>>,
        val sponsor: Sponsor? = null
    ): UiState
}