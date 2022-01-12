//
//  RecipeCardVM.swift
//  iosApp
//
//  Created by Miam on 12/01/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import shared

class RecipeCardVM : RecipeCardViewModel, ObservableObject {
    @Published var recipe: Recipe = Recipe.companion.emptyRecipe()


    override init() {
        super.init()
        // TODO handle other states
        collect(flow: uiState, collect: { data in
            let state = data as! RecipeCardContractState
            print("" + state.recipeCard.debugDescription)
            switch state.recipeCard {
                case let success as BasicUiStateSuccess<Recipe>:
                    self.recipe = success.data!
                default:
                    break
                }
            }
        )

    }
}

