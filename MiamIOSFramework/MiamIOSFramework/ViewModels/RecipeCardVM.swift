//
//  RecipeCardVM.swift
//  MiamIOSFramework
//
//  Created by miam on 17/02/2022.
//

import shared

public class RecipeCardVM : RecipeViewModel, ObservableObject {
    @Published var recipe: Recipe?


    override init(routerVM: RouterOutletViewModel) {
        super.init(routerVM:routerVM)
        collect(flow: uiState, collect: { data in
            let state = data as! RecipeContractState
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
