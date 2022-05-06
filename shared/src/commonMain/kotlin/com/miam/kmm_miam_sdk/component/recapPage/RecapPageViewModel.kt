package com.miam.kmm_miam_sdk.component.recapPage

import com.miam.kmm_miam_sdk.base.mvi.BaseViewModel


class RecapPageViewModel :
    BaseViewModel<RecapPageContract.Event, RecapPageContract.State, RecapPageContract.Effect>() {


    override fun createInitialState(): RecapPageContract.State =
        RecapPageContract.State(
        )

    override fun handleEvent(event: RecapPageContract.Event) {

    }
}