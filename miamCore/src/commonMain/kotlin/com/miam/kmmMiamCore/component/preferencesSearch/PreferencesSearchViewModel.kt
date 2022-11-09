package com.miam.kmmMiamCore.component.preferencesSearch

import com.miam.kmmMiamCore.base.mvi.BaseViewModel
import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.miam_core.data.repository.TagsRepositoryImp
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.inject

open class PreferencesSearchViewModel:
    BaseViewModel<PreferencesSearchContract.Event, PreferencesSearchContract.State, PreferencesSearchContract.Effect>() {


    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        println(" [ERROR][Miam][PreferencesSearchViewModel] $exception")
    }

    private val tagsRepositoryImp: TagsRepositoryImp by inject()
    private val searchTmp = MutableStateFlow("")


    override fun createInitialState(): PreferencesSearchContract.State = PreferencesSearchContract.State(searchProposal = BasicUiState.Empty)

    override fun handleEvent(event: PreferencesSearchContract.Event) {
        TODO("Not yet implemented")
    }

    fun resetState() {
        setState { copy(searchProposal = BasicUiState.Empty) }
    }

    fun search(search: String) {
        searchTmp.value = search
        if (currentState.searchProposal is BasicUiState.Loading) return
        setState { copy(searchProposal = BasicUiState.Loading) }
        launch(coroutineHandler) {
            val tags = tagsRepositoryImp.autocomplete(search)
            setState { copy(searchProposal = BasicUiState.Success(tags.toList())) }
            if (searchTmp.value != search) {
                if (searchTmp.value.isBlank()) {
                    setState { copy(searchProposal = BasicUiState.Empty) }
                } else {
                    val newSearch = searchTmp.value
                    searchTmp.value = ""
                    search(newSearch)
                }
            }
        }
    }
}