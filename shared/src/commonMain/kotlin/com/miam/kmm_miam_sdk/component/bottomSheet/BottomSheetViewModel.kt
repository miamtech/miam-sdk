package com.miam.kmm_miam_sdk.component.bottomSheet

import com.miam.kmm_miam_sdk.base.mvi.BaseViewModel


open class BottomSheetViewModel :
    BaseViewModel<BottomSheetContract.Event, BottomSheetContract.State, BottomSheetContract.Effect>() {

    override fun createInitialState(): BottomSheetContract.State =
      // TODO remove mock values
        BottomSheetContract.State(
            content= BottomSheetContent.BASKET_PREVIEW,
            recipeId = 1,
            isOpen = false
        )

    override fun handleEvent(event: BottomSheetContract.Event) {
        when (event) {
            is BottomSheetContract.Event.GoToDetail -> navigateTo( BottomSheetContent.RECIPE_DETAIL)
            is BottomSheetContract.Event.GoToHelper -> navigateTo (BottomSheetContent.RECIPE_HELPER)
            is BottomSheetContract.Event.GoToSponsor -> navigateTo(BottomSheetContent.RECIPE_SPONSOR)
            is BottomSheetContract.Event.GoToPreview -> {
                setState { copy(recipeId = event.recipeId) }
                navigateTo( BottomSheetContent.BASKET_PREVIEW) }
            BottomSheetContract.Event.OpenBottomSheet -> {
                setState { copy(isOpen = true) }
                setEffect { BottomSheetContract.Effect.BottomSheetOpened }
            }
            BottomSheetContract.Event.CloseBottomSheet -> {
                    setState { copy(isOpen = false) }
                    setEffect { BottomSheetContract.Effect.BottomSheetClosed }
                }
            }

        }

     private fun navigateTo( destination : BottomSheetContent) {
         setState { copy(content = destination) }
         if (!uiState.value.isOpen) {
             setEvent(BottomSheetContract.Event.OpenBottomSheet)
         }
     }
    }

