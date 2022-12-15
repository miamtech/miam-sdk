//
//  RecipesList.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 09/09/2022.
//

import Foundation
import SwiftUI
import miamCore

@available(iOS 14, *)
internal struct RecipesView: View {
    @ObservedObject private var recipesListPageModel: RecipeListPageVM
    let browseCatalogAction: () -> Void
    let searchString: String
    let showingFavorites: Bool
    let columns:Int
    let spacing: CGFloat
    let recipeCardHeight: CGFloat
    
    init(recipesListPageModel: RecipeListPageViewModel,recipesListColumns: Int, recipeListSpacing: CGFloat, recipeCardHeight: CGFloat, browseCatalogAction: @escaping () -> Void, searchString: String, showingFavorites: Bool) {
        self.recipesListPageModel = RecipeListPageVM(model: recipesListPageModel)
        self.browseCatalogAction = browseCatalogAction
        self.searchString = searchString
        self.showingFavorites = showingFavorites
        self.columns = recipesListColumns
        self.spacing = recipeListSpacing
        self.recipeCardHeight = recipeCardHeight
    }
    
    var body: some View {
        if let state = recipesListPageModel.state {
            ManagementResourceState<NSArray, CatalogRecipesPageSuccessView, CatalogLoadingView, CatalogRecipePageNoResultsView>(
                resourceState: state.recipes,
                successView: CatalogRecipesPageSuccessView(
                    title: recipesListPageModel.title,
                    recipes: recipesListPageModel.recipes,
                    hasNoResults: recipesListPageModel.hasNoResults,
                    searchString: searchString,
                    columns:columns,
                    spacing:spacing,
                    recipeCardHeight: recipeCardHeight,
                    loadMoreContentAction: { recipe in
                        recipesListPageModel.loadMoreContent(currentRecipe: recipe)
                    },
                    browseCatalogAction: {
                        browseCatalogAction()
                    }),
                loadingView: CatalogLoadingView(loadingText: MiamText.sharedInstance.simmering),
                emptyView: CatalogRecipePageNoResultsView(searchString: searchString, browseCatalogAction: browseCatalogAction, showingFavorites: showingFavorites))
        }
    }
}
