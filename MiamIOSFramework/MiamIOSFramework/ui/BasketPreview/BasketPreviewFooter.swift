//
//  BasketPreviewBottomView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 06/06/2022.
//

import SwiftUI

@available(iOS 14, *)
internal struct BasketPreviewFooter: View {
    let removeFromBasketAction: () -> Void
    let continueShoppingAction: () -> Void

    var body: some View {
        if let template = Template.sharedInstance.basketPreviewFooterTemplate {
            template(removeFromBasketAction, continueShoppingAction)
        } else {
            HStack {
                ZStack(alignment: .center) {
                    Button {
                        removeFromBasketAction()
                    } label: {
                        Text(MiamText.sharedInstance.removeFromBasket)
                            .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyBigStyle)
                    }.foregroundColor(Color.miamColor(.secondaryText))

                }.padding(.horizontal, Dimension.sharedInstance.mPadding).frame(minWidth: 155.0)

                ZStack(alignment: .center) {
                    Button {
                        continueShoppingAction()
                    } label: {
                        Text(MiamText.sharedInstance.keepShopping)
                            .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyBigBoldStyle)
                    }.foregroundColor(Color.miamColor(.white)).padding(.horizontal, Dimension.sharedInstance.sPadding)
                }.frame(maxWidth: .infinity)
                    .frame(height: 64.0)
                    .background(Color.miamColor(.primaryText))
            }.frame(maxWidth: .infinity, alignment: .bottom).background(Color.miamColor(.white))
        }
    }
}
