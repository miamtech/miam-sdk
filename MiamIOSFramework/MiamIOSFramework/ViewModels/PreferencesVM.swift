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
public class PreferencesVM: ObservableObject {
    static let sharedInstance = PreferencesVM()
    private let preferencesViewModelInstance = PreferencesViewModelInstance.shared.instance
    
    @Published public var state: PreferencesContractState?
  
    public var diets: [CheckableTag] = []
    public var equipments: [CheckableTag] = []
    public var ingredients: [CheckableTag] = []
    
    public init() {
        self.preferencesViewModelInstance.collect(flow: self.preferencesViewModelInstance.uiState) { data in
            guard let state = data as? PreferencesContractState else {
                return
            }
            self.state = state

            switch state.basicState {
            case _ as BasicUiStateSuccess<KotlinBoolean>:
                self.diets = state.diets
                self.ingredients = state.ingredients
                self.equipments = state.equipments
            default:
                break
            }
        }
    }
    
    public func addTag(_ tag: Tag) {
        preferencesViewModelInstance.addIngredientPreference(tag: tag)
    }
    
    public func updateGuestsNumber(_ numberOfGuests: Int) {
        preferencesViewModelInstance.changeGlobalGuest(numberOfGuest: Int32(numberOfGuests))
    }
    
    public func togglePreference(_ preference: CheckableTag) {
        preferencesViewModelInstance.togglePreference(tagIdToToggle: preference.tag.id)
    }
    
    public func resetPreferences() {
        preferencesViewModelInstance.resetPreferences()
    }

    public func applyPreferences() {
        preferencesViewModelInstance.applyPreferences()
    }
}
