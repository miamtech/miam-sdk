package com.miam.kmmMiamCore.component.preferences

import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.UiEffect
import com.miam.kmmMiamCore.base.mvi.UiEvent
import com.miam.kmmMiamCore.base.mvi.UiState
import com.miam.kmmMiamCore.miam_core.model.CheckableTag


public enum class PreferencesContent { ALL_PREFRENCES, SEARCH_PREFRERENCES }

public interface PreferencesContract {
    public sealed class Event : UiEvent

    public data class State(
        val basicState: BasicUiState<PreferencesContent>,
        val diets: List<CheckableTag>,
        val ingredients: List<CheckableTag>,
        val equipments: List<CheckableTag>,
        val recipesFound: Int,
        val guests: Int?
    ): UiState

    public sealed class Effect: UiEffect
}