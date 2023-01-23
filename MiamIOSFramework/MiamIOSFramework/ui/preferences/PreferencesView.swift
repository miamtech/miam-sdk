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
    @ObservedObject private var preferencesViewModel: PreferencesVM = PreferencesVM.sharedInstance
   
    let onAddTagTapped: () -> Void
    let closeTapped: () -> Void
    let applyPreferencesTapped: () -> Void
   
    let defaultNumberOfGuests = KotlinInt(int: 4)
    
    public init(onAddTagTapped: @escaping () -> Void, closeTapped: @escaping () -> Void,
                applyPreferencesTapped: @escaping () -> Void) {
        self.onAddTagTapped = onAddTagTapped
        self.closeTapped = closeTapped
        self.applyPreferencesTapped = applyPreferencesTapped
    }
    
    public var body: some View {
        if let currentState = preferencesViewModel.state {
            VStack {
                ManagementResourceState<PreferencesContent, PreferencesSuccessView, PreferencesLoadingView, EmptyView>(
                    resourceState: currentState.basicState,
                    successView: PreferencesSuccessView(numberOfPersons: Int(truncating: currentState.guests ?? defaultNumberOfGuests),
                                                        ingredients: currentState.ingredients,
                                                        equipments: currentState.equipments,
                                                        diets: currentState.diets,
                                                        numberOfRecipesFound: Int(currentState.recipesFound),
                                                        onNumberOfGuestsChanged: { numberOfGuests in
                                                            preferencesViewModel.updateGuestsNumber(numberOfGuests)
                                                        },
                                                        onToggleTag: { tag in
                                                            preferencesViewModel.togglePreference(tag)
                                                        },
                                                        onAddTagTapped: {
                                                            onAddTagTapped()
                                                        }, closeTapped: {
                                                            preferencesViewModel.resetPreferences()
                                                            closeTapped()
                                                        }, applyTapped: {
                                                            preferencesViewModel.applyPreferences()
                                                            closeTapped()
                                                        }),
                    loadingView: PreferencesLoadingView(),
                    emptyView: EmptyView()
                ).frame(maxHeight: .infinity)
            }
        }
    }
}
