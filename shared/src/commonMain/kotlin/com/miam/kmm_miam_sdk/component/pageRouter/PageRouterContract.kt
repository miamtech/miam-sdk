package com.miam.kmm_miam_sdk.component.pageRouter

import com.miam.kmm_miam_sdk.base.mvi.UiEffect
import com.miam.kmm_miam_sdk.base.mvi.UiEvent
import com.miam.kmm_miam_sdk.base.mvi.UiState
import com.miam.kmm_miam_sdk.component.recapPage.RecapPageViewModel


enum class RouterPageContent {
    FAVORITE_PAGE, CATALOG_PAGE, RECAP_PAGE
}

interface PageRouterContract {
    sealed class Event : UiEvent {
        object GoToFavorite: PageRouterContract.Event()
        object GoToCatalog: PageRouterContract.Event()
        object GoToRecap : PageRouterContract.Event()
    }

    data class State(
        val content: RouterPageContent,
        val vmRecap: RecapPageViewModel?,
    ) : UiState

    sealed class Effect : UiEffect {
    }
}