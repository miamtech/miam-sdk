//
//  ItemSelectorVM.swift
//  MiamIOSFramework
//
//  Created by Miam on 08/06/2022.
//

import Foundation

import shared

public class ItemSelectorVM : ItemSelectorViewModel, ObservableObject {
    
    public static let sharedInstance = ItemSelectorVM()
    
    private override init() {
        super.init()
        collect(flow: uiState, collect: { data in
            let _ = data as! ItemSelectorContractState
        })
        
    }
    
    public func returnToPreview(){
        setEvent(event: ItemSelectorContractEvent.ReturnToBasketPreview())
    }
    
    public func chooseItem(index : Int) {
        choose(index: Int32(index))
        returnToPreview()
    }
}
