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
    let showingFavorites: Bool
    let columns:Int
    let spacing: CGFloat
    let recipeCardHeight: CGFloat
    
    init(recipesListColumns: Int, recipeListSpacing: CGFloat, recipeCardHeight: CGFloat, browseCatalogAction: @escaping () -> Void,  showingFavorites: Bool) {
        self.browseCatalogAction = browseCatalogAction
        self.showingFavorites = showingFavorites
        self.columns = recipesListColumns
        self.spacing = recipeListSpacing
        self.recipeCardHeight = recipeCardHeight
        self.recipesListPageModel = RecipeListPageVM()
    }
    
    init(categoryId:String, categoryTitle :String, recipesListColumns: Int, recipeListSpacing: CGFloat, recipeCardHeight: CGFloat, browseCatalogAction: @escaping () -> Void,  showingFavorites: Bool) {
        self.browseCatalogAction = browseCatalogAction
        self.showingFavorites = showingFavorites
        self.columns = recipesListColumns
        self.spacing = recipeListSpacing
        self.recipeCardHeight = recipeCardHeight
        self.recipesListPageModel = RecipeListPageVM(categoriesId: categoryId, title:categoryTitle)
    }
    
    var body: some View {
        if let state = recipesListPageModel.state {
            ManagementResourceState<NSArray, CatalogRecipesPageSuccessView, CatalogLoadingView, CatalogRecipePageNoResultsView>(
                resourceState: state.recipes,
                successView: CatalogRecipesPageSuccessView(
                    title: recipesListPageModel.title,
                    recipes: recipesListPageModel.recipes,
                    hasNoResults: recipesListPageModel.hasNoResults,
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
                emptyView: CatalogRecipePageNoResultsView( browseCatalogAction: browseCatalogAction, showingFavorites: showingFavorites))
        }
    }
}
