//
//  ItemSelectorProductRow.swift
//  MiamIOSFramework
//
//  Created by Miam on 08/06/2022.
//

import SwiftUI
import miamCore

@available(iOS 14, *)
struct ItemSelectorProductRow: View {

    private var isSelected: Bool
    private var product: BasketPreviewLine

    init(product: BasketPreviewLine, isSelected: Bool = false) {
        self.isSelected = isSelected
        self.product = product

    }

    var body: some View {
        if let template = Template.sharedInstance.itemSelectorProductRowTemplate {
            template(product, isSelected)
        } else {
            VStack {
                HStack {
                    if let picture = URL(string: product.picture) {
                        AsyncImage(
                            url: picture,
                            height: 90
                        ).frame(width: 90, height: 90)
                    } else {
                        Image.miamImage(icon: .empty).frame(width: 90, height: 90)
                    }
                    VStack(alignment: .leading) {
                        Text(product.productBrand )
                            .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodySmallBoldStyle)
                            .padding(.bottom, 8)
                        Text(product.productDescription )
                            .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodySmallBoldStyle)
                            .padding(.bottom, 32).frame(width: 200)
                        HStack {
                            Spacer()
                            Text(product.price + "€")
                                .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyBigBoldStyle)
                                .padding(.trailing, 16)
                            Text(isSelected ? ItemSelectorText.sharedInstance.selectedProduct : ItemSelectorText.sharedInstance.select )
                                .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyMediumBoldStyle)
                                .padding(.horizontal, 20)
                                .padding(.vertical, 9)
                                .foregroundColor(Color.miamColor(.white))
                                .background( isSelected ? Color.miamColor(.grey) : Color.miamColor(.primary))
                                .cornerRadius(1000)
                        }
                    }.padding(16)
                }
                Divider()
            }
        }
    }
}
