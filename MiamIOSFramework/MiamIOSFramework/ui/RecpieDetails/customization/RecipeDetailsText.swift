//
//  RecipeDetailsText.swift
//  MiamIOSFramework
//
//  Created by Miam on 01/06/2022.
//

import Foundation


public class RecipeDetailsText {
    
    public static let sharedInstance = RecipeDetailsText()
    
    public var alreadyInCart = MiamText.sharedInstance.alreadyInCart
    public var checkBasketPreview = MiamText.sharedInstance.checkBasketPreview
    public var difficultyEasy = MiamText.sharedInstance.difficultyEasy
    public var difficultyMid = MiamText.sharedInstance.difficultyMid
    public var difficultyHard = MiamText.sharedInstance.difficultyHard
    public var preparationTime = MiamText.sharedInstance.preparationTime
    public var cookingTime = MiamText.sharedInstance.cookingTime
    public var restingTime = MiamText.sharedInstance.restingTime
    
    private init() {}
    
}
