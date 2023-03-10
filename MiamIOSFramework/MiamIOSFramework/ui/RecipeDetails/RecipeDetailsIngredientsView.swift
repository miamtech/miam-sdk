//
//  RecipeIngredientsListView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 16/09/2022.
//

import SwiftUI
import miamCore

@available(iOS 14.0, *)
struct RecipeDetailsIngredientsView: View {
    let ingredients: [Ingredient]
    let recipeGuests: Int
    let currentGuests: Int
    let guestUpdating: Bool
    let updateGuestsAction: (Int) -> Void

    init(ingredients: [Ingredient], recipeGuests: Int, currentGuests: Int,
         guestUpdating: Bool, updateGuestsAction: @escaping (Int) -> Void) {
        self.ingredients = ingredients
        self.recipeGuests = recipeGuests
        self.currentGuests = currentGuests
        self.guestUpdating = guestUpdating
        self.updateGuestsAction = updateGuestsAction
    }

    var body: some View {
        if let template = Template.sharedInstance.recipeDetailsIngredientsViewTemplate {
            template(
                ingredients,
                recipeGuests,
                currentGuests,
                guestUpdating,
                updateGuestsAction
            )
        } else {
            HStack {
                HStack {
                    Text("\(ingredients.count) \(MiamText.sharedInstance.ingredients)")
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleStyle)
                        .foregroundColor(Color.miamColor(.black))
                        .padding(Dimension.sharedInstance.lPadding)
                    Spacer()
                    CounterView(
                        count: currentGuests,
                        lightMode: false,
                        onCounterChanged: { guestNumber in updateGuestsAction(guestNumber) },
                        isLoading: guestUpdating,
                        isDisable: guestUpdating,
                        minValue: 1,
                        maxValue: 99
                    )
                }
            }.frame(height: 60.0, alignment: .topLeading)
            Divider()
                .background(Color.miamColor(.borderLight))
                .padding(.horizontal, Dimension.sharedInstance.lPadding)

            // Ingredients ListView
            VStack {
                VStack {
                    ForEach(ingredients, id: \.self) { ingredient in
                        if let attributes = ingredient.attributes {
                            let quantity = quantityForIngredient(ingredient, currentNumberOfGuests: currentGuests,
                                                                 recipeNumberOfGuests: recipeGuests)
                            let formattedQuantity = formatQuantity(quantity: quantity, unit: attributes.unit)
                            RecipeDetailsIngredientRow(ingredientName: attributes.name ?? "",
                                                       quantity: formattedQuantity)
                        }
                    }
                }.padding(.vertical, Dimension.sharedInstance.lPadding)
            }.background(Color.miamColor(.greyLighter)).cornerRadius(15.0).padding( .horizontal,
                                                                                    Dimension.sharedInstance.lPadding)
        }
    }

    func formatQuantity(quantity: Float, unit: String?) -> String {
        return QuantityFormatter.default().readableFloatNumber(value: quantity, unit: unit)
    }

    func quantityForIngredient(_ ingredient: Ingredient, currentNumberOfGuests: Int,
                               recipeNumberOfGuests: Int) -> Float {
        guard let quantity = ingredient.attributes?.quantity else {
            return 0.0
        }

        let realQuantities = QuantityFormatter.default().realQuantities(
            quantity: quantity,
            currentGuest: Int32(currentNumberOfGuests),
            recipeGuest: Int32(recipeNumberOfGuests)
        )

        return realQuantities
    }
}

@available(iOS 14.0, *)
struct RecipeIngredientsListView_Previews: PreviewProvider {
    static var previews: some View {
        RecipeDetailsIngredientsView(ingredients: [], recipeGuests: 4, currentGuests: 6, guestUpdating: false, updateGuestsAction: {_ in })
    }
}
