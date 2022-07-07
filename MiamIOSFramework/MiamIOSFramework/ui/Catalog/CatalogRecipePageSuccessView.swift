//
//  CatalogRecipePageSuccessView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 24/06/2022.
//

import Foundation
import SwiftUI

internal struct CatalogRecipesPageSuccessView: View {
    @ObservedObject var recipesListPageModel: RecipeListPageVM
    let catalogViewModel: CatalogVM
    init(viewModel: RecipeListPageVM, catalogViewModel: CatalogVM) {
        recipesListPageModel = viewModel
        self.catalogViewModel = catalogViewModel
    }

    var body: some View {
        if !recipesListPageModel.hasNoResults {
            Text(recipesListPageModel.title).font(Font.system(size: 20.0)).fontWeight(.heavy)
            List(recipesListPageModel.recipes, id: \.self) { recipe in
                RecipeCardView(recipeId: recipe.id).onAppear {
                    recipesListPageModel.loadMoreContent(currentRecipe: recipe)
                }
            }
            .background(Color.white)//.padding([.leading, .trailing, .top], Dimension.sharedInstance.mlPadding)
        } else {
            CatalogRecipePageNoResultsView(catalogViewModel: catalogViewModel, showingFavorites: false)
        }
    }
}
