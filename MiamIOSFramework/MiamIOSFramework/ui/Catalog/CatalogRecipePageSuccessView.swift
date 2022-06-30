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

    init(viewModel: RecipeListPageVM) {
        recipesListPageModel = viewModel
        recipesListPageModel.loadPage()
    }

    var body: some View {
        ScrollView {
            LazyVStack(alignment: .leading) {
                Text(recipesListPageModel.title).font(Font.system(size: 20.0)).fontWeight(.heavy)
                ForEach(recipesListPageModel.recipes, id: \.self) { recipe in
                    RecipeCardView(recipeId: recipe.id)
                }
            }.padding([.leading, .trailing], Dimension.sharedInstance.mlPadding)
        }
    }
}
