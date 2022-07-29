//
//  CatalogLoadingView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 08/07/2022.
//

import SwiftUI

@available(iOS 14, *)
struct CatalogLoadingView: View {
    let loadingText: String
    var body: some View {
        VStack {
            Text(loadingText)
            ProgressLoader(color: Color.miamColor(.primary))
        }
    }
}

