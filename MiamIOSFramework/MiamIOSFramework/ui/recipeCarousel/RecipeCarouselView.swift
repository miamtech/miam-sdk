//
//  RecipeCarouselView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 05/10/2022.
//  Copyright © 2022 Miam. All rights reserved.
//

import SwiftUI
import miamCore

@available(iOS 14, *)
struct RecipeCarouselView: View {
    let productId: String
    let index: Int = 4
    
    var recipeCarouselVm: RecipeCarouselVM = RecipeCarouselVM()

    var body: some View {
        ManagementResourceState<RecipeCarouselContractState, RecipesCarouselSuccessView, ProgressLoader, EmptyView>(
            resourceState: recipeCarouselVm.state!,
                                  successView: RecipesCarouselSuccessView(title: "Idées de repas", showAllButtonTitle: "Voir tout", recipes: []),
                                  loadingView: ProgressLoader(color: Color.miamColor(.primary)),
                                  emptyView: EmptyView())
    }
}

@available(iOS 14, *)
struct RecipeCarouselView_Previews: PreviewProvider {
    static var previews: some View {
        RecipeCarouselView(productId: "100")
    }
}
