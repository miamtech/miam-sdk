//
//  ItemSelectorVM.swift
//  MiamIOSFramework
//
//  Created by Miam on 08/06/2022.
//

import Foundation

import miamCore

@available(iOS 14, *)
public class ItemSelectorVM : ObservableObject {
    
    @Published var state: ItemSelectorContractState?
    
    public let sharedInstance = ItemSelectorInstance.shared.instance
    
    init() {
        
        sharedInstance.collect(flow: sharedInstance.uiState,
                                                     collect: { data in
            self.state = data as? ItemSelectorContractState
        })
        
    }
    
    public func returnToPreview(){
        sharedInstance.setEvent(event: ItemSelectorContractEvent.ReturnToBasketPreview())
    }
    
    public func chooseItem(index : Int) {
        sharedInstance.choose(index: Int32(index))
        returnToPreview()
    }
}
