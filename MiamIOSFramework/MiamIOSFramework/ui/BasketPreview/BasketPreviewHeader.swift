//
//  BasketPreviewHeader.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 10/06/2022.
//

import Foundation
import SwiftUI

struct BasketPreviewHeader: View {
    private let headerHeight = 150.0

    let basketTitle: String
    let basketDescription: String
    let pricePerGuest: String
    let numberOfGuests: Int
    let price: String
    private var formattedPrice: String {
        guard let priceDouble = Double(price) else {
            return ""
        }

        return String(format: "%.2f€", priceDouble)
    }
    let pictureURL: URL

    let descreaseGuestsCount: () -> Void
    let increaseGuestsCount: () -> Void

    var body: some View {
        HStack {
            AsyncImage(url: pictureURL, placeholder: {
                    ProgressView()
                }, height: 150.0).frame(width: 150, height: 150, alignment: .topLeading)
            EmptyView().frame(width: 150, height: 150, alignment: .topLeading)


            VStack (alignment: .leading) {
                Text(basketTitle)
                    .foregroundColor(MiamColor.sharedInstance.black)
                    .font(.system(size: 16, weight: .heavy, design: .default))
                    .padding(.leading, Dimension.sharedInstance.sPadding)

                Text(basketDescription)
                    .foregroundColor(MiamColor.sharedInstance.bodyText)
                    .font(.system(size: 16, weight: .light, design: .default))
                    .padding(.leading, Dimension.sharedInstance.sPadding)
                    .padding(.top, Dimension.sharedInstance.borderWidth)

                Text(pricePerGuest)
                    .foregroundColor(MiamColor.sharedInstance.bodyText)
                    .font(.system(size: 16, weight: .light, design: .default))
                    .padding(.leading, Dimension.sharedInstance.sPadding)
            }.frame(width: .infinity, height: .infinity, alignment: .topLeading)
        }.frame(width: .infinity, height: headerHeight, alignment: .topLeading)
        HStack {
            Text("\(price) €").foregroundColor(MiamColor.sharedInstance.primary).fontWeight(.bold).padding([.leading], Dimension.sharedInstance.lPadding)
            Spacer()

            CounterView(count: numberOfGuests, isDisable: false) {

            } decrease: {

            }

        }
                Text(formattedPrice).foregroundColor(MiamColor.sharedInstance.primary).fontWeight(.bold).padding([.leading], Dimension.sharedInstance.lPadding)
        Divider().background(MiamColor.sharedInstance.borderBottom).frame(height: 1.0, alignment: .leading)
    }
}
