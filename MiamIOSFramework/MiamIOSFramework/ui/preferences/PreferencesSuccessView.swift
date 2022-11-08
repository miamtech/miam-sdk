//
//  PreferencesSuccessView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 03/11/2022.
//  Copyright © 2022 Miam. All rights reserved.
//

import SwiftUI
import miamCore
@available(iOS 14, *)
struct PreferencesSuccessView: View {
    let numberOfPersons: Int
    let ingredients: [CheckableTag]
    let equipments: [CheckableTag]
    let diets: [CheckableTag]
    let numberOfRecipesFound: Int
    let onNumberOfGuestsChanged: (Int) -> Void
    let onToggleTag: (CheckableTag) -> Void
    let onAddTagTapped: () -> Void
    let closeTapped: () -> Void
    let applyTapped: () -> Void
    
    var body: some View {
        GeometryReader { geometry in
            VStack {
                ScrollView {
                    VStack(alignment: .leading) {
                        HStack {
                            Text(MiamText.sharedInstance.numberOfPersons)
                            Spacer()
                            CounterView(count: numberOfPersons, isDisable: false) {
                                onNumberOfGuestsChanged(numberOfPersons + 1)
                            } decrease: {
                                guard numberOfPersons > 1 else {
                                    return
                                }
                                onNumberOfGuestsChanged(numberOfPersons - 1)
                            }
                            
                        }.background(Color.miamColor(.lightGreyBackground))
                        PreferencesListView(title: "Régime particulier",
                                            subtitle: "Avez-vous un régime particulier ?",
                                            preferences: diets) { tag in
                            onToggleTag(tag)
                        }
                        
                        PreferencesTagsListView(title: "Gouts",
                                                subtitle: "Y a-t-il des ingrédients que vous n'aimez pas ?",
                                                tags: ingredients, geometry: geometry,
                                                onToggleTag: { tag in
                            onToggleTag(tag)
                        }, onAddTagTapped: {
                            onAddTagTapped()
                        })
                        
                        PreferencesListView(title: "Mode de cuisson",
                                            subtitle: "De quels modes de cuissons disposez-vous ?",
                                            preferences: equipments) { tag in
                            onToggleTag(tag)
                        }
                    }
                    
                }
                .padding(16.0)
                .background(Color.miamColor(.lightGreyBackground))
                PreferencesFooterView(cancelTapped: closeTapped, applyTapped: applyTapped,
                                      numberOfRecipesFound: numberOfRecipesFound)
            }
            
        }
    }
}

