//
//  BasketPreviewHeader.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 10/06/2022.
//

import Foundation
import SwiftUI
import miamCore

@available(iOS 14, *)
struct BasketPreviewHeader: View {
    private let headerHeight = 150.0

    let basketTitle: String
    let basketDescription: String
    let pricePerGuest: String
    let numberOfGuests: Int
    let price: String
    let showDetailsButton = false
    let isReloading: Bool

    private var formattedPrice: String {
        guard let priceDouble = Double(price) else {
            return ""
        }

        return String(format: "%.2f€", priceDouble)
    }

    let pictureURL: URL?

    let updateGuest: (Int) -> Void
    let goToDetail: () -> Void

    var body: some View {
        if let template = Template.sharedInstance.basketPreviewHeaderTemplate {
            template(basketTitle, pictureURL, basketDescription, pricePerGuest,
                     numberOfGuests, price, isReloading, updateGuest)
        } else {
            VStack(alignment: .leading, spacing: 0) {
                HStack {
                    if let picture = pictureURL {
                        AsyncImage(url: picture, height: 120.0).frame(width: 120, height: 120, alignment: .topLeading).cornerRadius(12.0)
                    } else {
                        Image.miamImage(icon: .empty).frame(width: 120, height: 120)
                    }
                    VStack(alignment: .leading) {
                        Text(basketTitle)
                            .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleMediumStyle)
                            .foregroundColor(Color.miamColor(.black))

                        Text(basketDescription)
                            .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyBigLightStyle)
                            .foregroundColor(Color.miamColor(.secondaryText))
                            .padding(.top, Dimension.sharedInstance.borderWidth)

                        Text(pricePerGuest)
                            .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyBigLightStyle)
                            .foregroundColor(Color.miamColor(.secondaryText))
                        Button {
                            goToDetail()
                        } label: {
                            Text(MiamText.sharedInstance.showDetails)
                                .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyBigBoldStyle)
                                .foregroundColor(Color.miamColor(.primaryText))
                        }.padding([.top], Dimension.sharedInstance.sPadding)
                    }.frame(alignment: .topLeading).padding([.leading], Dimension.sharedInstance.sPadding)
                }.frame(height: headerHeight, alignment: .topLeading)
                HStack {
                    Text(formattedPrice)
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyBigBoldStyle)
                        .foregroundColor(Color.miamColor(.primary))
                    Spacer()

                    CounterView(
                        count: numberOfGuests,
                        lightMode: false,
                        onCounterChanged: { guestCount in
                        updateGuest(guestCount)
                    },
                        isLoading: isReloading,
                        isDisable: isReloading,
                        minValue: 1,
                        maxValue: 99)
                }
            }.padding([.leading, .trailing], 16.0)
            Divider().background(Color.miamColor(.borderLight)).frame(height: 1.0, alignment: .leading)
        }
    }
}
