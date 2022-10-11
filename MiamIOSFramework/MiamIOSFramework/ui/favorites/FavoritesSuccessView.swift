//
//  FavoritesSuccessView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 23/09/2022.
//

import SwiftUI
import miamCore

@available(iOS 14, *)
struct FavoritesSuccessView: View {
    let favorites: [Recipe]
    let loadMoreRecipes: () -> Void
    var body: some View {
        if let template = Template.sharedInstance.favoritesSuccessViewTemplate {
            template(favorites, loadMoreRecipes)
        } else {
            ScrollView(showsIndicators: true) {
                LazyVStack {
                    ForEach(favorites, id: \.self) { favoriteRecipe in
                        RecipeCardView(recipeId: favoriteRecipe.id)
                    }.padding(.top, Dimension.sharedInstance.lPadding)
                }
            }.padding(.horizontal, Dimension.sharedInstance.mlPadding)
        }
    }
}
