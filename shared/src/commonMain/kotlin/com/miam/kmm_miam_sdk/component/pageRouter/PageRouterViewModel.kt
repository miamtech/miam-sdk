package com.miam.kmm_miam_sdk.component.pageRouter

import com.miam.kmm_miam_sdk.base.mvi.BaseViewModel

class PageRouterViewModel :  BaseViewModel<PageRouterContract.Event, PageRouterContract.State, PageRouterContract.Effect>() {

    override fun createInitialState(): PageRouterContract.State =
        PageRouterContract.State(
            content= RouterPageContent.RECAP_PAGE,
            vmRecap= null
        )

    override fun handleEvent(event: PageRouterContract.Event) {
        when (event) {
            is PageRouterContract.Event.GoToFavorite -> {
                navigateTo(RouterPageContent.FAVORITE_PAGE)
            }
            is PageRouterContract.Event.GoToCatalog -> {
                navigateTo(RouterPageContent.CATALOG_PAGE)
            }
            is PageRouterContract.Event.GoToRecap -> {
                navigateTo(RouterPageContent.RECAP_PAGE)
            }}
        }

    private fun navigateTo( destination : RouterPageContent) {
        setState { copy(content = destination) }
    }

}