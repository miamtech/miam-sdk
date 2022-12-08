//
//  IngredientRow.swift
//  MiamIOSFramework
//
//  Created by miam on 12/05/2022.
//

import SwiftUI
import miamCore

@available(iOS 14, *)
struct RecipeDetailsIngredientRow: View {
    
    private var ingredientName : String
    private var quantity : String
    
    init(ingredientName : String, quantity: String){
        var ingredientNameCapitailized = ingredientName
        ingredientNameCapitailized.capitalizeFirstLetter()
        self.ingredientName = ingredientNameCapitailized
        self.quantity = quantity
    }
        
    var body: some View {
        HStack {
            Text(ingredientName)
                .foregroundColor(Color.miamColor(.black))
                .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyBigStyle)
                .padding(Dimension.sharedInstance.mPadding)
            
            Spacer()
            
            Text(quantity)
                .foregroundColor(Color.miamColor(.black))
                .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleSmallStyle)
                .padding(Dimension.sharedInstance.mPadding)
        }
    }
}
