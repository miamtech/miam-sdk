//
//  CatalogRecipePageSuccessView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 24/06/2022.
//

import Foundation
import SwiftUI
import miamCore

@available(iOS 14, *)
internal struct CatalogRecipesPageSuccessView: View {
    let title: String
    let recipes: [Recipe]
    let hasNoResults: Bool
    let searchString: String
    let columns: Int 
    let spacing: CGFloat
    let loadMoreContentAction: (Recipe) -> Void
    let browseCatalogAction: () -> Void
      
    var body: some View {
        if !hasNoResults {
            ScrollView {
                VStack {
                        HStack {
                            Text(title)
                                .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleStyle)
                                .frame(height: 40.0)
                            Spacer()
                        }
                        LazyVGrid( columns:  Array(repeating:GridItem(.flexible()), count: columns), spacing: spacing){
                            ForEach(recipes, id: \.self) { recipe in
                                RecipeCardView(recipeId: recipe.id, showMealIdeaTag: false).onAppear {
                                    loadMoreContentAction(recipe)
                                    //
                                }.padding(.top,Dimension.sharedInstance.lPadding)
                            }
                        }
                    }
            }.padding(.horizontal, Dimension.sharedInstance.mlPadding)
        } else {
            CatalogRecipePageNoResultsView(searchString: searchString, browseCatalogAction: {
                browseCatalogAction()
            }, showingFavorites: false)
        }
    }
}
