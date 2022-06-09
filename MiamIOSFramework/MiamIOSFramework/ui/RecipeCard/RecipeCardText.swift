//
//  RecipeCardText.swift
//  MiamIOSFramework
//
//  Created by Miam on 27/04/2022.
//

import Foundation

public class RecipeCardText {
    
    public static let sharedInstance = RecipeCardText()
    
    public var alreadyInCart = MiamText.sharedInstance.alreadyInCart
    public var recipeFlag = MiamText.sharedInstance.recipeFlag
    public var addRecipe = MiamText.sharedInstance.addRecipe
    
    private init() {}
    
}