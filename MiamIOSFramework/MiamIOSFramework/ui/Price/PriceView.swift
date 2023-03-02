//
//  PriceView.swift
//  MiamIOSFramework
//
//  Created by Miam on 02/05/2022.
//

import SwiftUI
import miamCore

@available(iOS 14, *)
public struct PriceView: View {

    @ObservedObject var viewModel: PriceVM = PriceVM()

    private var formattedPrice: String {
        guard let price = viewModel.price?.pricePerServe else {
            return ""
        }

        let numberFormatter = NumberFormatter()
        numberFormatter.currencyCode = "EUR"
        numberFormatter.numberStyle = .currency
        numberFormatter.maximumFractionDigits = 2
        guard let formattedPrice = numberFormatter.string(from: NSNumber(floatLiteral: price)) else {
            return ""
        }

        return formattedPrice
    }

    public init(
        recipeId: String,
        guestNumber: Int
    ) {
        viewModel.setRecipe(recipeId: recipeId, guestNumber: Int32(guestNumber))
    }

    public var body: some View {
        if let template = Template.sharedInstance.priceViewTemplate {
            template(
                viewModel.price?.price ?? 0,
                viewModel.price?.pricePerServe ?? 0
            )
        } else {
            VStack {
                HStack(alignment: .top, spacing: 2) {
                    Text(formattedPrice)
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyMediumBoldStyle)
                }

                Text(MiamText.sharedInstance.preGuests)
                    .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodySmallStyle)
                    .foregroundColor(Color.miamColor(.grey))
            }
        }
    }
}

@available(iOS 14, *)
struct PriceView_Previews: PreviewProvider {
    static var previews: some View {
        PriceView(recipeId: String(1), guestNumber: 4)
    }
}
