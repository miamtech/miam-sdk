//
//  CatalogPreferencesFooterView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 04/11/2022.
//  Copyright © 2022 Miam. All rights reserved.
//

import SwiftUI

@available(iOS 14, *)
public struct PreferencesFooterView: View {
    let cancelTapped: () -> Void
    let applyTapped: () -> Void
    let numberOfRecipesFound: Int
    
    public init(cancelTapped: @escaping () -> Void, applyTapped: @escaping () -> Void, numberOfRecipesFound: Int) {
        self.cancelTapped = cancelTapped
        self.applyTapped = applyTapped
        self.numberOfRecipesFound = numberOfRecipesFound
    }
    
    public var body: some View {
        if let template = Template.sharedInstance.preferencesFooterView {
            template(cancelTapped, applyTapped, numberOfRecipesFound)
        } else {
            HStack {
                Button {
                    cancelTapped()
                } label: {
                    Text(MiamText.sharedInstance.cancel)
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyMediumBoldStyle)
                        .foregroundColor(Color.miamColor(.black))
                }
                .frame(maxWidth: .infinity)
                .fixedSize(horizontal: false, vertical: true)
                .padding(EdgeInsets(top: 10, leading: 20, bottom: 10, trailing: 20))
                .background(Capsule().foregroundColor(Color.miamColor(.white)))
                .overlay(Capsule().stroke(Color.miamColor(.borderLight), lineWidth: 1.0))
                
                Button {
                    applyTapped()
                } label: {
                    Text("\(MiamText.sharedInstance.see) \(numberOfRecipesFound) \(MiamText.sharedInstance.meals)")
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyMediumBoldStyle)
                        .foregroundColor(Color.miamColor(.white))
                }
                .frame(maxWidth: .infinity)
                .fixedSize(horizontal: false, vertical: true)
                .padding(EdgeInsets(top: 10, leading: 20, bottom: 10, trailing: 20))
                .background(Capsule().foregroundColor(Color.miamColor(.primary)))
                
            }.padding([.leading, .trailing], Dimension.sharedInstance.lPadding)
        }
    }
}
