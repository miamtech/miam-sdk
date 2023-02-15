//
//  CatalogFilterVM.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 01/07/2022.
//

import Foundation
import miamCore

@available(iOS 14, *)
public class CatalogFilterViewModel: ObservableObject {
    public static let sharedInstance = CatalogFilterViewModel()
    
    private let viewModelInstance = FilterViewModelInstance.shared.instance

    @Published public var state: SingletonFilterContractState?
    
    public var numberOfRecipes: Int = 0
    public var difficulty: Array<CatalogFilterOptions> = []
    public var cost: Array<CatalogFilterOptions> = []
    public var time: Array<CatalogFilterOptions> = []

    public init() {
        self.viewModelInstance.collect(flow: viewModelInstance.uiState) { data in
            let state = data as! SingletonFilterContractState
           
            self.state = state
            self.numberOfRecipes = Int(state.numberOfResult)
            self.difficulty = state.difficulty
            self.cost = state.cost
            self.time = state.time
        }
    }
    
    public func setEvent(event: SingletonFilterContractEvent) {
        viewModelInstance.setEvent(event: event)
    }
    
    public func clear() {
        viewModelInstance.clear()
    }
}
