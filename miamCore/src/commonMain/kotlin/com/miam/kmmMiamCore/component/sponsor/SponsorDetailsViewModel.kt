package com.miam.kmmMiamCore.component.sponsor

import com.miam.core.sdk.di.MiamDI.sponsorBlockRepository
import com.miam.kmmMiamCore.base.mvi.BaseViewModel
import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.handler.LogHandler
import com.miam.kmmMiamCore.miam_core.model.Sponsor
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

public open class SponsorDetailsViewModel: BaseViewModel<SponsorDetailsContract.Event, SponsorDetailsContract.State, SponsorDetailsContract.Effect>() {
    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        LogHandler.error("Miam error in sponsor view $exception ${exception.stackTraceToString()}")
    }

    private fun defaultState(): SponsorDetailsContract.State {
        return SponsorDetailsContract.State(BasicUiState.Loading)
    }

    init {
       setState { defaultState() }
    }

    public fun fetchSponsorBlockByIds(sponsor: Sponsor) {
        setState { copy(sponsorBlocks = BasicUiState.Loading, sponsor = sponsor) }
        launch(coroutineHandler) {
            val sponsorBlocks = sponsorBlockRepository.getSponsorBlocksBySponsorId(sponsorId = sponsor.id)
            setState { copy(sponsorBlocks = BasicUiState.Success(sponsorBlocks)) }
        }
    }

    override fun createInitialState(): SponsorDetailsContract.State {
        return defaultState()
    }

    override fun handleEvent(event: SponsorDetailsContract.Event) {

    }
}