//
//  BasketPreviewHeader.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 10/06/2022.
//

import Foundation
import SwiftUI
import shared

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


    let decreaseGuestsCount: () -> Void
    let increaseGuestsCount: () -> Void
    let goToDetail: () -> Void

    var body: some View {
        if (Template.sharedInstance.basketPreviewHeaderTemplate != nil) {
            Template.sharedInstance.basketPreviewHeaderTemplate!(basketTitle, pictureURL, basketDescription, price, numberOfGuests, price, decreaseGuestsCount, increaseGuestsCount)
        } else {
            VStack {
                HStack {
                    if hasPicture {
                        AsyncImage(url: pictureURL!, placeholder: {
                                ProgressView()
                        }, height: 120.0).frame(width:120, height: 120, alignment: .topLeading).cornerRadius(12.0)
                    }

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

                        Button {
                            goToDetail()
                        } label: {
                            Text("Voir le détail").fontWeight(.heavy).foregroundColor(MiamColor.sharedInstance.primaryText)
                        }.padding([.leading, .top], Dimension.sharedInstance.sPadding)
                    }.frame(alignment: .topLeading)
                }.frame(height: headerHeight, alignment: .topLeading)
                HStack {

                    Text(formattedPrice).foregroundColor(MiamColor.sharedInstance.primary).fontWeight(.bold).padding([.leading], Dimension.sharedInstance.lPadding)
                    Spacer()

                    CounterView(count: numberOfGuests, isDisable: false) {
                        increaseGuestsCount()
                    } decrease: {
                        decreaseGuestsCount()
                    }
                }
            }.padding([.leading, .trailing], 16.0)
            Divider().background(MiamColor.sharedInstance.borderBottom).frame(height: 1.0, alignment: .leading)
        }
    }
}
