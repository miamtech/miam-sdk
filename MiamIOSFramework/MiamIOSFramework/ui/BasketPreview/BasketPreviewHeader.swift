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
    private var formattedPrice: String {
        guard let priceDouble = Double(price) else {
            return ""
        }

        return String(format: "%.2f€", priceDouble)
    }
    
    
    let pictureURL: URL?
    private var hasPicture: Bool {
        return pictureURL != nil
    }


    let updateGuest: (Int) -> Void
    let goToDetail: () -> Void

    var body: some View {
        if (Template.sharedInstance.basketPreviewHeaderTemplate != nil) {
            Template.sharedInstance.basketPreviewHeaderTemplate!(basketTitle, pictureURL, basketDescription, price, numberOfGuests, price, updateGuest)
        } else {
            VStack(alignment: .leading) {
                HStack {
                    if hasPicture {
                        AsyncImage(url: pictureURL!, height: 120.0).frame(width:120, height: 120, alignment: .topLeading).cornerRadius(12.0)
                    }

                    VStack (alignment: .leading) {
                        Text(basketTitle)
                            .foregroundColor(Color.miamColor(.black))
                            .font(.system(size: 16, weight: .heavy, design: .default))

                        Text(basketDescription)
                            .foregroundColor(Color.miamColor(.secondaryText))
                            .font(.system(size: 16, weight: .light, design: .default))
                            .padding(.top, Dimension.sharedInstance.borderWidth)

                        Text(pricePerGuest)
                            .foregroundColor(Color.miamColor(.secondaryText))
                            .font(.system(size: 16, weight: .light, design: .default))

                        Button {
                            goToDetail()
                        } label: {
                            Text(MiamText.sharedInstance.showDetails).fontWeight(.heavy).foregroundColor(Color.miamColor(.primaryText))
                        }.padding([.top], Dimension.sharedInstance.sPadding)
                    }.frame(alignment: .topLeading).padding([.leading], Dimension.sharedInstance.sPadding)
                }.frame(height: headerHeight, alignment: .topLeading)
                HStack {
                    Text(formattedPrice).foregroundColor(Color.miamColor(.primary)).fontWeight(.bold)
                    Spacer()

                    CounterView(count: numberOfGuests)  { guestCount in
                        updateGuest(guestCount)
                    }
                }
            }.padding([.leading, .trailing], 16.0)
            Divider().background(Color.miamColor(.borderLight)).frame(height: 1.0, alignment: .leading)
        }
    }
}
