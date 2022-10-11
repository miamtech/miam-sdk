import com.miam.kmmMiamCore.miam_core.model.CatalogFilterOptions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent


data class FilterState(
    val difficulty: List<CatalogFilterOptions>,
    val cost: List<CatalogFilterOptions>,
    val time: List<CatalogFilterOptions>,
    val searchString: String? = null,
    val isFavorite: Boolean = false,
    val category: String? = null
)

class Filter : KoinComponent {

    private val initialfilters: FilterState =
        FilterState(
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

    val currentState: FilterState
        get() = filterState.value


    protected fun setState(reduce: FilterState.() -> FilterState) {
        val newState = currentState.reduce()
        _filterState.value = newState
    }

    private val _filterState: MutableStateFlow<FilterState> = MutableStateFlow(initialfilters)
    val filterState = _filterState.asStateFlow()


    fun setCat(catId: String) {
        setState { copy(category = catId) }
    }

    fun setFavorite() {
        setState { copy(isFavorite = true) }
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