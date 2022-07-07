//
//  BasketTagVM.swift
//  MiamIOSFramework
//
//  Created by Miam on 07/07/2022.
//

import Foundation
import  shared

@available(iOS 14, *)
public class BasketTagVM : BasketTagViewModel, ObservableObject {
    
    @Published var recipeList: NSArray?
    
    init(productExtId: String) {
         super.init(vmRouter: RouterOutletViewModel())
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
}
