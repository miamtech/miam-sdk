package com.miam.kmmMiamCore.component.catalogFilter

import com.miam.kmmMiamCore.base.mvi.BaseViewModel
import com.miam.kmmMiamCore.miam_core.data.repository.RecipeRepositoryImp
import com.miam.kmmMiamCore.miam_core.model.CatalogFilterOptions
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object CatalogFilterInstance : KoinComponent {
    val instance: CatalogFilterViewModel by inject()
}

open class CatalogFilterViewModel : BaseViewModel<CatalogFilterContract.Event, CatalogFilterContract.State, CatalogFilterContract.Effect>() {

    private val recipeRepositoryImp: RecipeRepositoryImp by inject()


    override fun createInitialState(): CatalogFilterContract.State = CatalogFilterContract.State(
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
        category = null,
        numberOfResult = 0
    )

    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        println("Miam error in catalog view $exception")
    }


    override fun handleEvent(event: CatalogFilterContract.Event) {
        when (event) {
            is CatalogFilterContract.Event.OnTimeFilterChanged -> {
                setState { copy(time = singleChoiceGroupUpdate(currentState.time, event.timeFilter)) }
                setEffect { CatalogFilterContract.Effect.OnUpdate }
                getRecipeCount()
            }
            is CatalogFilterContract.Event.OnCostFilterChanged -> {
                setState { copy(cost = singleChoiceGroupUpdate(currentState.cost, event.costFilter)) }
                setEffect { CatalogFilterContract.Effect.OnUpdate }
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
                setEffect { CatalogFilterContract.Effect.OnUpdate }
                getRecipeCount()
            }
        }
    }

    fun getRecipeCount() {
        launch(coroutineHandler) {
            val count = recipeRepositoryImp.getRecipeNumberOfResult(getSelectedFilterAsQueryString())
            setState { copy(numberOfResult = count) }
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

    fun setCat(catId: String) {
        setState { copy(category = catId) }
        setEffect { CatalogFilterContract.Effect.OnUpdate }
    }

    fun setFavorite() {
        setState { copy(isFavorite = true) }
        setEffect { CatalogFilterContract.Effect.OnUpdate }
    }

    fun setSearchString(searchString: String) {
        setState { copy(searchString = searchString) }
        setEffect { CatalogFilterContract.Effect.OnUpdate }
    }

    fun clear() {
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
                searchString = null,
                isFavorite = false,
                category = null,
                numberOfResult = 0
            )
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

    fun getActiveFilterCount(): Int {
        val temp = listOf(
            currentState.difficulty.filter { it.isSelected },
            currentState.cost.filter { it.isSelected },
            currentState.time.filter { it.isSelected }
        ).flatten()

        return temp.size
    }
}