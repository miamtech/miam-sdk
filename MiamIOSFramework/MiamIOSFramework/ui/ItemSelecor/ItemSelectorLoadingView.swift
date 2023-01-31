//
//  ItemSelectorLoadingView.swift
//  MiamIOSFramework
//
//  Created by Miam on 23/01/2023.
//  Copyright © 2023 Miam. All rights reserved.
//

import SwiftUI

@available(iOS 14, *)
struct ItemSelectorLoadingView: View {
    var body: some View {
        if let template = Template.sharedInstance.itemSelectorLoadingViewTemplate {
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
