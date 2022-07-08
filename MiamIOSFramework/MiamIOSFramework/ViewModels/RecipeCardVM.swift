//
//  RecipeCardVM.swift
//  MiamIOSFramework
//
//  Created by miam on 17/02/2022.
//

import shared

@available(iOS 14, *)
public class RecipeCardVM : RecipeViewModel, ObservableObject {
   
    @Published var recipe: Recipe?
    @Published var state: RecipeContractState?

    override init(routerVM: RouterOutletViewModel) {
        super.init(routerVM:routerVM)
        collect(flow: uiState, collect: { data in
            let state = data as! RecipeContractState
            self.state = state
            switch state.recipeState {
                case let success as BasicUiStateSuccess<Recipe>:
                    self.recipe = success.data!
                default:
                    break
                }
            }
        )
    }
    
    
    func toggleLike(){
        setEvent(event: RecipeContractEvent.OnToggleLike())
    }
    
    func likeIsEnable() -> Bool {
        return state?.likeIsEnable ?? false
    }
}
