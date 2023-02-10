package com.miam.core.sdk.viewModels.sponsorDetailViewModel

import com.miam.core.sdk.di.MiamDI
import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.UiState
import com.miam.kmmMiamCore.miam_core.model.Sponsor
import com.miam.kmmMiamCore.miam_core.model.SponsorBlock
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

public data class SponsorDetailState(
    val sponsorBlocks: BasicUiState<List<SponsorBlock>>,
    val sponsor: Sponsor? = null
): UiState


public interface SponsorDetailViewModel {
    public fun fetchSponsorBlockByIds(sponsor: Sponsor)
}

public class SponsorDetailViewModelImpl: CoroutineScope by CoroutineScope(Dispatchers.Main), SponsorDetailViewModel {

    private val _uiState = MutableStateFlow(SponsorDetailState(BasicUiState.Loading))
    private val sponsorBlockRepository = MiamDI.sponsorBlockRepository
    public val state: StateFlow<SponsorDetailState> = _uiState

    public override fun fetchSponsorBlockByIds(sponsor: Sponsor) {
        _uiState.value = state.value.copy(sponsorBlocks = BasicUiState.Loading, sponsor = sponsor)
        launch {
            val sponsorBlocks = sponsorBlockRepository.getSponsorBlocksBySponsorId(sponsorId = sponsor.id)
            _uiState.value = state.value.copy(sponsorBlocks = BasicUiState.Success(sponsorBlocks))
        }
    }
}

