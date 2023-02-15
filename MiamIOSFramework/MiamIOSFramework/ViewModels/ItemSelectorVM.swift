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
    
    @Published public var state: ItemSelectorContractState?
    @Published public var selectedItem : BasketPreviewLine?
    
    public let sharedInstance = ItemSelectorInstance.shared.instance
    public let routeService = RouteServiceInstance.shared.instance
    
    public init() {
        
        sharedInstance.collect(flow: sharedInstance.uiState,
                                                     collect: { data in
            let state = data as! ItemSelectorContractState
            self.state = state
            switch state.selectedItem {
            case let success as BasicUiStateSuccess<BasketPreviewLine>:
                self.selectedItem = success.data
            default:
                break
            }
        })
        
    }
    
    public func returnToPreview() {
        sharedInstance.setEvent(event: ItemSelectorContractEvent.ReturnToBasketPreview())
    }
    
    public func chooseItem(selectedItem: BasketPreviewLine,index : Int) {
        sharedInstance.choose(selectedItem: selectedItem ,index: Int32(index))
        routeService.previous()
        returnToPreview()
    }
}
