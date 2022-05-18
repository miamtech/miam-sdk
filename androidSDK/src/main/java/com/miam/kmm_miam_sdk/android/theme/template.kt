package com.miam.kmm_miam_sdk.android.theme

import androidx.compose.runtime.Composable
import com.miam.kmm_miam_sdk.base.mvi.BasicUiState
import com.miam.kmm_miam_sdk.component.recipe.RecipeViewModel
import com.miam.kmm_miam_sdk.miam_core.model.BasketPreviewLine
import com.miam.kmm_miam_sdk.miam_core.model.Item
import com.miam.kmm_miam_sdk.miam_core.model.Recipe
import com.miam.kmm_miam_sdk.miam_core.model.RecipeStep

object Template {

////////////////// Recipe Card //////////////////

    /**
     * recipe allow you to access all recipe object property as image, title ...
     * vmRecipe is a view controller share between recipeCard and recipeDetail, you'll find state of the recipe
     * such as if the recipe is loading or if it's already in basket , also function like increment or decrement guest count
     * call look function 'll open recipe detail without adding recipe to basket
     * call buy function 'll open basket preview and add recipe to basket if it not already in
     */
    var recipeCardTemplate : (
    @Composable() (
        recipe: Recipe,
        vmRecipe: RecipeViewModel,
        look : () -> Unit,
        buy : () -> Unit
        ) -> Unit
    )? = null

    /**
     * view when recipe is fetching
     */
    var recipeLoaderTemplate : (
    @Composable() () -> Unit
    ) ? = null

    /**
     * view when miam fail to fetch
     */
    var recipeEmptyTemplate: (
      @Composable() () -> Unit
    ) ? = null

/////////// Favorite Page //////////////////////////

    var loadingFavoritePage : (@Composable() () -> Unit)? = null

    var emptyFavoritePage : (
    @Composable() (
        visitCatalog : () -> Unit,
    ) -> Unit
    )? = null    

////////////////// Recipe details //////////////////

    /**
     * sticky header
     */
    var recipeDetailHeaderTemplate : (
    @Composable() (
        closeDetail : () -> Unit,
        recipe: Recipe
            ) -> Unit
    ) ? = null

    var recipeDetailInfosTemplate : (
    @Composable() (recipe : Recipe) -> Unit
    ) ? = null

    var recipeDetailStepsTemplate : (@Composable() (
        recipe: Recipe,
        vmRecipe : RecipeViewModel
            ) -> Unit )? = null

    var recipeDetailIngredientTemplate :  (@Composable() (
        steps : List<RecipeStep>,
        vmRecipe : RecipeViewModel
            ) -> Unit )? =  null

    var recipeDetailFooterTemplate : (@Composable() (
        recipe: Recipe,
        vmRecipe : RecipeViewModel,
        look : () -> Unit,
        buy : () -> Unit
            ) -> Unit )? =  null

////////////////// Product Selector //////////////////

    var  currentProductTemplate :  (@Composable() (
            selectedItem: BasketPreviewLine
            ) -> Unit )? =  null

    var  productOptionListTemplate :  (@Composable() (
             options: List<BasketPreviewLine>,
             choose:(index :Int) -> Unit
            ) -> Unit )? =  null

////////////////// Product Selector //////////////////

    var basketPreviewHeaderTemplate  : (@Composable() (
        recipeVm : RecipeViewModel,
        goBackToRecipeDetails: ()-> Unit
            ) -> Unit)? = null


    var basketPreviewLineFooterTemplate :  (@Composable() (
        removeAddClose :  ()-> Unit,
        close : ()-> Unit
    ) -> Unit)? = null

/////////////// Basket Preview //////////////////////

    var basketPreviewLineTemplate  :  (@Composable() (
        recipeName: String,
        recipeDescription: String,
        pricePerGuest: String,
        guestCount:  Int,
        goToRecipeDetail : () -> Unit,
        increaseGuest: () -> Unit,
        decreaseGuest: () ->  Unit
    ) -> Unit)? = null

    var basketPreviewLoadingTemplate :  (@Composable() () -> Unit)? = null

    var basketPreviewProductLine : (@Composable() (
        productName: String,
        description: String,
        quantity: Int,
        sharingCount: String, // ex : partager avec x recette
        delete : () -> Unit,
        replace: () -> Unit,
        increaseQty: () -> Unit,
        decreaseQty: () ->  Unit
            ) -> Unit)? = null

    var basketPreviewExpendHeaderTemplate :  (@Composable() (
        isOpen: Boolean,
        toggle : () -> Unit,
    ) -> Unit)? = null

    var basketPreviewExpendRowTemplate :  (@Composable() (
        productName: String,
        add : () -> Unit,
    ) -> Unit)? = null
                                                                       

}