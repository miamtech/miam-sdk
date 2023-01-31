//
//  IngredientNotAddedRow.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 10/06/2022.
//

import Foundation
import SwiftUI

@available(iOS 14, *)
struct IngredientNotInBasketRow: View {
    let name: String
    let addIngredientAction: () -> Void
    let isAddable: Bool
    var body: some View {
        if Template.sharedInstance.ingredientNotInBasketRowTemplate != nil {
            Template.sharedInstance.ingredientNotInBasketRowTemplate!(name, isAddable ? addIngredientAction : nil)
        } else {
            HStack {
                Text(name.capitalizingFirstLetter())
                    .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleMediumStyle)
                    .foregroundColor(Color.miamColor(.secondaryText))

                Spacer()
                if isAddable {
                Button(action: addIngredientAction) {
                    Image.miamImage(icon: .plusGreen)
                        .resizable()
                        .aspectRatio( contentMode: .fit)
                        .frame(width: 30, height: 30, alignment: .center)
                        .foregroundColor(Color.miamColor(.primary))
                    Text(MiamText.sharedInstance.addIngredientText)
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyBigBoldStyle)
                        .foregroundColor(Color.miamColor(.primary))
                }

                }

            }.padding(.horizontal, Dimension.sharedInstance.lPadding)
                .padding(.bottom, Dimension.sharedInstance.lPadding)
        }
    }
}

@available(iOS 14, *)
struct IngredientNotInBasketRow_Previews: PreviewProvider {
    static var previews: some View {
        IngredientNotInBasketRow(name: "Oeufs", addIngredientAction: {}, isAddable: true)
    }
}
