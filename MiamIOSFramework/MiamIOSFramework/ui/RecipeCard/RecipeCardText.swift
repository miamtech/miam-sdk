//
//  RecipeCardText.swift
//  MiamIOSFramework
//
//  Created by Miam on 27/04/2022.
//

import Foundation

public class RecipeCardText {

    public static let sharedInstance = RecipeCardText()

    public var recipeFlag = MiamText.sharedInstance.recipeFlag

    private init() {}

}
