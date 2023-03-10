package com.miam.kmmMiamCore.component.singletonFilter

import com.miam.core.sdk.di.MiamDI
import com.miam.core.sdk.localisation.Localisation
import com.miam.kmmMiamCore.base.mvi.BaseViewModel
import com.miam.kmmMiamCore.base.mvi.Effect
import com.miam.kmmMiamCore.handler.LogHandler
import com.miam.kmmMiamCore.miam_core.data.datasource.RecipeFilter
import com.miam.kmmMiamCore.miam_core.data.repository.RecipeRepositoryImp
import com.miam.kmmMiamCore.miam_core.model.CatalogFilterOptions
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

public object FilterViewModelInstance {
    public val instance: SingletonFilterViewModel = MiamDI.recipeFilterViewModel
}

public sealed class FilterEffect: Effect {
    public object OnUpdate: FilterEffect()
}

public open class SingletonFilterViewModel:
    BaseViewModel<SingletonFilterContract.Event, SingletonFilterContract.State, SingletonFilterContract.Effect>() {
    private val recipeRepositoryImp: RecipeRepositoryImp = MiamDI.recipeRepository

    private val sideEffect = MutableSharedFlow<FilterEffect>()
    public fun observeSideEffect(): Flow<FilterEffect> = sideEffect

    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        LogHandler.error("Miam error in singleton filter VM view $exception")
    }

    init {
        launch(coroutineHandler) {
            // uiState.first { it != null } // true ¯\_(ツ)_/¯
            getRecipeCount()
        }
    }

    override fun createInitialState(): SingletonFilterContract.State = initialState

    override fun handleEvent(event: SingletonFilterContract.Event) {
        when (event) {
            is SingletonFilterContract.Event.OnTimeFilterChanged -> {
                setState { copy(time = singleChoiceGroupUpdate(currentState.time, event.timeFilter)) }
                getRecipeCount()
            }
            is SingletonFilterContract.Event.OnCostFilterChanged -> {
                setState { copy(cost = singleChoiceGroupUpdate(currentState.cost, event.costFilter)) }
                getRecipeCount()
            }
            is SingletonFilterContract.Event.OnDifficultyChanged -> {
                setState {
                    copy(
                        difficulty = multipleChoiceGroupUpdate(
                            currentState.difficulty,
                            event.difficulty
                        )
                    )
                }
                getRecipeCount()
            }
        }
    }

    public fun getRecipeCount() {
        launch(coroutineHandler) {
            val count = recipeRepositoryImp.getRecipeNumberOfResult(getSelectedFilters())
            setState { copy(numberOfResult = count) }
        }
    }

    private fun singleChoiceGroupUpdate(
        group: List<CatalogFilterOptions>,
        option: CatalogFilterOptions
    ): List<CatalogFilterOptions> {
        return group.map { currentOption ->
            if (currentOption.name == option.name) currentOption.toogle() else currentOption.off()
        }
    }

    private fun multipleChoiceGroupUpdate(
        group: List<CatalogFilterOptions>,
        option: CatalogFilterOptions
    ): List<CatalogFilterOptions> {
        return group
            .map { currentOption ->
                if (currentOption.name == option.name) currentOption.toogle() else currentOption
            }
    }

    public fun setCat(catId: String) {
        setState { copy(category = catId) }
        getRecipeCount()
    }

    public fun setFavorite() {
        setState { copy(isFavorite = true) }
        getRecipeCount()
    }

    public fun setSearchString(searchString: String) {
        setState { copy(searchString = searchString) }
        getRecipeCount()
    }

    public fun applyFilter() {
        launch { sideEffect.emit(FilterEffect.OnUpdate) }
    }

    public fun clear() {
        setState { initialState }
        getRecipeCount()
    }

    public fun getSelectedFilters(): Map<String, String> {
        var filters: MutableMap<String, String> = mutableMapOf()
        val difficultyOptions = currentState.difficulty.filter { option -> option.isSelected }
        val costOption = currentState.cost.find { option -> option.isSelected }
        val timeOption = currentState.time.find { option -> option.isSelected }

        if (difficultyOptions.isNotEmpty()) {
            filters[RecipeFilter.DIFFICULTY.filterName] = difficultyOptions.joinToString("-") { it.name } + ",eq"
        }
        if (costOption != null) {
            val border = costOption.name.split('-')
            filters[RecipeFilter.COST.filterName] = "${border[0]},gt,${border[1]},lt"
        }
        if (timeOption != null) {
            filters[RecipeFilter.TOTAL_TIME.filterName]= timeOption.name
        }
        if (currentState.searchString != null) {
            filters[RecipeFilter.SEARCH.filterName]= currentState.searchString.toString()
        }
        if (currentState.category != null) {
            filters[RecipeFilter.PACKAGES.filterName] = currentState.category.toString()
        }
        if (currentState.isFavorite) {
            filters[RecipeFilter.LIKED.filterName] = "true"
            filters[RecipeFilter.ACTIVE.filterName] = "true,false"
        }

        return filters
    }

    public fun getActiveFilterCount(): Int {
        val temp = listOf(
            currentState.difficulty.filter { it.isSelected },
            currentState.cost.filter { it.isSelected },
            currentState.time.filter { it.isSelected }
        ).flatten()

        return temp.size
    }

    public companion object {
        public val initialState: SingletonFilterContract.State = SingletonFilterContract.State(
            difficulty = listOf(
                CatalogFilterOptions("1", Localisation.Recipe.lowDifficulty.localised),
                CatalogFilterOptions("2", Localisation.Recipe.mediumDifficulty.localised),
                CatalogFilterOptions("3", Localisation.Recipe.highDifficulty.localised),
            ),
            cost = listOf(
                CatalogFilterOptions("0-5", Localisation.Filters.lessThanFiveEuros.localised),
                CatalogFilterOptions("5-10", Localisation.Filters.betweenFiveAndTenEuros.localised),
                CatalogFilterOptions("10-1000", Localisation.Filters.moreThanTenEuros.localised),
            ),
            time = listOf(
                CatalogFilterOptions("15", Localisation.Filters.lessThanFifteenMinutes.localised),
                CatalogFilterOptions("30", Localisation.Filters.lessThanThirteenMinutes.localised),
                CatalogFilterOptions("60", Localisation.Filters.lessThanAnHour.localised),
                CatalogFilterOptions("120", Localisation.Filters.lessThanTwoHours.localised),
            ),
            searchString = null,
            isFavorite = false,
            category = null,
            numberOfResult = 0
        )
    }
}