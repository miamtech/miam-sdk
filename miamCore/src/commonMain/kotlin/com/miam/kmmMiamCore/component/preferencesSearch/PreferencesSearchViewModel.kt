package com.miam.kmmMiamCore.component.preferencesSearch

import com.miam.core.sdk.di.MiamDI
import com.miam.kmmMiamCore.base.mvi.BaseViewModel
import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.handler.LogHandler
import com.miam.kmmMiamCore.miam_core.data.repository.TagsRepositoryImp
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

public open class PreferencesSearchViewModel:
    BaseViewModel<PreferencesSearchContract.Event, PreferencesSearchContract.State, PreferencesSearchContract.Effect>() {


    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        LogHandler.error(" [ERROR][Miam][PreferencesSearchViewModel] $exception")
    }

    private val tagsRepositoryImp: TagsRepositoryImp = MiamDI.tagsRepository
    private val rememberedLastSearch = MutableStateFlow("")


    override fun createInitialState(): PreferencesSearchContract.State = PreferencesSearchContract.State(searchProposal = BasicUiState.Empty)

    override fun handleEvent(event: PreferencesSearchContract.Event) {
        TODO("Not yet implemented")
    }

    public fun resetState() {
        setState { copy(searchProposal = BasicUiState.Empty) }
    }

    public fun search(search: String) {
        rememberedLastSearch.value = search
        if (currentState.searchProposal is BasicUiState.Loading) return

        if (search.isBlank()) return setState { copy(searchProposal = BasicUiState.Empty) }

        setState { copy(searchProposal = BasicUiState.Loading) }
        launch(coroutineHandler) {
            val tags = tagsRepositoryImp.autocomplete(search)
            setState { copy(searchProposal = BasicUiState.Success(tags.toList())) }
            // user kept typing
            if (rememberedLastSearch.value != search) search(rememberedLastSearch.value)
        }
    }
}
