//
//  Template.swift
//  MiamIOSFramework
//
//  Created by Miam on 02/05/2022.
//

import Foundation
import SwiftUI
import shared

public class Template {
    
    public static let sharedInstance = Template()
    
    private init(){}
    
    
    /**
        recipe view Model
         look : ( ) -> Void
         buy: () -> Void
     */
    public var recipeCardTemplate : ((_: RecipeCardVM,
                                      _ : @escaping () -> Void,
                                      _ : @escaping () -> Void)  -> AnyView)? = nil
    
    /**
     First parameter Int is the current value of counter
     Second parameter is decrese function
     Third parameter is increase function
     */
    public var counterTemplate : ((_ : Int,
                                   _ : @escaping () -> Void,
                                   _ : @escaping () -> Void)  -> AnyView)? = nil
    
    /**
     Second parameter Int is de interger part of the price
     First parameter Int is decimal part of the price
     */
    public var priceTemplate : ((_ : Int,_ : Int )  -> AnyView)? = nil
    
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
     closeDetail : () -> Unit
     recipe : Recipe
     
     */
    public var recipeDetailHeaderTemplate : ((
             _: @escaping  () -> Void,
            _: Recipe
                ) -> AnyView
        )? = nil
    
    /**
     _: Recipe
     */
    public var recipeDetailInfosTemplate : (
         (_: Recipe) -> AnyView
        )? = nil
        
    /**
     ingredients: [Ingredient],
     vmRecipe : RecipeViewModel
     guest: count,
     increase function
     decrese function
     */
    public  var recipeDetailIngredientsTemplate : ( (
            _: [Ingredient],
            _: RecipeViewModel,
            _ : Int,
            _ : @escaping () -> Void,
            _ : @escaping () -> Void
                ) -> AnyView )? = nil
    /**
     steps : [RecipeStep],
     vmRecipe : RecipeViewModel
     */
    public  var recipeDetailStepsTemplate :  ((
            _ : [RecipeStep],
            _ : RecipeViewModel
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
            _ : () -> Void,
            _ : () -> Void
                ) -> AnyView )? =  nil


    /**
     basketTitle: String
     basketPictureURL: URL?,
     basketDescription: String,
     pricePerGuest: String,
     numberOfGuests: Int,
     price: String,
     decreaseGuestsCount: () -> Unit,
     increaseGuestsCount: () -> Unit
     */
    public var basketPreviewHeaderTemplate: ((
        String,
        URL?,
        String,
        String,
        Int,
        String,
        () -> Void,
        () -> Void
    ) -> AnyView)? = nil

    /**
     removeFromBasketAction: () -> Void
     continueShoppingAction: () -> Void
     */
    public var basketPreviewFooterTemplate: ((
        () -> Void,
        () -> Void
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
        () -> Void,
        () -> Void
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
        () -> Void
    )-> AnyView)? = nil

    public var catalogFiltersViewTemplate: AnyView? = nil


    /**
     title: String
     filters: Array<CatalogFilterOptions>
     filterSelected: (CatalogFilterOptions) -> Void
     */
    public var catalogFiltersSectionTemplate: ((
        String,
        Array<CatalogFilterOptions>,
        (CatalogFilterOptions) -> Void
    ) -> AnyView)? = nil

    /**
     filter: CatalogFilterOptions
     filterSelected: (CatalogFilterOptions) -> Void
     */
    public var catalogFilterRowTemplate: ((
        CatalogFilterOptions,
        (CatalogFilterOptions) -> Void
    ) -> AnyView)? = nil
}
