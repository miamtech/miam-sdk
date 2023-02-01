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

    private init() {}

    /**
     width: CGFloat
     height: CGFloat
     */
    public var asyncImageLoadingTemplate: ((CGFloat, CGFloat) -> AnyView )?

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
    public var recipeCardTemplate: ((Recipe?,
                                      Bool,
                                      Bool,
                                      Bool,
                                      @escaping () -> Void,
                                      @escaping () -> Void) -> AnyView)?

    public var recipeCardLoadingViewTemplate: (() -> AnyView)?

    /**
     count: Int - the current value of counter
     lightMode: Bool - Show or hide "persons" text
     updateGuestAction:  (Int) -> Voiddecrease: () -> Void
     increase: () -> Void
     */
    public var counterViewTemplate: ((Int,
                                       Bool,
                                       @escaping () -> Void,
                                       @escaping () -> Void
                                      ) -> AnyView)?

    /**
     Second parameter Int is de interger part of the price
     First parameter Int is decimal part of the price
     */
    public var priceViewTemplate: ((_: Double, _: Double ) -> AnyView)?

    /// ItemSelector Templates

    /**
     selectedItem: BasketPreviewLine
     */
    public var  currentProductTemplate: ( (
        _: BasketPreviewLine
    ) -> AnyView )?

    /**
     options: List<BasketPreviewLine>,
     choose:( oldItem :BasketPreviewLine, index :Int) -> Void
     */
    public var  productOptionListTemplate: ( (
        _: [BasketPreviewLine],
        (BasketPreviewLine, Int) -> Void
    ) -> AnyView )?

    public var itemSelectorLoadingViewTemplate: ( (
    ) -> AnyView )?

    /// RecipeDetail Templates

    /**
     showTitleInHeader: Bool
     recipeTitle: String
     */
    public var recipeDetailsTitleBarTemplate: ((
        Bool,
        String
    ) -> AnyView)?

    /**
     mediaURL: String?
     title: String
     difficulty: Int
     totalTime: String
     showTitleInHeader: Bool
     isLikeEnabled: Bool
     recipeId: String
     */
    public var recipeDetailsHeaderTemplate: ((
        String?,
        String,
        Int,
        String,
        Binding<Bool>,
        Bool,
        String
    ) -> AnyView)?

    /**
     _: Recipe
     */
    public var recipeDetailInfosTemplate: (
        (_: Recipe) -> AnyView
    )?

    /**
     ingredients: [Ingredient]
     recipeGuests: Int
     currentGuests: Int
     guestUpdating : bool
     updateGuest: (Int) -> Void
     */
    public  var recipeDetailsIngredientsViewTemplate: ((
        [Ingredient],
        Int,
        Int,
        Bool,
        @escaping (Int) -> Void
    ) -> AnyView )?

    /**
     steps : [RecipeStep],
     */
    public  var recipeDetailStepsViewTemplate: ((
        [RecipeStep]
    ) -> AnyView )?

    /**
     recipe: Recipe,
     guest: Int,
     isInCart: Bool,
     look : () -> Unit,
     buy : () -> Unit
     */
    public var recipeDetailFooterTemplate: ((
        _: Recipe,
        _: Int,
        _: Bool,
        _: @escaping () -> Void,
        _: @escaping () -> Void
    ) -> AnyView )?

    /**
     preparationTime: String
     cookingTime: String
     restingTime: String
     */
    public var recipeTimeViewTemplate: ((
        String,
        String,
        String
    ) -> AnyView)?

    public var basketPreviewLoadingViewTemplate: ((
    ) -> AnyView)?

    /**
     numberOf ProductAdded: Int
     */
    public var basketPreviewTitleTemplate: ((
        Int
    ) -> AnyView)?

    /**
     basketTitle: String
     basketPictureURL: URL?,
     basketDescription: String,
     pricePerGuest: String,
     numberOfGuests: Int,
     price: String,
     isReloading : Bool
     updateGuest: (Int) -> Void
     */
    public var basketPreviewHeaderTemplate: ((
        String,
        URL?,
        String,
        String,
        Int,
        String,
        Bool,
        @escaping (Int) -> Void
    ) -> AnyView)?

    /**
     removeFromBasketAction: () -> Void
     continueShoppingAction: () -> Void
     */
    public var basketPreviewFooterTemplate: ((
        @escaping () -> Void,
        @escaping () -> Void
    ) -> AnyView)?

    /**
     productName: String,
     productPictureURL: URL?,
     productBrandName: String,
     productDescription: String,
     productPrice: String,
     quantity: Int,
     itemsCount: Int,
     updatingBasketEntryId: String,
     removeProduct: () -> Unit,
     replaceProduct: () -> Unit
     onQuantityChanged: (Int) -> Void
     */
    public var basketPreviewRowTemplate: ((
        String,
        URL?,
        String,
        String,
        String,
        Int,
        Int,
        String?,
        @escaping () -> Void,
        @escaping () -> Void,
        @escaping (Int) -> Void
    ) -> AnyView)?

    /**
     foldableSectionTitle: String,
     foldedState: Bool
     */
    public var ingredientFoldableHeaderTemplate: ((
        String,
        Binding<Bool>
    ) -> AnyView)?

    /**
     ingredientName: String
     addToBasketAction: () -> Void
     */
    public var ingredientNotInBasketRowTemplate: ((
        String,
        (() -> Void)?
    ) -> AnyView)?

    /**
     loadingText: String
     */
    public var catalogLoadingViewTemplate: ((
        String
    ) -> AnyView)?

    /**
     close: () -> Void
     search: () -> Void
     */
    public var catalogSearchViewTemplate: ((
        @escaping () -> Void,
        @escaping () -> Void
    ) -> AnyView)?

    /**
     searchString: String
     browseCatalogAction: () -> Void
     showingFavorites
     */
    public var catalogRecipePageNoResultsViewTemplate: ((
        String,
        (() -> Void)?,
        Bool
    ) -> AnyView)?

    /**
     package: CatalogPackage
     showRecipes: (CatalogPackage) -> Void
     */
    public var catalogPackageRowTemplate: ((
        CatalogPackage,
        @escaping (CatalogPackage) -> Void
    ) -> AnyView)?

    /**
     closeCatalogAction: (() -> Void)?
     */
    public var catalogViewHeaderTemplate: ((
        (() -> Void)?
    ) -> AnyView)?

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
    ) -> AnyView)?

    public var catalogFiltersViewTemplate: AnyView?

    /**
     title: String
     filters: Array<CatalogFilterOptions>
     filterSelected: (CatalogFilterOptions) -> Void
     */
    public var catalogFiltersSectionTemplate: ((
        String,
        Array<CatalogFilterOptions>,
        @escaping (CatalogFilterOptions) -> Void
    ) -> AnyView)?

    /**
     filter: CatalogFilterOptions
     filterSelected: (CatalogFilterOptions) -> Void
     */
    public var catalogFilterRowTemplate: ((
        CatalogFilterOptions,
        @escaping (CatalogFilterOptions) -> Void
    ) -> AnyView)?

    /**
     favoriteRecipes: [Recipe]
     loadMoreContentAction: () -> Void
     */
    public var favoritesSuccessViewTemplate: ((
        [Recipe],
        @escaping () -> Void
    ) -> AnyView)?

    /**
     browseCatalogAction: () -> Void
     */
    public var favoritesEmptyViewTemplate: ((
        @escaping () -> Void
    ) -> AnyView)?

    /**
     parameter MyMealsActionColumnTemplateParameters
     */
    public var myMealsActionColumnTemplate: ((
        MyMealsActionColumnTemplateParameters
    ) -> AnyView)?
    /**
     
     */
    public var myMealsLoadingViewTemplate: ((
    ) -> AnyView)?

    /**
     
     */
    public var myMealsEmptyViewTemplate: ((
    ) -> AnyView)?

    /**
     isLiked: Bool
     likeButtonTapped: () -> Void
     */
    public var likeButtonTemplate: ((
        Bool,
        @escaping () -> Void
    ) -> AnyView)?

    /**
     recipes: [Recipe]
     tagTappedAction: () -> Void
     */
    public var tagViewTemplate: ((
        [Recipe],
        @escaping () -> Void
    ) -> AnyView)?

    /**
     showingListModal: Binding<Bool>
     recipeList : NSArray
     basketTagVm : BasketTagVM
     */
    public var basketTagListModal: ((
        Binding<Bool>,
        NSArray,
        BasketTagVM
    ) -> AnyView)?

    /**
     product : BasketPreviewLine
     isSelected : Bool
     */
    public var itemSelectorProductRowTemplate: ((
        BasketPreviewLine,
        Bool
    ) -> AnyView)?

    /**
     
     */
    public var myMealButtonEmptyViewTemplate: ((
    ) -> AnyView)?

    /**
     mealsCount: Int
     onButtonTapped: () -> Void
     */
    public var myMealButtonSuccessViewTemplate: ((
        Int,
        @escaping () -> Void
    ) -> AnyView)?

    /**
     
     */
    public var preferencesLoadingViewTemplate: ((
    ) -> AnyView)?

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
    ) -> AnyView)?

    /**
     tag: CheckableTag
     onToggleTag: (CheckableTag) -> Void
     */
    public var preferenceListItemViewTemplate: ((
        CheckableTag,
        @escaping (CheckableTag) -> Void
    ) -> AnyView)?

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
    ) -> AnyView)?

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
    ) -> AnyView)?

    /**
     onTapped: () -> Void
     */
    public var addTagViewTemplate: ((
        @escaping () -> Void
    ) -> AnyView)?

    /**
     tag: CheckableTag
     onToggleTag: (CheckableTag) -> Void
     */
    public var preferencesTagViewTemplate: ((
        CheckableTag,
        @escaping (CheckableTag) -> Void
    ) -> AnyView)?

    /**
     cancelTapped: () -> Void
     applyTapped: () -> Void
     numberOfRecipesFound: Int
     */
    public var preferencesFooterView: ((
        @escaping () -> Void,
        @escaping () -> Void,
        Int
    ) -> AnyView)?

    /**
     preferencesSearchViewModel: PreferencesSearchVM
     closeAction: () -> Void
     */
    public var preferencesSearchViewTemplate: ((
        PreferencesSearchVM,
        @escaping () -> Void
    ) -> AnyView)?

    /**
     showBackButton: Bool
     backAction: (() -> Void)?
     titleView: AnyView
     */
    @available(*, deprecated, message: "Not used anymore, views use native navigation bar.")
    public var titleBarViewTemplate: ((
        Bool,
        (() -> Void)?,
        AnyView
    ) -> AnyView)?

    // RecipesList

    /**
     CatalogPageTitleTemplateParameters
     */
    public var recipesListTitleTemplate: ((
        CatalogPageTitleTemplateParameters
    ) -> AnyView)?

    /**
     CatalogPageTitleTemplateParameters
     */
    public var recipesListCategoryTitleTemplate: ((
        CatalogPageTitleTemplateParameters
    ) -> AnyView)?

    /**
     CatalogPageTitleTemplateParameters
     */
    public var recipesListSearchTitleTemplate: ((
        CatalogPageTitleTemplateParameters
    ) -> AnyView)?

    /**
     recipeId: String
     close: () -> Void
     */
    public var recipeModalTemplate: ((
        String,
        RecipeCardVM,
        @escaping () -> Void
    ) -> AnyView)? = nil
    
    /**
     Error Template
     */
    public var errorTemplate: ((
    ) -> AnyView)? = nil
}
