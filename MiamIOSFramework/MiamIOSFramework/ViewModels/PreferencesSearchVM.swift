//
//  PreferencesSearchVM.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 04/11/2022.
//  Copyright © 2022 Miam. All rights reserved.
//

import Foundation
import miamCore

@available(iOS 14, *)
class PreferencesSearchVM: PreferencesSearchViewModel, ObservableObject {
    @Published var state: PreferencesSearchContractState?
    @Published var tagsSuggestions: [Tag] = []
    override init() {
        super.init()
        self.collect(flow: uiState) { data in
            guard let state = data as? PreferencesSearchContractState else {
                return
            }
            
            self.state = state
            
            switch(state.searchProposal) {
            case let success as BasicUiStateSuccess<NSArray>:
                if let suggestions = success.data as? [Tag] {
                    self.tagsSuggestions = suggestions
                }
            default:
                ()
            }
        }
    }
}
