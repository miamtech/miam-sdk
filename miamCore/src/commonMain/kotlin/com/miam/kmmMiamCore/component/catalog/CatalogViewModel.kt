package com.miam.kmmMiamCore.component.catalog

import com.miam.kmmMiamCore.base.mvi.BaseViewModel
import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.PointOfSaleStore
import com.miam.kmmMiamCore.component.preferences.PreferencesEffect
import com.miam.kmmMiamCore.component.preferences.SingletonPreferencesViewModel
import com.miam.kmmMiamCore.component.recipeListPage.RecipeListPageContract
import com.miam.kmmMiamCore.component.recipeListPage.RecipeListPageViewModel
import com.miam.kmmMiamCore.component.singletonFilter.SingletonFilterViewModel
import com.miam.kmmMiamCore.handler.LogHandler
import com.miam.kmmMiamCore.helpers.letElse
import com.miam.kmmMiamCore.miam_core.data.repository.PackageRepositoryImp
import com.miam.kmmMiamCore.miam_core.data.repository.RecipeRepositoryImp
import com.miam.kmmMiamCore.miam_core.model.Package
import com.miam.kmmMiamCore.services.RouteService
import com.miam.kmmMiamCore.services.RouteServiceAction
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import org.koin.core.component.inject

open class CatalogViewModel: BaseViewModel<CatalogContract.Event, CatalogContract.State, CatalogContract.Effect>() {

    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        LogHandler.error("Miam error in catalog view $exception")
    }

    private val packageRepositoryImp: PackageRepositoryImp by inject()
    private val pointOfSaleStore: PointOfSaleStore by inject()
    private val routeService: RouteService by inject()
    private val recipeRepositoryImp: RecipeRepositoryImp by inject()
    private val preference: SingletonPreferencesViewModel by inject()

    private val currentFiltersQuery: String
        get() = currentState.catalogFilterVM.getSelectedFilterAsQueryString()


    private val filterVm: SingletonFilterViewModel
        get() = currentState.catalogFilterVM

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
        routeService.dispatch(RouteServiceAction.SetPageRoute("Idées repas", ::goToCatalogMain))
    }

    override fun handleEvent(event: CatalogContract.Event) {
        when (event) {
            is CatalogContract.Event.GoToDefault -> {
                routeService.dispatch(RouteServiceAction.SetPageRoute("Idées repas", ::goToCatalogMain))
                goToCatalogMain()
            }
            is CatalogContract.Event.GoToFavorite -> {
                routeService.dispatch(RouteServiceAction.SetPageRoute("Mes repas favoris", ::goToFavorites))
                goToFavorites()
            }
            is CatalogContract.Event.GoToRecipeList -> {
                // PB ICI
                routeService.dispatch(RouteServiceAction.SetPageRoute("Une envie de ?") { setState { copy(content = CatalogContent.RECIPE_LIST) } })
                setState { copy(content = CatalogContent.RECIPE_LIST, searchOpen = false, filterOpen = false, preferenceOpen = false) }
                fetchRecipes()

            }
            is CatalogContract.Event.GoToRecipeListFromCategory -> {
                currentState.catalogFilterVM.setCat(event.categoryId)
                currentState.recipePageVM.setEvent(
                    RecipeListPageContract.Event.InitPage(event.title)
                )
                routeService.dispatch(RouteServiceAction.SetPageRoute("Une envie de ?") { setState { copy(content = CatalogContent.RECIPE_LIST) } })
                setState { copy(content = CatalogContent.RECIPE_LIST, searchOpen = false) }
            }
            is CatalogContract.Event.TogglePreference -> {
                routeService.dispatch(RouteServiceAction.SetDialogRoute("", {}, { setState { copy(preferenceOpen = false) } }))
                setState { copy(preferenceOpen = !currentState.preferenceOpen) }
            }
            is CatalogContract.Event.ToggleFilter -> {
                routeService.dispatch(RouteServiceAction.SetDialogRoute("", {}, { setState { copy(filterOpen = false) } }))
                setState { copy(filterOpen = !currentState.filterOpen) }
            }
            is CatalogContract.Event.ToggleSearch -> {
                routeService.dispatch(RouteServiceAction.SetDialogRoute("", {}, { setState { copy(searchOpen = false) } }))
                setState { copy(searchOpen = !currentState.searchOpen) }
            }
            is CatalogContract.Event.OnFilterValidation -> {
                routeService.dispatch(RouteServiceAction.SetPageRoute("Une envie de ?") { setState { copy(content = CatalogContent.RECIPE_LIST) } })
                setState { copy(content = CatalogContent.RECIPE_LIST, filterOpen = false) }
            }
            is CatalogContract.Event.OnSearchLaunch -> {
                routeService.dispatch(RouteServiceAction.SetPageRoute("Une envie de ?") { setState { copy(content = CatalogContent.RECIPE_LIST) } })
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
        listenPreferencesChanges()
        if (preference.isInit) fetchCategories()
    }

    private fun goToCatalogMain() {
        setState { copy(content = CatalogContent.DEFAULT, searchOpen = false, filterOpen = false, catalogFilterVM = SingletonFilterViewModel()) }
    }

    private fun goToFavorites() {
        currentState.catalogFilterVM.setFavorite()
        currentState.recipePageVM.setEvent(RecipeListPageContract.Event.InitPage("Mes idées repas"))
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
                ) "Votre sélection" else "Votre recherche : \"${currentState.catalogFilterVM.currentState.searchString}\""
            )
        )
    }

    private fun listenPreferencesChanges() {
        launch(coroutineHandler) {
            preference.observeSideEffect().filter { effect ->
                effect is PreferencesEffect.PreferencesLoaded || effect is PreferencesEffect.PreferencesChanged
            }.collect {
                fetchCategories()
            }
        }
    }

    private fun recipePageTitle(searchString: String?): String {
        return if (searchString.isNullOrEmpty()) "Votre sélection" else "Votre recherche : \"${searchString}\""
    }

    private fun fetchCategories() {
        letElse(
            pointOfSaleStore.supplierId,
            { supplierId ->
                launch(coroutineHandler) {
                    // when using preferences the cta will redirect to the list of recipes that are shown only if catalog is in sucess. do not put it into loading
                    if (currentState.content == CatalogContent.DEFAULT) setState { copy(categories = BasicUiState.Loading) }
                    val categories = getSupplierCategoriesWithRecipes(supplierId)
                    setState { copy(categories = BasicUiState.Success(categories)) }
                }.invokeOnCompletion { error ->
                    if (error != null) setState { copy(categories = BasicUiState.Error("Could not fetch packages")) }
                }
            },
            {
                LogHandler.error("CatalogViewModel.fetchCategories with null supplier")
                setState { copy(categories = BasicUiState.Error("Could not fetch packages")) }
            }
        )
    }

    private suspend fun getSupplierCategoriesWithRecipes(supplierId: Int): List<Package> {
        // TODO le multi page n'est pas encore supporté
        val fetchedPackages = packageRepositoryImp.getActivePackageForRetailer(supplierId.toString(), listOf())
        val fetchedPackagesWithRecipes = fetchPackagesRecipes(fetchedPackages).awaitAll()
        return fetchedPackagesWithRecipes.filter { p -> !p.relationships?.recipes?.data.isNullOrEmpty() }
    }

    private suspend fun fetchPackagesRecipes(fetchedPackages: List<Package>): List<Deferred<Package>> {
        return fetchedPackages.map { currentPackage ->
            async {
                val packageFilter = "&filter[packages]=${currentPackage.id}"
                val filter = "${filterVm.getSelectedFilterAsQueryString()}${preference.getPreferencesAsQueryString()}&$packageFilter"
                val recipes = recipeRepositoryImp.getRecipesFromStringFilter(
                    filter,
                    RecipeRepositoryImp.DEFAULT_INCLUDED,
                    RecipeRepositoryImp.DEFAULT_PAGESIZE,
                    RecipeRepositoryImp.FIRST_PAGE
                )
                currentPackage.buildRecipes(recipes)
            }
        }
    }
}