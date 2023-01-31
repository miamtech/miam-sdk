//
//  BasketTagVM.swift
//  MiamIOSFramework
//
//  Created by Miam on 07/07/2022.
//

import Foundation
import miamCore

@available(iOS 14, *)
public class BasketTagVM: BasketTagViewModel, ObservableObject {

    @Published var recipeList: NSArray?
    var vMRouter: RouterOutletViewModel

    init(productExtId: String) {
        self.vMRouter = RouterOutletViewModel()
        super.init(vmRouter: vMRouter)
        // TODO handle other states
        collect(flow: uiState, collect: { data in
            let state = data as! BasketTagContractState
            switch state.recipeList {
                case let success as BasicUiStateSuccess<NSArray>:
                    self.recipeList = success.data!
                default:
                    break
                }
            }
        )
        setEvent(event: BasketTagContractEvent.SetRetailerProductId(productId: productExtId))
    }

    public override  func goToDetail(recipe: Recipe) {
        let vmRecipe = RecipeCardVM(routerVM: RouterOutletViewModel())
        vmRecipe.setRecipe(recipe: recipe)
        vMRouter.goToDetail(vmRecipe: vmRecipe, showDetailsFooter: false)
    }

}
