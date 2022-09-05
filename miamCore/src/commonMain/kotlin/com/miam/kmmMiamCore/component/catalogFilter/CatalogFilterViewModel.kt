package com.miam.kmmMiamCore.component.catalogFilter

import com.miam.kmmMiamCore.miam_core.data.repository.RecipeRepositoryImp
import com.miam.kmmMiamCore.miam_core.model.CatalogFilterOptions
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.koin.core.component.inject

open class CatalogFilterViewModel :
    com.miam.kmmMiamCore.base.mvi.BaseViewModel<CatalogFilterContract.Event, CatalogFilterContract.State, CatalogFilterContract.Effect>() {

    private val recipeRepositoryImp: RecipeRepositoryImp by inject()

    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        println("Miam error in catalog view $exception")
    }

    init {
        getRecipeCount()
    }

    override fun createInitialState(): CatalogFilterContract.State =
        CatalogFilterContract.State(
            numberOfResult = 0,
            difficulty = listOf(
                CatalogFilterOptions("1", "Chef débutant"),
                CatalogFilterOptions("2", "Chef intermédiaire"),
                CatalogFilterOptions("3", "Top chef"),
            ),
            cost = listOf(
                CatalogFilterOptions("0-5", "Moins de 5€"),
                CatalogFilterOptions("5-10", "Entre 5€ et 10€"),
                CatalogFilterOptions("10-1000", "Plus de 10€"),
            ),
            time = listOf(
                CatalogFilterOptions("15", "Moins de 15 min"),
                CatalogFilterOptions("30", "Moins de 30 min"),
                CatalogFilterOptions("60", "Moins de 1 h"),
                CatalogFilterOptions("120", "Moins de 2 h"),
            ),
            searchString = null,
            isFavorite = false,
            category = null
        )

    override fun handleEvent(event: CatalogFilterContract.Event) {
        when (event) {
            is CatalogFilterContract.Event.SetSearchString -> {
                setState {
                    copy(
                        searchString = event.searchString
                    )
                }
            }
            is CatalogFilterContract.Event.OnTimeFilterChanged -> {
                setState {
                    copy(
                        time = singleChoiceGroupUpdate(currentState.time, event.timeFilter)
                    )
                }
                getRecipeCount()
            }
            is CatalogFilterContract.Event.OnCostFilterChanged -> {
                setState {
                    copy(
                        cost = singleChoiceGroupUpdate(currentState.cost, event.costFilter)
                    )
                }
                getRecipeCount()
            }
            is CatalogFilterContract.Event.OnDifficultyChanged -> {
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

    fun getRecipeCount() {
        launch(coroutineHandler) {
            val count =
                recipeRepositoryImp.getRecipeNumberOfResult(getSelectedFilterAsQueryString())
            setState { copy(numberOfResult = count) }
        }
    }

    fun setCat(catId: String) {
        setState {
            copy(
                category = catId
            )
        }
    }


    fun setFavorite() {
        setState {
            copy(
                isFavorite = true
            )
        }
    }

    private fun singleChoiceGroupUpdate(
        group: List<CatalogFilterOptions>,
        option: CatalogFilterOptions
    ): List<CatalogFilterOptions> {
        return group.map { currentOption ->
            if (currentOption.name == option.name) currentOption.on() else currentOption.off()
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

    fun getSelectedFilterAsQueryString(): String {
        var filter = ""
        val difficultyOptions = currentState.difficulty.filter { option -> option.isSelected }
        val costOption = currentState.cost.find { option -> option.isSelected }
        val timeOption = currentState.time.find { option -> option.isSelected }

        if (difficultyOptions.isNotEmpty()) {
            filter += "filter[difficulty]="
            filter += difficultyOptions.joinToString("-") { it.name }
            filter += ",eq&"
        }
        if (costOption != null) {
            filter += "filter[computed_cost]="
            val border = costOption.name.split('-')
            filter += "${border[0]},gt,${border[1]},lt&"
        }
        if (timeOption != null) {
            filter += "filter[total-time]="
            filter += (timeOption.name + "&")
        }
        if (currentState.searchString != null) {
            filter += "filter[search]=${currentState.searchString}&"
        }
        if (currentState.category != null) {
            filter += "filter[packages]=${currentState.category}&"
        }
        if (currentState.isFavorite) {
            filter += "filter[liked]=true&"
        }
        return filter
    }

    fun clearFilter() {
        setState {
            copy(
                difficulty = listOf(
                    CatalogFilterOptions("1", "Chef débutant"),
                    CatalogFilterOptions("2", "Chef intermédiaire"),
                    CatalogFilterOptions("3", "Top chef"),
                ),
                cost = listOf(
                    CatalogFilterOptions("0-5", "Moins de 5€"),
                    CatalogFilterOptions("5-10", "Entre 5€ et 10€"),
                    CatalogFilterOptions("10-1000", "Plus de 10€"),
                ),
                time = listOf(
                    CatalogFilterOptions("15", "Moins de 15 min"),
                    CatalogFilterOptions("30", "Moins de 30 min"),
                    CatalogFilterOptions("60", "Moins de 1 h"),
                    CatalogFilterOptions("120", "Moins de 2 h"),
                ),
            )
        }
    }

    fun getActiveFilterCount(): Int {
        val temp = listOf(
            currentState.difficulty.filter { it.isSelected },
            currentState.cost.filter { it.isSelected },
            currentState.time.filter { it.isSelected }
        ).flatten()

        return temp.size
    }
}