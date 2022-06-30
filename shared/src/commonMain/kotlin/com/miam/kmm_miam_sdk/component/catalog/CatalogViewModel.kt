package com.miam.kmm_miam_sdk.component.catalog

import com.miam.kmm_miam_sdk.base.mvi.BaseViewModel
import com.miam.kmm_miam_sdk.base.mvi.BasicUiState
import com.miam.kmm_miam_sdk.base.mvi.PointOfSaleStore
import com.miam.kmm_miam_sdk.component.catalogFilter.CatalogFilterContract
import com.miam.kmm_miam_sdk.component.catalogFilter.CatalogFilterViewModel
import com.miam.kmm_miam_sdk.component.recipeListPage.RecipeListPageContract
import com.miam.kmm_miam_sdk.component.recipeListPage.RecipeListPageViewModel
import com.miam.kmm_miam_sdk.miam_core.data.repository.PackageRepositoryImp
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.koin.core.component.inject

open class CatalogViewModel:
    BaseViewModel<CatalogContract.Event, CatalogContract.State, CatalogContract.Effect>() {

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
            searchString="",
            filterOpen= false,
            searchOpen= false
        )

    override fun handleEvent(event: CatalogContract.Event) {
        when (event) {
            is CatalogContract.Event.GoToDefault -> {
                setState {copy(
                    content = CatalogContent.DEFAULT,
                    searchString = "",
                    searchOpen = false,
                    catalogFilterVM = CatalogFilterViewModel()
                )}}
            is CatalogContract.Event.GoToFavorite -> {
                setState { copy(
                    content = CatalogContent.RECIPE_LIST,
                    searchOpen = false,
                ) }
                currentState.catalogFilterVM.setEvent(
                    CatalogFilterContract.Event.SetFavorite
                )
                currentState.recipePageVM.setEvent(
                    RecipeListPageContract.Event.InitPage(
                        "Mes Favorits",
                        currentState.catalogFilterVM.getSelectedFilterAsQueryString()
                    )
                )
            }
            is CatalogContract.Event.GoToRecipeList -> {
                if(currentState.searchString.isNotEmpty()){
                    currentState.catalogFilterVM.setEvent(
                        CatalogFilterContract.Event.SetSearchString(currentState.searchString)
                    )
                }
                setState {copy(content = CatalogContent.RECIPE_LIST, searchOpen = false)}
                currentState.recipePageVM.setEvent(
                    RecipeListPageContract.Event.InitPage(
                        if(currentState.searchString.isEmpty()) "Votre sélection" else "Votre recherche : \"${currentState.searchString}\"",
                        currentState.catalogFilterVM.getSelectedFilterAsQueryString()
                    )
                )
            }
            is CatalogContract.Event.GoToRecipeListFromCategory -> {
                currentState.catalogFilterVM.setEvent(
                    CatalogFilterContract.Event.SetCategoryTitle(event.category.attributes?.title ?: "")
                )
                setState { copy(
                    content = CatalogContent.RECIPE_LIST,
                    searchOpen = false,
                ) }
                currentState.recipePageVM.setEvent(
                    RecipeListPageContract.Event.InitPage(
                        "${event.category.attributes?.title}",
                        currentState.catalogFilterVM.getSelectedFilterAsQueryString()
                    )
                )
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
                setState {copy(content = CatalogContent.RECIPE_LIST, searchOpen = false, searchString = event.searchString)}
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
                // TODO on ne filtre pas sur les category == true ?
                // TODO le multi page n'est pas encore supporté
                setState {copy(categories = BasicUiState.Success(fetchedPackage))}
            }
        }
    }


}