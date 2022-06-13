//
//  IngredientNotAddedRow.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 10/06/2022.
//

import Foundation
import SwiftUI

struct IngredientNotInBasketRow: View {
    let name: String
    let addIngredientAction: () -> Void
    private let addIngredientText = "Ajouter"
    private let addIngredientIcon = "PlusGreen"
    var body: some View {
        if (Template.sharedInstance.ingredientNotInBasketRowTemplate != nil) {
            Template.sharedInstance.ingredientNotInBasketRowTemplate!(name, addIngredientAction)
        } else {
            HStack {
                Text(name)
                    .font(.system(size: 16.0, weight: .bold, design: .default))
                    .foregroundColor(MiamColor.sharedInstance.bodyText)

                Spacer()

                Button(action: addIngredientAction) {
                    Image(addIngredientIcon)
                        .resizable()
                        .aspectRatio( contentMode: .fit)
                        .frame(width: 30, height: 30, alignment: .center)
                        .foregroundColor(MiamColor.sharedInstance.primary)
                    Text(addIngredientText)
                        .font(.system(size: 16.0, weight: .bold, design: .default))
                        .foregroundColor(MiamColor.sharedInstance.primary)
                }

            }.padding(.horizontal, Dimension.sharedInstance.lPadding)
                .padding(.bottom, Dimension.sharedInstance.lPadding)
        }
    }
}

struct IngredientNotInBasketRow_Previews: PreviewProvider {
    static var previews: some View {
        IngredientNotInBasketRow(name: "Oeufs", addIngredientAction: {})
    }
}
