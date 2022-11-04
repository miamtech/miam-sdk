//
//  PreferencesView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 03/11/2022.
//  Copyright © 2022 Miam. All rights reserved.
//

import SwiftUI
import miamCore

@available(iOS 14, *)
public struct PreferencesView: View {
    @ObservedObject private var preferencesViewModel: PreferencesVM = PreferencesVM()
    
    public init() {}
    
    public var body: some View {
        if let currentState = preferencesViewModel.state {
            ManagementResourceState<KotlinBoolean, PreferencesSuccessView, PreferencesLoadingView, EmptyView>(
                resourceState: currentState.basicState,
                successView: PreferencesSuccessView(numberOfPersons: Int(currentState.guests),
                                                    ingredients: currentState.ingredients,
                                                    equipments: currentState.equipments,
                                                    diets: currentState.diets,
                                                    onNumberOfGuestsChanged: { numberOfGuests in
                                                        preferencesViewModel.changeGlobaleGuest(numberOfGuest: Int32(numberOfGuests))
                                                    },
                                                    onToggleTag: { tag in
                                                        preferencesViewModel.togglePreference(checkableTag: tag)
                                                    }
                                                    ),
                loadingView: PreferencesLoadingView(),
                emptyView: EmptyView()
            )
        }
    }
}
