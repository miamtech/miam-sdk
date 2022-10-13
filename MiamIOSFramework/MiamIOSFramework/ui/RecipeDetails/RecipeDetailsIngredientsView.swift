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
    let increaseGuestsAction: () -> Void
    let decreaseGuestsAction: () -> Void
    
    var counterView: CounterView
    init(ingredients: [Ingredient], recipeGuests: Int, currentGuests: Int, increaseGuestsAction: @escaping () -> Void, decreaseGuestsAction: @escaping () -> Void) {
        self.ingredients = ingredients
        self.recipeGuests = recipeGuests
        self.currentGuests = currentGuests
        self.increaseGuestsAction = increaseGuestsAction
        self.decreaseGuestsAction = decreaseGuestsAction
        
        self.counterView = CounterView(
                count: recipeGuests,
                isDisable: false,
                increase: increaseGuestsAction,
                decrease: decreaseGuestsAction
            )
    }
    
    
    var body: some View {
        if let template = Template.sharedInstance.recipeDetailsIngredientsViewTemplate {
            template(
                ingredients,
                recipeGuests,
                currentGuests,
                increaseGuestsAction,
                decreaseGuestsAction
            )
        } else {
            HStack {
                HStack {
                    Text("\(ingredients.count) \(MiamText.sharedInstance.ingredients)")
                        .foregroundColor(Color.miamColor(.black))
                        .font(.system(size: 20, weight: .heavy, design: .default))
                        .padding(Dimension.sharedInstance.lPadding)
                    Spacer()
                    counterView
                }
            }.frame(height: 60.0, alignment: .topLeading)
            Divider()
                .background(Color.miamColor(.borderLight))
                .padding(.horizontal, Dimension.sharedInstance.lPadding)
            
            // Ingredients ListView
            VStack {
                VStack {
                    ForEach(ingredients, id: \.self) { ingredient in
                        RecipeDetailsIngredientRow(
                            ingredientName: ingredient.attributes!.name!,
                            quantity: QuantityFormatter.default().readableFloatNumber(value:
                                                                                        QuantityFormatter.default().realQuantities(
                                                                                            quantity: ingredient.attributes!.quantity!,
                                                                                            currentGuest: Int32(currentGuests),
                                                                                            recipeGuest: Int32(recipeGuests)
                                                                                        ),
                                                                                      unit: ingredient.attributes!.unit))
                    }
                }.padding(.vertical, Dimension.sharedInstance.lPadding)
            }.background(Color.miamColor(.greyLighter)).cornerRadius(15.0).padding( .horizontal, Dimension.sharedInstance.lPadding)
        }
    }
}

@available(iOS 14.0, *)
struct RecipeIngredientsListView_Previews: PreviewProvider {
    static var previews: some View {
        RecipeDetailsIngredientsView(ingredients: [], recipeGuests: 4, currentGuests: 6,
                                     increaseGuestsAction: {}, decreaseGuestsAction: {})
    }
}
