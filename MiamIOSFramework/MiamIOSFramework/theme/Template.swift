//
//  Template.swift
//  MiamIOSFramework
//
//  Created by Miam on 02/05/2022.
//

import Foundation
import SwiftUI
import miamCore

@available(iOS 14, *)
public class Template {
    
    public static let sharedInstance = Template()
    
    private init(){}
    
    /**
     width: CGFloat
     height: CGFloat
     */
    public var asyncImageLoadingTemplate :  ((CGFloat, CGFloat) -> AnyView )? =  nil
    
    /**
    recipe: Recipe?
    isRecipeInCart: Bool
    isLikeEnabled: Bool
    isLiked: Bool
    showMealIdeaTag: Bool
    goToDetailsAction: () -> Void
    showOrAddRecipeAction: () -> Void
    toggleLikeAction: () -> Void
     */
    public var recipeCardTemplate : ((Recipe?,
                                      Bool,
                                      Bool,
                                      Bool,
                                      @escaping () -> Void,
                                      @escaping () -> Void)  -> AnyView)? = nil
    
    public var recipeCardLoadingViewTemplate: (()->AnyView)? = nil
    
    /**
     count: Int - the current value of counter
     lightMode: Bool - Show or hide "persons" text
     updateGuestAction:  (Int) -> Voiddecrease: () -> Void
     increase: () -> Void
     */
    public var counterViewTemplate : ((Int,
                                       Bool,
                                       @escaping () -> Void,
                                       @escaping () -> Void
                                      )  -> AnyView)? = nil
    
    /**
     Second parameter Int is de interger part of the price
     First parameter Int is decimal part of the price
     */
    public var priceViewTemplate : ((_ : Double,_ : Double )  -> AnyView)? = nil
    
    /// ItemSelector Templates
    
    /**
     selectedItem: BasketPreviewLine
     */
    public var  currentProductTemplate :  ( (
        _: BasketPreviewLine
    ) -> AnyView )? =  nil
    
    /**
     options: List<BasketPreviewLine>,
     choose:(index :Int) -> Void
     */
    public var  productOptionListTemplate :  ( (
        _: [BasketPreviewLine],
        _: @escaping (_ : Int) -> Void
    ) -> AnyView )? =  nil
    
    
    /// RecipeDetail Templates

    
    /**
     mediaURL: String?
     title: String
     difficulty: Int
     totalTime: String
     showTitleInHeader: Bool
     isLikeEnabled: Bool
     recipeId: String
     */
    public var recipeDetailsHeaderTemplate : ((
            String?,
            String,
            Int,
            String,
            Binding<Bool>,
            Bool,
            String
    ) -> AnyView)? = nil
    
    /**
     _: Recipe
     */
    public var recipeDetailInfosTemplate : (
         (_: Recipe) -> AnyView
        )? = nil
        
    /**
     ingredients: [Ingredient]
     recipeGuests: Int
     currentGuests: Int
     updateGuest: (Int) -> Void
     */
    public  var recipeDetailsIngredientsViewTemplate : ((
        [Ingredient],
        Int,
        Int,
        @escaping (Int) -> Void
    ) -> AnyView )? = nil
    
    
    /**
     steps : [RecipeStep],
     */
    public  var recipeDetailStepsViewTemplate :  ((
        [RecipeStep]
    ) -> AnyView )? =  nil
    
    /**
     recipe: Recipe,
     vmRecipe : RecipeViewModel,
     look : () -> Unit,
     buy : () -> Unit
     */
    public var recipeDetailFooterTemplate : ((
           // _: Recipe,
            _ : RecipeViewModel,
            _ : @escaping () -> Void,
            _ : @escaping () -> Void
                ) -> AnyView )? =  nil


    /**
     basketTitle: String
     basketPictureURL: URL?,
     basketDescription: String,
     pricePerGuest: String,
     numberOfGuests: Int,
     price: String,
     updateGuest: (Int) -> Void
     */
    public var basketPreviewHeaderTemplate: ((
        String,
        URL?,
        String,
        String,
        Int,
        String,
        @escaping (Int) -> Void
    ) -> AnyView)? = nil

    /**
     removeFromBasketAction: () -> Void
     continueShoppingAction: () -> Void
     */
    public var basketPreviewFooterTemplate: ((
        @escaping () -> Void,
        @escaping () -> Void
    ) -> AnyView)? = nil

    /**
     productName: String,
     productPictureURL: URL?,
     productBrandName: String,
     productDescription: String,
     productPrice: String,
     removeProduct: () -> Unit,
     replaceProduct: () -> Unit
     */
    public var basketPreviewRowTemplate: ((
        String,
        URL?,
        String,
        String,
        String,
        @escaping () -> Void,
        @escaping () -> Void
    ) -> AnyView)? = nil

    /**
     foldableSectionTitle: String,
     foldedState: Bool
     */
    public var ingredientFoldableHeaderTemplate: ((
        String,
        Binding<Bool>
    ) -> AnyView)? = nil

    /**
     ingredientName: String
     addToBasketAction: () -> Void
     */
    public var ingredientNotInBasketRowTemplate: ((
        String,
        (() -> Void)?
    )-> AnyView)? = nil


    /**
     loadingText: String
     */
    public var catalogLoadingViewTemplate: ((
        String
    ) -> AnyView)? = nil
    
    /**
     recipeListPageViewModel: RecipeListPageViewModel?
     packages: [CatalogPackage]
     catalogContent: CatalogModelContent
     Binding var showingPackageRecipes: Bool
     Binding var showingFavorites: Bool
     Binding var headerHeight: Double
     searchString: String
     browseCatalogAction: () -> Void
     navigateToRecipeAction: (Package) -> Void
     */
    public var catalogSuccessViewTemplate: ((
        RecipeListPageViewModel?,
        [CatalogPackage],
        CatalogModelContent,
        Binding<Bool>,
        Binding<Bool>,
        Binding<Double>,
        String,
        @escaping () -> Void,
        @escaping (Package) -> Void
    ) -> AnyView)? = nil
    
    /**
     catalog: CatalogVM
     close: () -> Void
     search: () -> Void
     */
    public var catalogSearchViewTemplate: ((
        CatalogVM,
        @escaping () -> Void,
        @escaping () -> Void
    ) -> AnyView)? = nil
   
    /**
     searchString: String
     browseCatalogAction: () -> Void
     showingFavorites
     */
    public var catalogRecipePageNoResultsViewTemplate: ((
        String,
        @escaping () -> Void,
        Bool
    ) -> AnyView)? = nil
    
    /**
     package: CatalogPackage
     showRecipes: (CatalogPackage) -> Void
     */
    public var catalogPackageRowTemplate: ((
        CatalogPackage,
        @escaping (CatalogPackage) -> Void
    ) -> AnyView)? = nil

    /**
     closeCatalogAction: (() -> Void)?
     */
    public var catalogViewHeaderTemplate: ((
        (() -> Void)?
    ) -> AnyView)? = nil

    /**
     showBackButton: Bool
     favoritesFilterActive: Bool
     backTapped: () -> Void
     filtersTapped: () -> Void
     searchTapped: () -> Void
     favoritesTapped: () -> Void
     preferenceTapped: () -> Void
     */
    public var catalogViewToolbarTemplate: ((
        Bool,
        Bool,
        @escaping () -> Void,
        @escaping () -> Void,
        @escaping () -> Void,
        @escaping () -> Void,
        @escaping () -> Void
    ) -> AnyView)? = nil



    public var catalogFiltersViewTemplate: AnyView? = nil


    /**
     title: String
     filters: Array<CatalogFilterOptions>
     filterSelected: (CatalogFilterOptions) -> Void
     */
    public var catalogFiltersSectionTemplate: ((
        String,
        Array<CatalogFilterOptions>,
        @escaping (CatalogFilterOptions) -> Void
    ) -> AnyView)? = nil

    /**
     filter: CatalogFilterOptions
     filterSelected: (CatalogFilterOptions) -> Void
     */
    public var catalogFilterRowTemplate: ((
        CatalogFilterOptions,
        @escaping (CatalogFilterOptions) -> Void
    ) -> AnyView)? = nil
    
    
    /**
     favoriteRecipes: [Recipe]
     loadMoreContentAction: () -> Void
     */
    public var favoritesSuccessViewTemplate: ((
        [Recipe],
        @escaping () -> Void
    ) -> AnyView)? = nil
    
    /**
     browseCatalogAction: () -> Void
     */
    public var favoritesEmptyViewTemplate: ((
       @escaping () -> Void
    ) -> AnyView)? = nil
    
    /**
     
     */
    public var myMealsLoadingViewTemplate: ((
    ) -> AnyView)? = nil
    
    /**
     
     */
    public var myMealsEmptyViewTemplate: ((
    ) -> AnyView)? = nil
    
    /**
     isLiked: Bool
     likeButtonTapped: () -> Void
     */
    public var likeButtonTemplate: ((
        Bool,
        @escaping () -> Void
    ) -> AnyView)? = nil
   
    /**
     recipes: [Recipe]
     tagTappedAction: () -> Void
     */
    public var tagViewTemplate: ((
        [Recipe],
        @escaping () -> Void
    ) -> AnyView)? = nil
    
    /**
     showingListModal: Binding<Bool>
     showingPopup: Binding<Bool>
     recipeList : NSArray
     basketTagVm : BasketTagVM
     */
    public var basketTagListModal: ((
        Binding<Bool>,
        Binding<Bool>,
        NSArray,
        BasketTagVM
    ) -> AnyView)? = nil
   
    /**
     product : BasketPreviewLine
     isSelected : Bool
     */
    public var itemSelectorProductRowTemplate: ((
        BasketPreviewLine,
        Bool
    ) -> AnyView)? = nil
    
    /**
     
     */
    public var myMealButtonEmptyViewTemplate: ((
    ) -> AnyView)? = nil
    
    /**
     mealsCount: Int
     onButtonTapped: () -> Void
     */
    public var myMealButtonSuccessViewTemplate: ((
        Int,
        @escaping () -> Void
    ) -> AnyView)? = nil
    
    /**
     
     */
    public var preferencesLoadingViewTemplate: ((
    ) -> AnyView)? = nil
   
    /**
     numberOfPersons: Int
     ingredients: [CheckableTag]
     equipments: [CheckableTag]
     diets: [CheckableTag]
     numberOfRecipesFound: Int
     onNumberOfGuestsChanged: (Int) -> Void
     onToggleTag: (CheckableTag) -> Void
     onAddTagTapped: () -> Void
     closeTapped: () -> Void
     applyTapped: () -> Void
     */
    public var preferencesSuccessViewTemplate: ((
        Int,
        [CheckableTag],
        [CheckableTag],
        [CheckableTag],
        Int,
        @escaping (Int) -> Void,
        @escaping (CheckableTag) -> Void,
        @escaping () -> Void,
        @escaping () -> Void,
        @escaping () -> Void
    ) -> AnyView)? = nil
    
    /**
     tag: CheckableTag
     onToggleTag: (CheckableTag) -> Void
     */
    public var preferenceListItemViewTemplate: ((
        CheckableTag,
        @escaping (CheckableTag) -> Void
    ) -> AnyView)? = nil
    
    /**
     title: String
     subtitle: String
     preferences: [CheckableTag]
     onToggleTag: (CheckableTag) -> Void
     */
    public var preferencesListViewTemplate: ((
        String,
        String,
        [CheckableTag],
        @escaping (CheckableTag) -> Void
    ) -> AnyView)? = nil
   
    /**
     title: String
     subtitle: String
     tags: [CheckableTag]
     geometry: GeometryProxy
     onToggleTag: (CheckableTag) -> Void
     onAddTagTapped: () -> Void
     */
    public var preferencesTagsListViewTemplate: ((
        String,
        String,
        [CheckableTag],
        GeometryProxy,
        @escaping (CheckableTag) -> Void,
        @escaping () -> Void
    ) -> AnyView)? = nil
    
   
    /**
     onTapped: () -> Void
     */
    public var addTagViewTemplate: ((
        @escaping () -> Void
    ) -> AnyView)? = nil
    
    /**
     tag: CheckableTag
     onToggleTag: (CheckableTag) -> Void
     */
    public var preferencesTagViewTemplate: ((
        CheckableTag,
        @escaping (CheckableTag) -> Void
    ) -> AnyView)? = nil
    
    
    /**
     cancelTapped: () -> Void
     applyTapped: () -> Void
     numberOfRecipesFound: Int
     */
    public var preferencesFooterView: ((
        @escaping () -> Void,
        @escaping () -> Void,
        Int
    ) -> AnyView)? = nil
    
    /**
     preferencesSearchViewModel: PreferencesSearchVM
     closeAction: () -> Void
     */
    public var preferencesSearchViewTemplate: ((
        PreferencesSearchVM,
        @escaping () -> Void
    ) -> AnyView)? = nil
}
