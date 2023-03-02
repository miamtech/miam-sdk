//
//  CatalogPreferencesView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 04/11/2022.
//  Copyright © 2022 Miam. All rights reserved.
//

import SwiftUI

@available(iOS 14, *)
public struct CatalogPreferencesView: View {
    let close: () -> Void
    @State var showSearch = false

    public init(close: @escaping () -> Void) {
        self.close = close
    }

    public var body: some View {
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
                    }).navigationBarTitle(MiamText.sharedInstance.preferencesSearchTitle, displayMode: .inline)
                        .navigationBarBackButtonHidden(true)
                        .navigationBarItems(leading: Button {
                            showSearch = false
                        } label: {
                            Image.miamImage(icon: .back)
                                .renderingMode(.template)
                                .foregroundColor(.black)
                        })
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
