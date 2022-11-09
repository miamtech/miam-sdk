//
//  CatalogPreferencesView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 04/11/2022.
//  Copyright © 2022 Miam. All rights reserved.
//

import SwiftUI

@available(iOS 14, *)
struct CatalogPreferencesView: View {
    let close: () -> Void
    @State var showSearch = false
    var body: some View {
        NavigationView {
            VStack {
                PreferencesView(onAddTagTapped: {
                    showSearch = true
                }, closeTapped: {
                    close()
                }, applyPreferencesTapped: {
                    close()
                })
                
                NavigationLink("Search", isActive: $showSearch) {
                    // TODO: Show search view
                    PreferencesSearchView(close: {
                        self.showSearch = false
                    }).navigationTitle(MiamText.sharedInstance.preferencesSearchTitle)
                }.hidden()
            }.navigationTitle(MiamText.sharedInstance.preferencesTitle)
                .navigationBarTitleDisplayMode(.inline)
                .toolbar {
                    ToolbarItem(placement: .navigationBarLeading) {
                        Button {
                            close()
                        } label: {
                            Image.miamImage(icon: .cross)
                        }
                    }
                }
        }.accentColor(Color.miamColor(.black))
    }
}
