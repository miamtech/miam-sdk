package com.miam.kmm_miam_sdk.component.recapPage

import com.miam.kmm_miam_sdk.base.mvi.BaseViewModel
import com.miam.kmm_miam_sdk.base.mvi.BasicUiState
import com.miam.kmm_miam_sdk.base.mvi.BasketEffect
import com.miam.kmm_miam_sdk.base.mvi.BasketStore
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.core.component.inject


class RecapPageViewModel :
    BaseViewModel<RecapPageContract.Event, RecapPageContract.State, RecapPageContract.Effect>() {

    private val coroutineHandler = CoroutineExceptionHandler {
            _, exception -> println(" [ERROR][Miam][MyMeal] $exception")
    }
    private val basketStore : BasketStore by inject()


    override fun createInitialState(): RecapPageContract.State =
        RecapPageContract.State(
            lines= BasicUiState.Loading,
            bpls= null,
            isReloading= false,
            openIdx = -1,
            job= null
        )

    init {
        val job = launch(coroutineHandler) {
            basketStore.observeSideEffect().filter { basketEffect -> basketEffect == BasketEffect.BasketPreviewChange }.collect{
                val bpls = basketStore.observeState().first {
                    it.basketPreview != null && it.basketPreview.isNotEmpty()
                }.basketPreview!!
                setState { copy(lines = BasicUiState.Success(bpls),bpls = bpls, isReloading= false) }
            }
        }
        setState { copy(job = job) }
    }

    override fun handleEvent(event: RecapPageContract.Event) {

    }
}