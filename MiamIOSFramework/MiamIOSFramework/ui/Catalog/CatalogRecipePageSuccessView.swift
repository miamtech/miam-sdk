//
//  CatalogRecipePageSuccessView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 24/06/2022.
//

import Foundation
import SwiftUI

@available(iOS 14, *)
internal struct CatalogRecipesPageSuccessView: View {
    @ObservedObject var recipesListPageModel: RecipeListPageVM
    let catalogViewModel: CatalogVM
    init(viewModel: RecipeListPageVM, catalogViewModel: CatalogVM) {
        recipesListPageModel = viewModel
        self.catalogViewModel = catalogViewModel
    }

    var body: some View {
        if !recipesListPageModel.hasNoResults {
            VStack {
                Text(recipesListPageModel.title).font(Font.system(size: 20.0))
                    .fontWeight(.heavy).frame(height: 40.0)
                List(recipesListPageModel.recipes, id: \.self) { recipe in
                    RecipeCardView(recipeId: recipe.id, showMealIdeaTag: false).onAppear {
                        recipesListPageModel.loadMoreContent(currentRecipe: recipe)
                    }
                }
                .padding(0)
                .background(Color.white)
            }
        } else {
            CatalogRecipePageNoResultsView(catalogViewModel: catalogViewModel, showingFavorites: false)
        }
    }
}
