//
//  RecipesCarouselView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 05/10/2022.
//  Copyright © 2022 Miam. All rights reserved.
//

import SwiftUI
import miamCore

@available(iOS 14, *)
struct RecipesCarouselSuccessView: View {
    let title: String
    let showAllButtonTitle: String
    let recipes: [Recipe]
    var body: some View {
        LazyHStack {
            ForEach(recipes, id: \.self) { recipe in
                RecipeCardView(recipeId: recipe.id, showMealIdeaTag: false).frame(width: 300).padding(Dimension.sharedInstance.mlPadding)
            }
        }
    }
}

@available(iOS 14, *)
struct RecipesCarouselView_Previews: PreviewProvider {
    static var previews: some View {
        RecipesCarouselSuccessView(title: "Idées de repas", showAllButtonTitle: "Voir tout", recipes: [])
    }
}
