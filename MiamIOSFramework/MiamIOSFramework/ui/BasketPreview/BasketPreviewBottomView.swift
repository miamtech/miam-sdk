//
//  BasketPreviewBottomView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 06/06/2022.
//

import SwiftUI


internal struct BasketPreviewBottomView: View {
    var body: some View {
        HStack {
            ZStack(alignment: .center) {
                Text("Retirer du panier")
                    .foregroundColor(MiamColor.sharedInstance.bodyText)
                    .font(.system(size: 16.0, weight: .regular, design: .default))
            }.padding(.horizontal, Dimension.sharedInstance.mPadding).frame(minWidth: 155.0)

            ZStack(alignment: .center) {
                Text("Continuer mes courses")
                    .foregroundColor(MiamColor.sharedInstance.white
                    ).padding(.horizontal, Dimension.sharedInstance.sPadding)
                    .font(.system(size: 16.0, weight: .bold, design: .default))
            }.frame(maxWidth: .infinity)
                .frame(height: 64.0)
                .background(MiamColor.sharedInstance.primaryText)
        }.frame(maxWidth: .infinity, alignment: .bottom).background(MiamColor.sharedInstance.white)
    }
}
