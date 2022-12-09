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
    let updateGuestAction: (Int) -> Void
   
    
    public var counterView: CounterView
    init(ingredients: [Ingredient], recipeGuests: Int, currentGuests: Int, updateGuestsAction: @escaping (Int) -> Void) {
        self.ingredients = ingredients
        self.recipeGuests = recipeGuests
        self.currentGuests = currentGuests
        self.updateGuestAction = updateGuestsAction
       
        
        self.counterView = CounterView(
                count: currentGuests,
                onCounterChanged: { guestNumber in updateGuestsAction(guestNumber)}
            )
    }
    
    
    var body: some View {
        if let template = Template.sharedInstance.recipeDetailsIngredientsViewTemplate {
            template(
                ingredients,
                recipeGuests,
                currentGuests,
                updateGuestAction
            )
        } else {
            HStack {
                HStack {
                    Text("\(ingredients.count) \(MiamText.sharedInstance.ingredients)")
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleStyle)
                        .foregroundColor(Color.miamColor(.black))
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
        RecipeDetailsIngredientsView(ingredients: [], recipeGuests: 4, currentGuests: 6, updateGuestsAction: {_ in })
    }
}
