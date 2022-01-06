//
//  CharacterDetailVM.swift
//  iosApp
//
//  Created by miam on  27/12/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import shared

class RecipeCardVM : RecipeCardViewModel, ObservableObject {
    @Published var recipe: Recipe = Recipe(id: 1, )


    override init() {
        super.init()

        collect(flow: uiState, collect: { data in
            let state = data as! RecipeCardContractState

            switch state.recipe {
                case let success as BasicUiStateSuccess<Recipe>:
                    self.recipe = success.data!
                default:
                    break
                }
            }
        )

    }
}

