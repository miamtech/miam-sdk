//
//  RecipeDetailsFooter.swift
//  MiamIOSFramework
//
//  Created by Miam on 01/06/2022.
//

import SwiftUI

struct RecipeDetailsFooter: View {
    
    private var recipeVM : RecipeCardVM
    
    init(recipeVM : RecipeCardVM){
        self.recipeVM = recipeVM
    }
    var body: some View {
        HStack {
            if(recipeVM.recipe != nil){
                PriceView(recipeId: recipeVM.recipe!.id, guestNumber: Int(recipeVM.currentState.guest))
            }
            
            if(recipeVM.currentState.isInCart){
                HStack {
                    Text(RecipeDetailsText.sharedInstance.alreadyInCart).foregroundColor(MiamColor.sharedInstance.white)
                        .padding(.horizontal, Dimension.sharedInstance.sPadding)
                        .font(.system(size: 12.0, weight: .bold, design: .default))
                }.frame(maxWidth: .infinity)
                    .frame(height: 80.0)
                .background(MiamColor.sharedInstance.ternary)
                
            } else {
                HStack {
                    Text(RecipeDetailsText.sharedInstance.checkBasketPreview).foregroundColor(MiamColor.sharedInstance.white)
                        .padding(.horizontal, Dimension.sharedInstance.sPadding)
                        .font(.system(size: 12.0, weight: .bold, design: .default))
                    Image("cart")
                        .aspectRatio(contentMode: .fit)
                        .frame(width: 24, height: 24, alignment: .center)
                        .foregroundColor(Color.white)
                }.frame(maxWidth: .infinity)
                    .frame(height: 80.0)
                .background(MiamColor.sharedInstance.primaryText)
            }
        }
    }
}
