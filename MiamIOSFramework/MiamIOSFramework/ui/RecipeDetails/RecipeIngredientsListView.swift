//
//  RecipeIngredientsListView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 16/09/2022.
//

import SwiftUI
import miamCore

@available(iOS 14.0, *)
struct RecipeIngredientsListView: View {
    let ingredients: [Ingredient]
    let recipeGuests: Int
    let currentGuests: Int
    let counterView: AnyView
    
    var body: some View {
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
                    IngredientRow(
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

@available(iOS 14.0, *)
struct RecipeIngredientsListView_Previews: PreviewProvider {
    static var previews: some View {
        RecipeIngredientsListView(ingredients: [], recipeGuests: 4, currentGuests: 6, counterView: AnyView(EmptyView()))
    }
}
