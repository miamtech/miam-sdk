//
//  RecipeCardVM.swift
//  MiamIOSFramework
//
//  Created by miam on 17/02/2022.
//

import miamCore

@available(iOS 14, *)
public class RecipeCardVM : RecipeViewModel, ObservableObject {
    @Published public var recipe: Recipe?

    public override init() {
        super.init(routerVM: routerVM)
        collect(flow: uiState, collect: { data in
            let state = data as? RecipeContractState
            self.state = state

            switch state?.recipeState {
            case let success as BasicUiStateSuccess<Recipe>:
                    self.recipe = success.data!
            default:
                    break
            }
        })
    }

}
