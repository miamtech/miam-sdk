//
//  IngredientRow.swift
//  MiamIOSFramework
//
//  Created by miam on 12/05/2022.
//

import SwiftUI
import shared

struct IngredientRow: View {
    
    private var ingredientName : String
    private var qty : String
    
    init(ingredientName : String, qty: String){
        var ingredientNameCapitailized = ingredientName
        ingredientNameCapitailized.capitalizeFirstLetter()
        self.ingredientName = ingredientNameCapitailized
        self.qty = qty
    }
        
    var body: some View {
        HStack {
            Text(ingredientName)
                .foregroundColor(MiamColor.sharedInstance.black)
                .font(.system(size: 16, weight: .regular, design: .default))
                .padding(Dimension.sharedInstance.mPadding)
            
            Spacer()
            
            Text(qty)
                .foregroundColor(MiamColor.sharedInstance.black)
                .font(.system(size: 16, weight: .bold, design: .default))
                .cornerRadius(25.0).padding(Dimension.sharedInstance.mPadding)
        }
    }
}

