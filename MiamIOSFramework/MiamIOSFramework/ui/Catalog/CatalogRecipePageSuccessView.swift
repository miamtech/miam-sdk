//
//  CatalogRecipePageSuccessView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 24/06/2022.
//

import Foundation
import SwiftUI

internal struct CatalogRecipesPageSuccessView: View {
    let recipesListPageModel: RecipeListPageVM
    var body: some View {
        ScrollView {
            VStack {
                ForEach(recipesListPageModel.recipes, id: \.self) { recipe in
                    RecipeCardView(recipeId: recipe.id)
                }
            }
        }
    }
}
