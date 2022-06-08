//
//  ItemSelectorVM.swift
//  MiamIOSFramework
//
//  Created by Miam on 08/06/2022.
//

import Foundation

import shared

public class ItemSelectorVM : ItemSelectorViewModel, ObservableObject {

    override init() {
        super.init()
        collect(flow: uiState, collect: { data in
            let _ = data as! ItemSelectorContractState
        })
        
    }
}
