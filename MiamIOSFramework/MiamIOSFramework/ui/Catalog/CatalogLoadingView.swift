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
        if let template = Template.sharedInstance.catalogLoadingViewTemplate {
            template(loadingText)
        } else {
            VStack {
                Text(loadingText)
                    .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyBigStyle)
                ProgressLoader(color: Color.miamColor(.primary))
            }
        }
    }
}

