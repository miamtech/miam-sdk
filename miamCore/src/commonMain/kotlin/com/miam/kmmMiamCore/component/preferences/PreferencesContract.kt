package com.miam.kmmMiamCore.component.preferences

import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.UiEffect
import com.miam.kmmMiamCore.base.mvi.UiEvent
import com.miam.kmmMiamCore.base.mvi.UiState
import com.miam.kmmMiamCore.miam_core.model.CheckableTag


interface PreferencesContract {
    sealed class Event : UiEvent

    data class State(
        val basicState: BasicUiState<Boolean>,
        val diets: List<CheckableTag>,
        val ingredients: List<CheckableTag>,
        val equipments: List<CheckableTag>,
        val recipesFound: Int,
        val guests: Int?
    ) : UiState

    sealed class Effect : UiEffect
}