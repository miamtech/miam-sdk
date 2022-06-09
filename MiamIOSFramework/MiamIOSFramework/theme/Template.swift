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
        
}
