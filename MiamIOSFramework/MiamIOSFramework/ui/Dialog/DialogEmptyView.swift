//
//  DialogEmptyView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 07/12/2022.
//  Copyright © 2022 Miam. All rights reserved.
//

import SwiftUI

@available(iOS 14, *)
struct DialogEmptyView: View {
    let closeAction: () -> Void
    var body: some View {
        NavigationView {
            VStack {
                Text("Une erreur s'est produite. Veuillez réessayer")
                    .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyBigStyle)
                Button {
                    closeAction()
                } label: {
                    HStack(alignment: .center) {
                        Text("Fermer")
                            .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyBigStyle)
                    }
                }.foregroundColor(Color.miamColor(.white))
                    .frame(minHeight: 40.0, maxHeight: 40.0)
                    .padding(.horizontal, Dimension.sharedInstance.lPadding)
                    .background(Color.miamColor(.primaryText))
                    .cornerRadius(25)
                    .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyBoldStyle)
                    .overlay(Capsule().stroke(Color.miamColor(.primary), lineWidth: 1.0))
                    .padding(.bottom, Dimension.sharedInstance.lPadding)
            }.toolbar {
                Button("Fermer") {
                    closeAction()
                }
            }
        }
    }
}

@available(iOS 14, *)
struct DialogEmptyView_Previews: PreviewProvider {
    static var previews: some View {
        DialogEmptyView(closeAction: {})
    }
}
