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
            ProgressLoader(color: MiamColor.sharedInstance.primary)
            Text(loadingText)
        }
    }
}

@available(iOS 14, *)
struct CatalogLoadingView_Previews: PreviewProvider {
    static var previews: some View {
        CatalogLoadingView(loadingText: MiamText.sharedInstance.simmering)
    }
}
