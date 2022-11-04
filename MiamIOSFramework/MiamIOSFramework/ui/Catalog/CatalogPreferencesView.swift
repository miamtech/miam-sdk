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
    var body: some View {
        NavigationView {
            PreferencesView()
                .navigationTitle("Mes préférences")
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
        }
    }
}
