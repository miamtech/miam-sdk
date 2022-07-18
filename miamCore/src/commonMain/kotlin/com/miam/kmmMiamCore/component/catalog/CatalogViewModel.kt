package com.miam.kmmMiamCore.component.catalog

import com.miam.kmmMiamCore.base.mvi.BaseViewModel
import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.PointOfSaleStore
import com.miam.kmmMiamCore.component.catalogFilter.CatalogFilterViewModel
import com.miam.kmmMiamCore.component.recipeListPage.RecipeListPageContract
import com.miam.kmmMiamCore.component.recipeListPage.RecipeListPageViewModel
import com.miam.kmmMiamCore.miam_core.data.repository.PackageRepositoryImp
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.koin.core.component.inject

open class CatalogViewModel:
    com.miam.kmmMiamCore.base.mvi.BaseViewModel<CatalogContract.Event, CatalogContract.State, CatalogContract.Effect>() {

    private val coroutineHandler = CoroutineExceptionHandler {
            _, exception ->
        println("Miam error in catalog view $exception")
    }

    private val packageRepositoryImp: PackageRepositoryImp by inject()
    private val pointOfSaleStore: PointOfSaleStore by inject()

    override fun createInitialState(): CatalogContract.State =
        CatalogContract.State(
            categories= BasicUiState.Loading,
            content= CatalogContent.DEFAULT,
            catalogFilterVM = CatalogFilterViewModel(),
            recipePageVM = RecipeListPageViewModel(),
            filterOpen= false,
            searchOpen= false
        )

    override fun handleEvent(event: CatalogContract.Event) {
        when (event) {
            is CatalogContract.Event.GoToDefault -> {
                setState {copy(
                    content = CatalogContent.DEFAULT,
                    searchOpen = false,
                    filterOpen= false,
                    catalogFilterVM = CatalogFilterViewModel()
                )}}
            is CatalogContract.Event.GoToFavorite -> {

                currentState.catalogFilterVM.setFavorite()
                currentState.recipePageVM.setEvent(
                    RecipeListPageContract.Event.InitPage(
                        "Mes idées repas",
                        currentState.catalogFilterVM.getSelectedFilterAsQueryString()
                    )
                )
                setState { copy(
                    content = CatalogContent.RECIPE_LIST,
                    searchOpen = false,
                ) }
            }
            is CatalogContract.Event.GoToRecipeList -> {
                setState {copy(content = CatalogContent.RECIPE_LIST, searchOpen = false, filterOpen= false,)}
                currentState.recipePageVM.setEvent(
                    RecipeListPageContract.Event.InitPage(
                        if((currentState.catalogFilterVM.currentState.searchString ?: "").isEmpty()) "Votre sélection" else "Votre recherche : \"${currentState.catalogFilterVM.currentState.searchString}\"",
                        currentState.catalogFilterVM.getSelectedFilterAsQueryString()
                    )
                )
            }
            is CatalogContract.Event.GoToRecipeListFromCategory -> {
                currentState.catalogFilterVM.setCat(event.category.id)
                currentState.recipePageVM.setEvent(
                    RecipeListPageContract.Event.InitPage(
                        "${event.category.attributes?.title}",
                        currentState.catalogFilterVM.getSelectedFilterAsQueryString()
                    )
                )
                setState { copy(
                    content = CatalogContent.RECIPE_LIST,
                    searchOpen = false,
                ) }
            }
            is CatalogContract.Event.ToggleFilter -> {
                setState {copy(filterOpen = !currentState.filterOpen )}
            }
            is CatalogContract.Event.ToggleSearch -> {
                setState {copy(searchOpen = !currentState.searchOpen )}
            }
            is CatalogContract.Event.OnFilterValidation -> {
                setState {copy(content = CatalogContent.RECIPE_LIST, filterOpen = false)}
            }
            is CatalogContract.Event.OnSearchLaunch -> {
                setState {copy(content = CatalogContent.RECIPE_LIST, searchOpen = false,)}
            }
        }
    }

    init {
       fetchCategories()
    }

    private fun fetchCategories(){
        // TODO ALEX
        /**
         * fetch pakage
         * if empty set empty state to categories
         * if error set error state
         * else set new state at success with fetched data
         */
        val providerId = pointOfSaleStore.getProviderId()
        if(providerId != -1) {
            launch(coroutineHandler) {
                val fetchedPackage = packageRepositoryImp.getActivePackageForRetailer(providerId.toString())
                // TODO le multi page n'est pas encore supporté
                setState {copy(categories = BasicUiState.Success(fetchedPackage))}
            }
        }
    }


}