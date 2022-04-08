//
//  RecipeCardVM.swift
//  MiamIOSFramework
//
//  Created by miam on 17/02/2022.
//

import shared

public class RecipeCardVM : RecipeViewModel, ObservableObject {
    @Published var recipe: Recipe?


    override init() {
        super.init()
        // TODO handle other states
        collect(flow: uiState, collect: { data in
            let state = data as! RecipeContractState
            print("" + state.recipeState.debugDescription)
            switch state.recipeState {
                case let success as BasicUiStateSuccess<Recipe>:
                    self.recipe = success.data!
                default:
                    break
                }
            }
        )

    }
}
