package com.miam.kmm_miam_sdk.component.basketPreview

import com.miam.kmm_miam_sdk.base.mvi.BaseViewModel
import com.miam.kmm_miam_sdk.base.mvi.BasketEffect
import com.miam.kmm_miam_sdk.base.mvi.BasketStore
import com.miam.kmm_miam_sdk.miam_core.model.BasketPreviewLine
import kotlinx.coroutines.InternalCoroutinesApi

import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collectLatest
import org.koin.core.component.inject

@OptIn(InternalCoroutinesApi::class)
class BasketPreviewViewModel(val recipeId: Int?):
    BaseViewModel<BasketPreviewContract.Event, BasketPreviewContract.State, BasketPreviewContract.Effect>() {

    private val basketStore : BasketStore by inject()

    init {
        if(recipeId != null){
            launch {
                basketStore.observeSideEffect().collectLatest{
                    basketChange(it)
                }
            }

        }else {

        }
    }

    override fun createInitialState(): BasketPreviewContract.State =
        BasketPreviewContract.State(
            recipeId= null,
            showLines= false,
            lines= emptyList(),
        )

    override fun handleEvent(event: BasketPreviewContract.Event) {
        when (event) {
            is BasketPreviewContract.Event.SetRecipeId -> setRecipeid(event.newRecipeId)
            is BasketPreviewContract.Event.SetLines -> setLines(event.newlines)
            is BasketPreviewContract.Event.toogleLine -> toogleLine()
        }
    }

    private fun setRecipeid(newRecipeId :Int) {
        setState { copy(recipeId = newRecipeId)}
    }

    private fun setLines(newlines: List<BasketPreviewLine>) {
        setState { copy(lines = newlines )}
    }

    private fun toogleLine() {
        setState { copy( showLines = !uiState.value.showLines)}
    }

    private fun basketChange(sideEffect: BasketEffect){
        if (sideEffect == BasketEffect.BasketPreviewChange){
          setEvent(BasketPreviewContract.Event.SetLines(
              basketStore.observeState().value.basketPreview ?: emptyList())
          )
        }

    }
}