package com.miam.kmm_miam_sdk.android.theme

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.TextFieldValue
import com.miam.kmmMiamCore.component.recipe.RecipeViewModel
import com.miam.kmmMiamCore.miam_core.model.BasketEntry
import com.miam.kmmMiamCore.miam_core.model.BasketPreviewLine
import com.miam.kmmMiamCore.miam_core.model.CatalogFilterOptions
import com.miam.kmmMiamCore.miam_core.model.CheckableTag
import com.miam.kmmMiamCore.miam_core.model.Package
import com.miam.kmmMiamCore.miam_core.model.Recipe
import com.miam.kmmMiamCore.miam_core.model.RecipeStep

object Template {

////////////////// Recipe Card //////////////////

    /**
     * recipe allow you to access all recipe object property as image, title ...
     * vmRecipe is a view controller share between recipeCard and recipeDetail, you'll find state of the recipe
     * such as if the recipe is loading or if it's already in basket , also function like increment or decrement guest count
     * call look function 'll open recipe detail without adding recipe to basket
     * call buy function 'll open basket preview and add recipe to basket if it not already in
     */
    var recipeCardTemplate: (
    @Composable() (
        recipe: Recipe,
        vmRecipe: RecipeViewModel,
        look: () -> Unit,
        buy: () -> Unit
    ) -> Unit
    )? = null

    /**
     * view when recipe is fetching
     */
    var recipeLoaderTemplate: (
    @Composable() () -> Unit
    )? = null

    /**
     * view when miam fail to fetch
     */
    var recipeEmptyTemplate: (
    @Composable() () -> Unit
    )? = null

/////////// Favorite Page //////////////////////////

    var loadingFavoritePage: (@Composable() () -> Unit)? = null

    var emptyFavoritePage: (
    @Composable() (
        visitCatalog: () -> Unit,
    ) -> Unit
    )? = null

////////////////// Recipe details //////////////////

    /**
     * sticky header
     */
    var recipeDetailHeaderTemplate: (
    @Composable() (
        closeDetail: () -> Unit,
        recipe: Recipe
    ) -> Unit
    )? = null

    var recipeDetailInfosTemplate: (
    @Composable() (
        closeDetail: () -> Unit,
        recipe: Recipe,
    ) -> Unit)? = null

    var recipeDetailIngredientTemplate: (@Composable() (
        recipe: Recipe,
        vmRecipe: RecipeViewModel
    ) -> Unit)? = null

    var recipeDetailStepsTemplate: (@Composable() (
        steps: List<RecipeStep>,
        vmRecipe: RecipeViewModel
    ) -> Unit)? = null

    var recipeDetailFooterTemplate: (@Composable() (
        recipe: Recipe,
        vmRecipe: RecipeViewModel,
        look: () -> Unit,
        buy: () -> Unit
    ) -> Unit)? = null

////////////////// Product Selector //////////////////

    var productSelectorHeaderTemplate: (@Composable() (
        back: () -> Unit
    ) -> Unit)? = null

    var currentProductTemplate: (@Composable() (
        selectedItem: BasketPreviewLine
    ) -> Unit)? = null

    var productOptionListTemplate: (@Composable() (
        options: List<BasketPreviewLine>,
        choose: (index: Int) -> Unit
    ) -> Unit)? = null

////////////////// Product Selector //////////////////

    var basketPreviewHeaderTemplate: (@Composable() (
        recipeVm: RecipeViewModel,
        goBackToRecipeDetails: () -> Unit,
        previous: () -> Unit
    ) -> Unit)? = null


    var basketPreviewLineFooterTemplate: (@Composable() (
        removeAddClose: () -> Unit,
        close: () -> Unit
    ) -> Unit)? = null

/////////////// Basket Preview //////////////////////

    var basketPreviewRecipeLineTemplate: (@Composable() (
        recipeName: String,
        picture: String,
        recipeDescription: String,
        price: String,
        pricePerGuest: String,
        guestCount: Int,
        goToRecipeDetail: () -> Unit,
        guestUpdate: (guestCount: Int) -> Unit
    ) -> Unit)? = null

    var basketPreviewLoadingTemplate: (@Composable() () -> Unit)? = null

    var basketPreviewProductLine: (@Composable() (
        productName: String,
        description: String,
        productPicture: String,
        quantity: Int,
        sharingCount: String, // ex : partager avec x recette
        price: String,
        itemsCount: Int,
        delete: () -> Unit,
        replace: () -> Unit,
        onQuantityChanged: (newQuantity: Int) -> Unit,
        // Compose bug, build crash with exactly 10 parameters
        composeBugParam: Int?
    ) -> Unit)? = null


    var basketPreviewNotFoundTemplate: (@Composable() (
        notFoundProducts: List<BasketEntry>,
    ) -> Unit)? = null

    var basketPreviewOftenDeletedTemplate: (@Composable() (
        oftenDeletedProducts: List<BasketEntry>,
        addProduct: (BasketEntry) -> Unit
    ) -> Unit)? = null

    var basketPreviewRemovedTemplate: (@Composable() (
        removedProducts: List<BasketEntry>,
        addProduct: (BasketEntry) -> Unit
    ) -> Unit)? = null

    var basketPreviewExpendHeaderTemplate: (@Composable() (
        isOpen: Boolean,
        title: String,
        toggle: () -> Unit,
    ) -> Unit)? = null

    var basketPreviewExpendRowTemplate: (@Composable() (
        add: (() -> Unit)?,
        productName: String
    ) -> Unit)? = null

/////////////// My Meal Page  //////////////////////

    var myMealLoaderTemplate: (@Composable() () -> Unit)? = null

    var myMealRecipeExpendableAction: (@Composable() (
        isExpended: Boolean,
        expend: () -> Unit,
        removeRecipe: () -> Unit,
    ) -> Unit)? = null

///////////////////  Tag  //////////////////////////

    var TagTemplate: (@Composable() (
        recipes: List<Recipe>,
        showRecipe: (recipe: Recipe) -> Unit
    ) -> Unit)? = null

//////////////// Catalog /////////////////////////


    var CatalogHeader: (@Composable() (
        openFilter: () -> Unit,
        openSearch: () -> Unit,
        openPreferences: () -> Unit,
        goToFavorite: () -> Unit,
        goBackTOCatalog: () -> Unit,
        getActiveFilterCount: () -> Int,
        isMainPage: Boolean,
        isFavorite: Boolean
    ) -> Unit)? = null

    var CatalogCategoryTemplate: (@Composable() (
        context: Context,
        category: Package,
        recipesID: List<String>,
        goToCategoryPage: (category: Package) -> Unit
    ) -> Unit)? = null

    // optional template that is floating, you can for exemple set a button to myMeal
    var CatalogFloatingElementTemplate: (@Composable() (
    ) -> Unit)? = null

    var CatalogFilterTemplate: (@Composable() (
        difficulties: List<CatalogFilterOptions>,
        costs: List<CatalogFilterOptions>,
        times: List<CatalogFilterOptions>,
        onCostFilterChanged: (option: CatalogFilterOptions) -> Unit,
        onTimeFilterChanged: (option: CatalogFilterOptions) -> Unit,
        onDifficultyChanged: (option: CatalogFilterOptions) -> Unit,
        clearFilter: () -> Unit,
        goToFilterResult: () -> Unit,
        closeDialog: () -> Unit,
    ) -> Unit)? = null

    var CatalogLoaderTemplate: (@Composable() () -> Unit)? = null

    var CatalogSearchTemplate: (@Composable() (
        currentSearchValue: String,
        updateResearch: (newSearchValue: String) -> Unit,
        closeDialog: () -> Unit,
        goToResultPage: () -> Unit
    ) -> Unit)? = null

    /**
     * full loading state
     */
    var CatalogResultPageLoadingTemplate: (@Composable() () -> Unit)? = null

    /**
     * lazy loading loader
     */
    var CatalogResultPageLazyLoaderTemplate: (@Composable() () -> Unit)? = null

    var CatalogPageTitleTemplate: (@Composable() (
        title: String
    ) -> Unit)? = null

    var CatalogFavoritEmptyTemplate: (@Composable() (
        returnToCatalog: () -> Unit
    ) -> Unit)? = null

    var CatalogSearchResultEmptyTemplate: (@Composable() (
        returnToCatalog: () -> Unit,
        pageTitle: String
    ) -> Unit)? = null


    //////////////// Preferences /////////////////////////

    var PreferencesLoadingTemplate: (@Composable() () -> Unit)? = null

    var PreferencesHeaderTemplate: (@Composable() (closePref: () -> Unit) -> Unit)? = null

    var PreferencesFooterTemplate: (@Composable() (
        closePref: () -> Unit, applyPref: () -> Unit, recipesFound: Int
    ) -> Unit)? = null

    var GuestPreferencesSectionTemplate: (@Composable() (guests: Int, guestChanged: (count: Int) -> Unit) -> Unit)? = null

    var DietPreferencesSectionTemplate: (@Composable() (
        dietsTag: List<CheckableTag>, togglePreference: (tagIdToToogle: String) -> Unit
    ) -> Unit)? = null

    var IngredientPreferencesSectionTemplate: (@Composable() (
        ingredientsTag: List<CheckableTag>, togglePreference: (tagIdToToogle: String) -> Unit, toggleSearch: () -> Unit
    ) -> Unit)? = null

    var EquipmentPreferencesSectionTemplate: (@Composable() (
        equipmentsTag: List<CheckableTag>, togglePreference: (tagIdToToogle: String) -> Unit
    ) -> Unit)? = null

    var SearchPreferencesTemplate: (@Composable() (
        back: () -> Unit, text: TextFieldValue, (value: TextFieldValue) -> Unit
    ) -> Unit)? = null

    var SearchResultRowPreferencesTemplate: (@Composable() (
        select: () -> Unit, name: String,
    ) -> Unit)? = null

    var SearchPreferencesEmptyTemplate: (@Composable() () -> Unit)? = null

    var SearchPreferencesLoadingTemplate: (@Composable() () -> Unit)? = null

////////////////// MY MEAL BUTTON ////////////

    var myMealButtonSuccessViewTemplate: (@Composable() (
        recipeCount: Int, onclick: () -> Unit
    ) -> Unit)? = null

    var myMealButtonEmptyViewTemplate: (@Composable() (
    ) -> Unit)? = null

///////////// LIKE BUTTON ////////////////////////

    var LikeButtonTemplate: (@Composable() (
        isLiked: Boolean, likeAction: () -> Unit
    ) -> Unit)? = null

}