//
//  BasketPreviewBottomView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 06/06/2022.
//

import SwiftUI


internal struct BasketPreviewFooter: View {
    let removeFromBasketAction: () -> Void
    let continueShoppingAction: () -> Void

    var body: some View {
        if (Template.sharedInstance.basketPreviewFooterTemplate != nil) {
            Template.sharedInstance.basketPreviewFooterTemplate!(removeFromBasketAction, continueShoppingAction)
        } else {
            HStack {
                ZStack(alignment: .center) {
                    Button(action: {
                        removeFromBasketAction()
                    }) {
                        Text("Retirer du panier").font(.system(size: 16.0, weight: .regular, design: .default))
                    }.foregroundColor(MiamColor.sharedInstance.bodyText)

                }.padding(.horizontal, Dimension.sharedInstance.mPadding).frame(minWidth: 155.0)

                ZStack(alignment: .center) {
                    Button(action: {
                        continueShoppingAction()
                    }) {
                        Text("Continuer mes achats").font(.system(size: 16.0, weight: .bold, design: .default))
                    }.foregroundColor(MiamColor.sharedInstance.white).padding(.horizontal, Dimension.sharedInstance.sPadding)
                }.frame(maxWidth: .infinity)
                    .frame(height: 64.0)
                    .background(MiamColor.sharedInstance.primaryText)
            }.frame(maxWidth: .infinity, alignment: .bottom).background(MiamColor.sharedInstance.white)
        }
    }
}
