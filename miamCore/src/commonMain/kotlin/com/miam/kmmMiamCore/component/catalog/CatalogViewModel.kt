package com.miam.kmmMiamCore.component.catalog

import com.miam.kmmMiamCore.base.mvi.BaseViewModel
import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.PointOfSaleStore
import com.miam.kmmMiamCore.component.recipeListPage.RecipeListPageContract
import com.miam.kmmMiamCore.component.recipeListPage.RecipeListPageViewModel
import com.miam.kmmMiamCore.component.singletonFilter.SingletonFilterViewModel
import com.miam.kmmMiamCore.miam_core.data.repository.PackageRepositoryImp
import com.miam.kmmMiamCore.services.DialogRoute
import com.miam.kmmMiamCore.services.PageRoute
import com.miam.kmmMiamCore.services.RouteService
import com.miam.kmmMiamCore.services.RouteServiceAction
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.koin.core.component.inject

open class CatalogViewModel: BaseViewModel<CatalogContract.Event, CatalogContract.State, CatalogContract.Effect>() {

    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        println("Miam error in catalog view $exception")
    }

    private val packageRepositoryImp: PackageRepositoryImp by inject()
    private val pointOfSaleStore: PointOfSaleStore by inject()
    private val routeService: RouteService by inject()

    private val currentFiltersQuery: String
        get() = currentState.catalogFilterVM.getSelectedFilterAsQueryString()

    override fun createInitialState(): CatalogContract.State =
        CatalogContract.State(
            categories = BasicUiState.Loading,
            content = CatalogContent.DEFAULT,
            catalogFilterVM = SingletonFilterViewModel(),
            recipePageVM = RecipeListPageViewModel(),
            filterOpen = false,
            searchOpen = false,
            preferenceOpen = false,
            enableFilters = true,
            enablePreferences = false
        )

    init {
        routeService.dispatch(RouteServiceAction.SetRoute(PageRoute("Idées repas", ::goToCatalogMain)))
    }

    override fun handleEvent(event: CatalogContract.Event) {
        when (event) {
            is CatalogContract.Event.GoToDefault -> {
                routeService.dispatch(RouteServiceAction.SetRoute(PageRoute("Idées repas", ::goToCatalogMain)))
                goToCatalogMain()
            }
            is CatalogContract.Event.GoToFavorite -> {
                routeService.dispatch(RouteServiceAction.SetRoute(PageRoute("Mes repas favoris", ::goToFavorites)))
                goToFavorites()
            }
            is CatalogContract.Event.GoToRecipeList -> {
                routeService.dispatch(RouteServiceAction.SetRoute(PageRoute("Une envie de ?") { handleEvent(CatalogContract.Event.GoToDefault) }))
                setState {
                    copy(
                        content = CatalogContent.RECIPE_LIST,
                        searchOpen = false,
                        filterOpen = false,
                        preferenceOpen = false,
                    )
                }
                fetchRecipes()

            }
            is CatalogContract.Event.GoToRecipeListFromCategory -> {
                currentState.catalogFilterVM.setCat(event.categoryId)
                currentState.recipePageVM.setEvent(
                    RecipeListPageContract.Event.InitPage(
                        event.title,
                        currentState.catalogFilterVM.getSelectedFilterAsQueryString()
                    )
                )
                routeService.dispatch(RouteServiceAction.SetRoute(PageRoute("Une envie de ?") { handleEvent(CatalogContract.Event.GoToDefault) }))
                setState { copy(content = CatalogContent.RECIPE_LIST, searchOpen = false) }
            }
            is CatalogContract.Event.TogglePreference -> {
                routeService.dispatch(RouteServiceAction.SetRoute(DialogRoute("", {}, { setState { copy(preferenceOpen = false) } })))
                setState { copy(preferenceOpen = !currentState.preferenceOpen) }
            }
            is CatalogContract.Event.ToggleFilter -> {
                routeService.dispatch(RouteServiceAction.SetRoute(DialogRoute("", {}, { setState { copy(filterOpen = false) } })))
                setState { copy(filterOpen = !currentState.filterOpen) }
            }
            is CatalogContract.Event.ToggleSearch -> {
                routeService.dispatch(RouteServiceAction.SetRoute(DialogRoute("", {}, { setState { copy(searchOpen = false) } })))
                setState { copy(searchOpen = !currentState.searchOpen) }
            }
            is CatalogContract.Event.OnFilterValidation -> {
                routeService.dispatch(RouteServiceAction.SetRoute(PageRoute("Une envie de ?") { setState { copy(content = CatalogContent.RECIPE_LIST) } }))
                setState { copy(content = CatalogContent.RECIPE_LIST, filterOpen = false) }
            }
            is CatalogContract.Event.OnSearchLaunch -> {
                routeService.dispatch(RouteServiceAction.SetRoute(PageRoute("Une envie de ?") { setState { copy(content = CatalogContent.RECIPE_LIST) } }))
                setState { copy(content = CatalogContent.RECIPE_LIST, searchOpen = false) }
            }
            is CatalogContract.Event.OnCloseModal -> {
                setState { copy(searchOpen = false, filterOpen = false, preferenceOpen = false) }
            }
            is CatalogContract.Event.GoBack -> {
                routeService.previous()
            }
        }
    }

    init {
        fetchCategories()
    }

    private fun goToCatalogMain() {
        setState { copy(content = CatalogContent.DEFAULT, searchOpen = false, filterOpen = false, catalogFilterVM = SingletonFilterViewModel()) }
    }

    private fun goToFavorites() {
        currentState.catalogFilterVM.setFavorite()
        currentState.recipePageVM.setEvent(RecipeListPageContract.Event.InitPage("Mes idées repas", currentFiltersQuery))
        setState { copy(content = CatalogContent.RECIPE_LIST, searchOpen = false) }
    }

    fun enablePreferences(enable: Boolean = true) {
        setState { copy(enablePreferences = enable) }
    }

    fun enableFilters(enable: Boolean = true) {
        setState { copy(enableFilters = enable) }
    }

    private fun fetchRecipes() {
        currentState.recipePageVM.setEvent(
            RecipeListPageContract.Event.InitPage(
                if ((currentState.catalogFilterVM.currentState.searchString
                        ?: "").isEmpty()
                ) "Votre sélection" else "Votre recherche : \"${currentState.catalogFilterVM.currentState.searchString}\"",
                currentState.catalogFilterVM.getSelectedFilterAsQueryString()
            )
        )
    }

    private fun fetchCategories() {
        // TODO ALEX
        /**
         * fetch pakage
         * if empty set empty state to categories
         * if error set error state
         * else set new state at success with fetched data
         */
        val providerId = pointOfSaleStore.getProviderId()
        if (providerId != -1) {
            launch(coroutineHandler) {
                setState { copy(categories = BasicUiState.Loading) }
                val fetchedPackage =
                    packageRepositoryImp.getActivePackageForRetailer(providerId.toString())
                val newState =
                    if (fetchedPackage.isEmpty()) BasicUiState.Empty else BasicUiState.Success(
                        fetchedPackage
                    )
                // TODO le multi page n'est pas encore supporté
                setState { copy(categories = newState) }
            }.invokeOnCompletion { error ->
                if (error != null) {
                    setState { copy(categories = BasicUiState.Error("Could not fetch packages")) }
                }
            }
        }
    }


}