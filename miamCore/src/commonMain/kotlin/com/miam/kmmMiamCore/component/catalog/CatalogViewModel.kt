package com.miam.kmmMiamCore.component.catalog

import com.miam.core.sdk.di.MiamDI
import com.miam.kmmMiamCore.base.mvi.BaseViewModel
import com.miam.kmmMiamCore.base.mvi.BasicUiState
import com.miam.kmmMiamCore.base.mvi.PointOfSaleStore
import com.miam.kmmMiamCore.component.preferences.PreferencesEffect
import com.miam.kmmMiamCore.component.preferences.SingletonPreferencesViewModel
import com.miam.kmmMiamCore.component.singletonFilter.FilterViewModelInstance
import com.miam.kmmMiamCore.handler.LogHandler
import com.miam.kmmMiamCore.helpers.letElse
import com.miam.kmmMiamCore.miam_core.data.datasource.RecipeFilter
import com.miam.kmmMiamCore.miam_core.data.repository.PackageRepositoryImp
import com.miam.kmmMiamCore.miam_core.data.repository.RecipeRepositoryImp
import com.miam.kmmMiamCore.miam_core.model.Package
import com.miam.kmmMiamCore.miam_core.model.RecipeRelationshipName
import com.miam.kmmMiamCore.services.RouteService
import com.miam.kmmMiamCore.services.RouteServiceAction
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

public open class CatalogViewModel: BaseViewModel<CatalogContract.Event, CatalogContract.State, CatalogContract.Effect>() {

    private val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        LogHandler.error("Miam error in catalog view $exception")
    }

    private val packageRepositoryImp: PackageRepositoryImp = MiamDI.packageRepository
    private val pointOfSaleStore: PointOfSaleStore = MiamDI.pointOfSaleStore
    private val routeService: RouteService = MiamDI.routeService
    private val recipeRepositoryImp: RecipeRepositoryImp = MiamDI.recipeRepository
    private val preference: SingletonPreferencesViewModel = MiamDI.preferencesViewModel
    private val filterVm = FilterViewModelInstance.instance


    override fun createInitialState(): CatalogContract.State =
        CatalogContract.State(
            categories = BasicUiState.Loading,
            content = CatalogContent.CATEGORIES_LIST,
            dialogIsOpen = false,
            dialogContent = DialogContent.PREFERENCES,
            enableFilters = true,
            enablePreferences = false,
            openedCategoryId = "",
            openedCategoryTitle = ""
        )

    init {
        pushInitialRoute()
        listenPreferencesChanges()
        if (preference.isInit) fetchCategories()
    }

    public fun pushInitialRoute() {
        LogHandler.info("Route push init")
        routeService.dispatch(RouteServiceAction.SetInitialPageRoute(CATEGORIES_LIST_TITLE, ::setCategoriesListState))
    }

    override fun handleEvent(event: CatalogContract.Event) {
        when (event) {
            is CatalogContract.Event.GoToFavorite -> {
                routeService.dispatch(RouteServiceAction.SetPageRoute(FAVORITE_LIST_TITLE, ::goToFavorites))
                goToFavorites()
            }
            is CatalogContract.Event.GoBack -> {
                routeService.previous()
            }
        }
    }

    private fun setSearchState(content: CatalogContent) {
        setState { copy(content = content) }
    }

    public fun onSimpleSearch(content: CatalogContent) {
        routeService.onCloseDialog()
        if (currentState.content == CatalogContent.CATEGORIES_LIST) {
            routeService.dispatch(RouteServiceAction.SetPageRoute(RECIPE_LIST_TITLE) { setSearchState(content) })
            setSearchState(content)
        }
    }

    public fun goToCategoriesList() {
        routeService.dispatch(RouteServiceAction.SetPageRoute(CATEGORIES_LIST_TITLE, ::setCategoriesListState))
        setCategoriesListState()
    }

    private fun setCategoriesListState() {
        setState { copy(content = CatalogContent.CATEGORIES_LIST, dialogIsOpen = false) }
        filterVm.clear()
    }

    public fun goToCategory(categoryId: String, categoryTitle: String, subtitle: String? = null) {
        routeService.dispatch(RouteServiceAction.SetPageRoute(RECIPE_LIST_TITLE) { setCategoryState(categoryId, categoryTitle, subtitle) })
        setCategoryState(categoryId, categoryTitle, subtitle)
    }

    private fun setCategoryState(categoryId: String, categoryTitle: String, subtitle: String?) {
        setState { copy(content = CatalogContent.CATEGORY, openedCategoryId = categoryId, openedCategoryTitle = categoryTitle, subtitle = subtitle) }
    }

    private fun goToFavorites() {
        filterVm.setFavorite()
        setState { copy(content = CatalogContent.FAVORITE, dialogIsOpen = false) }
    }

    public fun openPreferences() {
        routeService.dispatch(
            RouteServiceAction.SetDialogRoute(
                "",
                { preference.goToAllPref() },
                ::close
            )
        )
        setState { copy(dialogIsOpen = true, dialogContent = DialogContent.PREFERENCES) }
    }

    public fun openFilter() {
        routeService.dispatch(RouteServiceAction.SetDialogRoute("", {/* back not needed */ }, ::close))
        setState { copy(dialogIsOpen = true, dialogContent = DialogContent.FILTER) }
    }

    public fun openSearch() {
        routeService.dispatch(RouteServiceAction.SetDialogRoute("", {/* back not needed */ }, ::close))
        setState { copy(dialogIsOpen = true, dialogContent = DialogContent.SEARCH) }
    }

    public fun close() {
        setState { copy(dialogIsOpen = false) }
    }

    public fun enablePreferences(enable: Boolean = true) {
        setState { copy(enablePreferences = enable) }
    }

    public fun enableFilters(enable: Boolean = true) {
        setState { copy(enableFilters = enable) }
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
        return if (searchString.isNullOrEmpty()) FILTER_LIST_TITLE else SEARCH_LIST_TITLE + "\"$searchString\""
    }

    private fun fetchCategories() {
        letElse(
            pointOfSaleStore.supplierId,
            { supplierId ->
                launch(coroutineHandler) {
                    // when using preferences the cta will redirect to the list of recipes that are shown only if catalog is in sucess. do not put it into loading
                    if (currentState.content == CatalogContent.CATEGORIES_LIST) setState { copy(categories = BasicUiState.Loading) }
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

    private suspend fun getSupplierCategoriesWithRecipes(supplierId: String): List<Package> {
        // TODO le multi page n'est pas encore supporté
        val fetchedPackages = packageRepositoryImp.getActivePackageForRetailer(supplierId.toString())
        val fetchedPackagesWithRecipes = fetchPackagesRecipes(fetchedPackages).awaitAll()
        return fetchedPackagesWithRecipes.filter { p -> !p.relationships?.recipes?.data.isNullOrEmpty() }
    }

    private suspend fun fetchPackagesRecipes(fetchedPackages: List<Package>): List<Deferred<Package>> {
        return fetchedPackages.map { currentPackage ->
            async {
                var filters = filterVm.getSelectedFilters().plus(preference.getPreferences()).toMutableMap()
                filters[RecipeFilter.PACKAGES.filterName] = currentPackage.id
                val recipes = recipeRepositoryImp.getRecipes(
                    filters,
                    RecipeRelationshipName.relationshipsForRecipeCard(),
                    RecipeRepositoryImp.DEFAULT_PAGESIZE,
                    RecipeRepositoryImp.FIRST_PAGE
                )
                currentPackage.buildRecipes(recipes)
            }
        }
    }

    public companion object {
        public const val RECIPE_LIST_TITLE: String = "Une envie de ?"
        public const val CATEGORIES_LIST_TITLE: String = "Idées repas"
        public const val FAVORITE_LIST_TITLE: String = "Mes repas favoris"
        public const val FILTER_LIST_TITLE: String = "Votre sélection"
        public const val SEARCH_LIST_TITLE: String = "Votre recherche :"
    }
}