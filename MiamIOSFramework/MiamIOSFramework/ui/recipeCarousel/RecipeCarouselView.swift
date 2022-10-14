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
public struct RecipeCarouselView: View {
    public let index: Int = 4
    
    let productId: String
    let numberOfResults: Int
    @ObservedObject var recipeCarouselVm: RecipeCarouselVM = RecipeCarouselVM()

    public init(productId: String, numberOfResults: Int) {
        self.productId = productId
        self.numberOfResults = numberOfResults
    }
    
    public var body: some View {
        if let carouselState = recipeCarouselVm.state {
            ManagementResourceState<NSArray, RecipesCarouselSuccessView, ProgressLoader, EmptyView>(
                resourceState: carouselState.suggestions,
                successView: RecipesCarouselSuccessView(recipes: recipeCarouselVm.suggestions),
                loadingView: ProgressLoader(color: Color.miamColor(.primary)),
                emptyView: EmptyView())
            .onAppear {
                recipeCarouselVm.setEvent(event: RecipeCarouselContractEvent.GetRecipeSuggestions(productId: productId,
                                                                                                  numberOfResult: KotlinInt(int: Int32(numberOfResults))))
            }
        }
    }
}

@available(iOS 14, *)
struct RecipeCarouselView_Previews: PreviewProvider {
    static var previews: some View {
        RecipeCarouselView(productId: "100", numberOfResults: 4)
    }
}
