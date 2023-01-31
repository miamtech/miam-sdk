//
//  BasketPreviewLoadingView.swift
//  MiamIOSFramework
//
//  Created by Miam on 21/06/2022.
//

import SwiftUI

@available(iOS 14, *)
struct BasketPreviewLoadingView: View {
    var body: some View {
        if let template = Template.sharedInstance.basketPreviewLoadingViewTemplate {
            template()
        } else {
            VStack {
                Text("Préparation du repas ...")
                    .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyBigStyle)
                ProgressLoader(color: Color.miamColor(.primaryText))
            }
        }
    }
}
