//
//  RecipeCardVM.swift
//  MiamIOSFramework
//
//  Created by miam on 17/02/2022.
//

import miamCore

@available(iOS 14, *)
public class RecipeCardVM : RecipeViewModel, ObservableObject {
   
    @Published var recipe: Recipe?
    @Published var state: RecipeContractState?
    @Published var isInCart: Bool = false

    var sortedSteps: [RecipeStep] {
        guard let recipe = recipe else {
            return []
        }
        return recipe.sortedStep
    }

    override init(routerVM: RouterOutletViewModel) {
        super.init(routerVM:routerVM)
        collect(flow: uiState, collect: { data in
            let state = data as! RecipeContractState
            self.state = state
            self.isInCart = state.isInCart
            switch state.recipeState {
                case let success as BasicUiStateSuccess<Recipe>:
                    self.recipe = success.data!
                    self.objectWillChange.send()
                default:
                    break
                }
            }
        )
    }
    
    
    func toggleLike(){
        setEvent(event: RecipeContractEvent.OnToggleLike())
    }
    
    var isLikeEnabled: Bool {
        return state?.likeIsEnable ?? false
    }
}
