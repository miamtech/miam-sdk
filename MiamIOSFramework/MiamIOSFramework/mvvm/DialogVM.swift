//
//  DialogVM.swift
//  MiamIOSFramework
//
//  Created by Miam on 03/05/2022.
//

import Foundation


import shared

public class DialogVM : RouterViewModel, ObservableObject {
    @Published var content: RouterContent?


    override init() {
        super.init()
        collect(flow: uiState, collect: { data in
            let _ = data as! RouterContractState
        })
        
    }
}
