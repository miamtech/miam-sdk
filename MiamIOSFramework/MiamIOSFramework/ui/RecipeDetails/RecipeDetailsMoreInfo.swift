//
//  RecipeDetailsMoreInfo.swift
//  MiamIOSFramework
//
//  Created by Miam on 02/06/2022.
//

import SwiftUI
import miamCore


@available(iOS 14, *)
struct RecipeDetailsMoreInfo: View {
    
    private var recipe : Recipe
    
    @SwiftUI.State private var collapsed: Bool = true
    
    init(recipe : Recipe){
        self.recipe = recipe
    }
    
    var body: some View {
        HStack {
            if(recipe.preparationTimeIos != "0") {
                HStack{
                    Text(RecipeDetailsText.sharedInstance.preparationTime + " :").foregroundColor(MiamColor.sharedInstance.bodyText).font(.system(size: 13.0, weight: .regular, design: .default))
                    Text(recipe.preparationTimeIos).foregroundColor(MiamColor.sharedInstance.bodyText).font(.system(size: 13.0, weight: .heavy, design: .default))
                }
            }
            if(recipe.cookingTimeIos != "0") {
                HStack{
                    Text(RecipeDetailsText.sharedInstance.cookingTime + " :").foregroundColor(MiamColor.sharedInstance.bodyText).font(.system(size: 13.0, weight: .regular, design: .default))
                    Text(recipe.cookingTimeIos).foregroundColor(MiamColor.sharedInstance.bodyText).font(.system(size: 13.0, weight: .heavy, design: .default))
                }
            }
            if(recipe.restingTimeIos != "0") {
                HStack{
                    Text(RecipeDetailsText.sharedInstance.restingTime + " :").foregroundColor(MiamColor.sharedInstance.bodyText).font(.system(size: 13.0, weight: .regular, design: .default))
                    Text(recipe.restingTimeIos).foregroundColor(MiamColor.sharedInstance.bodyText).font(.system(size: 13.0, weight: .heavy, design: .default))
                }
            }
                    }
                    .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: collapsed ? 0 : .none)
                    .clipped()
                    .animation(.easeOut)
                    .transition(.slide)
       
            
            Button(action: {
                self.collapsed.toggle()
            }) {
                HStack {
                    Text(MiamText.sharedInstance.recpeitDetailsInfo)
                        .foregroundColor(MiamColor.sharedInstance.grey)
                        .font(.system(size: 13, weight: .bold, design: .default))
                        .padding(Dimension.sharedInstance.mPadding)
                        .padding(.leading, Dimension.sharedInstance.lPadding)
                Image("chevron-down-grey", bundle: Bundle(for: RecipeCardVM.self))
                    .renderingMode(.original)
                    .rotationEffect(Angle.degrees(collapsed ? 0 : 180))
                    .padding(.trailing , 8)
                }
                .background(MiamColor.sharedInstance.greySurface)
                .cornerRadius(15.0)
                .padding()
            }.padding(.trailing, Dimension.sharedInstance.lPadding)
    }
}


