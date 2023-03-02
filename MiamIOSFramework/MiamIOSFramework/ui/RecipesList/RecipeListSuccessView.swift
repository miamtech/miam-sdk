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
internal struct RecipesListSuccessView: View {
    let title: String
    let recipes: [Recipe]
    let hasNoResults: Bool
    let columns: Int 
    let spacing: CGFloat
    let recipeCardHeight: CGFloat
    let titleTemplate: ((CatalogPageTitleTemplateParameters) -> AnyView)?
    let loadMoreContentAction: (Recipe) -> Void
    let browseCatalogAction: () -> Void
    let subtitle: String?
    
    var body: some View {
        if !hasNoResults {
            ScrollView {
                VStack {
                    if let template = titleTemplate {
                        template(CatalogPageTitleTemplateParameters(title: title, subtitle: subtitle))
                    } else {
                        HStack {
                            Text(title)
                                .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleStyle)
                                .frame(height: 40.0)
                            Spacer()
                        }
                    }
                    LazyVGrid( columns: Array(repeating:GridItem(.flexible()), count: columns), spacing: spacing) {
                        ForEach(recipes, id: \.self) { recipe in
                            RecipeCardView(recipe: recipe, showMealIdeaTag: false, recipeCardHeight: recipeCardHeight).onAppear {
                                loadMoreContentAction(recipe)
                                //
                            }.padding(.top,Dimension.sharedInstance.lPadding)
                        }
                    }
                }
            }.padding(.horizontal, Dimension.sharedInstance.mlPadding)
        } else {
            CatalogRecipePageNoResultsView(browseCatalogAction: {
                browseCatalogAction()
            }, showingFavorites: false)
        }
    }
}
