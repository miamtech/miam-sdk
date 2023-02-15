//
//  RecipesCarouselVM.swift
//  MiamIOSFramework
//
//  Created by Miam on 11/10/2022.
//  Copyright © 2022 Miam. All rights reserved.
//

import Foundation
import miamCore

@available(iOS 14, *)
public class RecipeCarouselVM: RecipeCarouselViewModel ,ObservableObject {
    @Published public var suggestions: [Recipe] = []
    @Published public var state: RecipeCarouselContractState?
    
    public override init() {
        super.init()
        self.collect(flow: uiState) { data in
            let state = data as! RecipeCarouselContractState
            self.state = state
            switch(state.suggestions) {
            case let success as BasicUiStateSuccess<NSArray>:
                if let suggestions = success.data as? [Recipe] {
                    self.suggestions = suggestions
                }
            default:
                ()
            }
        }
    }
}
