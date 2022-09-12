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
    
    init(recipesListPageModel: RecipeListPageViewModel, browseCatalogAction: @escaping () -> Void, searchString: String) {
        self.recipesListPageModel = RecipeListPageVM(model: recipesListPageModel)
        self.browseCatalogAction = browseCatalogAction
        self.searchString = searchString
    }
    
    var body: some View {
        if let state = recipesListPageModel.state {
            ManagementResourceState<NSArray, CatalogRecipesPageSuccessView, CatalogLoadingView, CatalogEmptyView>(
                resourceState: state.recipes,
                successView: CatalogRecipesPageSuccessView(
                    title: recipesListPageModel.title,
                    recipes: recipesListPageModel.recipes,
                    hasNoResults: recipesListPageModel.hasNoResults,
                    searchString: "",
                    loadMoreContentAction: { recipe in
                        recipesListPageModel.loadMoreContent(currentRecipe: recipe)
                    },
                    browseCatalogAction: {
                        browseCatalogAction()
                    }),
                loadingView: CatalogLoadingView(loadingText: MiamText.sharedInstance.simmering),
                emptyView: CatalogEmptyView()).padding([.top], Dimension.sharedInstance.lPadding)
        }
    }
}
