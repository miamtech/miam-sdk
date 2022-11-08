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
class PreferencesVM: ObservableObject {
    static let sharedInstance = PreferencesVM()
    private let preferencesViewModelInstance = PreferencesViewModelInstance.shared.instance
    
    @Published var state: PreferencesContractState?
  
    var diets: [CheckableTag] = []
    var equipments: [CheckableTag] = []
    var ingredients: [CheckableTag] = []
    
    private init() {
        self.preferencesViewModelInstance.collect(flow: self.preferencesViewModelInstance.uiState) { data in
            guard let state = data as? PreferencesContractState else {
                return
            }
            self.state = state
            
            switch(state.basicState) {
            case _ as BasicUiStateSuccess<KotlinBoolean>:
                self.diets = state.diets
                self.ingredients = state.ingredients
                self.equipments = state.equipments
            default:
                break
            }
        }
    }
    
    func addTag(_ tag: Tag) {
        preferencesViewModelInstance.addIngredientPreference(tag: tag)
    }
    
    func updateGuestsNumber(_ numberOfGuests: Int) {
        preferencesViewModelInstance.changeGlobaleGuest(numberOfGuest: Int32(numberOfGuests))
    }
    
    func togglePreference(_ preference: CheckableTag) {
        preferencesViewModelInstance.togglePreference(tagIdToToogle: preference.tag.id)
    }
    
    func resetPreferences() {
        preferencesViewModelInstance.resetPreferences()
    }

    func applyPreferences() {
        preferencesViewModelInstance.applyPreferences()
    }
}
