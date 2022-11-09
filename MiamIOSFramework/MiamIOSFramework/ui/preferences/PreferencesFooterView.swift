//
//  CatalogPreferencesFooterView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 04/11/2022.
//  Copyright © 2022 Miam. All rights reserved.
//

import SwiftUI

@available(iOS 14, *)
struct PreferencesFooterView: View {    
    let cancelTapped: () -> Void
    let applyTapped: () -> Void
    let numberOfRecipesFound: Int
    var body: some View {
        HStack {
            Button {
                cancelTapped()
            } label: {
                Text(MiamText.sharedInstance.cancel).foregroundColor(Color.miamColor(.black)).bold()
            }
            .frame(maxWidth: .infinity)
            .fixedSize(horizontal: false, vertical: true)
            .padding(EdgeInsets(top: 10, leading: 20, bottom: 10, trailing: 20))
            .background(Capsule().foregroundColor(Color.miamColor(.white)))
            .overlay(Capsule().stroke(Color.miamColor(.borderLight), lineWidth: 1.0))
            
            Button {
                applyTapped()
            } label: {
                Text("\(MiamText.sharedInstance.see) \(numberOfRecipesFound) \(MiamText.sharedInstance.meals)").foregroundColor(Color.miamColor(.white)).bold()
            }
            .frame(maxWidth: .infinity)
            .fixedSize(horizontal: false, vertical: true)
            .padding(EdgeInsets(top: 10, leading: 20, bottom: 10, trailing: 20))
            .background(Capsule().foregroundColor(Color.miamColor(.primary)))
            
        }.padding([.leading, .trailing], Dimension.sharedInstance.lPadding)
    }
}
