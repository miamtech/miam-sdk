package com.miam.kmm_miam_sdk.component.bottomSheet

import com.miam.kmm_miam_sdk.base.mvi.BaseViewModel

open class BottomSheetViewModel :
    BaseViewModel<BottomSheetContract.Event, BottomSheetContract.State, BottomSheetContract.Effect>() {

    override fun createInitialState(): BottomSheetContract.State =
        BottomSheetContract.State(
            content= BottomSheetContent.RECIPE_DETAIL
        )

    override fun handleEvent(event: BottomSheetContract.Event) {
        when (event) {
            is BottomSheetContract.Event.GoToDetail -> setState { copy(content = BottomSheetContent.RECIPE_DETAIL) }
            is BottomSheetContract.Event.GoToHelper -> setState { copy(content = BottomSheetContent.RECIPE_HELPER) }
            is BottomSheetContract.Event.GoToSponsor ->  setState { copy(content = BottomSheetContent.RECIPE_SPONSOR) }
            is BottomSheetContract.Event.GoToPreview -> setState { copy(content = BottomSheetContent.BASKET_PREVIEW) }
        }
    } }
