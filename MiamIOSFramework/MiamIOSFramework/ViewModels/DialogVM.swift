//
//  DialogVM.swift
//  MiamIOSFramework
//
//  Created by Miam on 03/05/2022.
//

import Foundation
import shared
import Combine


public class DialogVM : ObservableObject {
    
    @Published var state: RouterOutletContractState?
    private let routerVm: RouterOutletViewModel

     init(routerVm : RouterOutletViewModel) {
         self.routerVm = routerVm
         
          routerVm.collect(flow: routerVm.uiState, collect: { data in
             self.state = data as! RouterOutletContractState
        })
        
    }
    
    public func getKotlinVm() -> RouterOutletViewModel{
        return self.routerVm
    }
}
