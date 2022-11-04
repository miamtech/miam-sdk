//
//  PreferencesVM.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 02/11/2022.
//  Copyright © 2022 Miam. All rights reserved.
//

import Foundation
import miamCore

@available(iOS 14, *)
class PreferencesVM: SingletonPreferencesViewModel, ObservableObject {
    static let sharedInstance = PreferencesVM()
    
    @Published var state: PreferencesContractState?
  
    var diets: [CheckableTag] = []
    var equipments: [CheckableTag] = []
    var ingredients: [CheckableTag] = []
    
    override init() {
        super.init()
        self.collect(flow: uiState) { data in
            guard let state = data as? PreferencesContractState else {
                return
            }
            self.state = state
            
            switch(state.basicState) {
            case let _ as BasicUiStateSuccess<KotlinBoolean>:
                self.diets = state.diets
                self.ingredients = state.ingredients
                self.equipments = state.equipments
            default:
                break
            }
        }
    }
}
