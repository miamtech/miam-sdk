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
public class PreferencesSearchVM: PreferencesSearchViewModel, ObservableObject {
    @Published public var state: PreferencesSearchContractState?
    @Published public var tagsSuggestions: [Tag] = []
    public override init() {
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
