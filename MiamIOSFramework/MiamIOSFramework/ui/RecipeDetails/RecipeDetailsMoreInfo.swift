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
            if (recipe.preparationTimeIos != "0") {
                HStack{
                    Text(RecipeDetailsText.sharedInstance.preparationTime + " :")
                        .foregroundColor(Color.miamColor(.secondaryText))
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyMediumStyle)
                    Text(recipe.preparationTimeIos)
                        .foregroundColor(Color.miamColor(.secondaryText))
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleExtraSmallStyle)
                }
            }
            if (recipe.cookingTimeIos != "0") {
                HStack{
                    Text(RecipeDetailsText.sharedInstance.cookingTime + " :")
                        .foregroundColor(Color.miamColor(.secondaryText))
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyMediumStyle)
                    Text(recipe.cookingTimeIos)
                        .foregroundColor(Color.miamColor(.secondaryText))
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleExtraSmallStyle)
                }
            }
            if (recipe.restingTimeIos != "0") {
                HStack{
                    Text(RecipeDetailsText.sharedInstance.restingTime + " :")
                        .foregroundColor(Color.miamColor(.secondaryText))
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyMediumStyle)
                    Text(recipe.restingTimeIos)
                        .foregroundColor(Color.miamColor(.secondaryText))
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleExtraSmallStyle)
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
                        .foregroundColor(Color.miamColor(.grey))
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleExtraSmallStyle)
                        .padding(Dimension.sharedInstance.mPadding)
                        .padding(.leading, Dimension.sharedInstance.lPadding)
                    Image.miamImage(icon: .greyChevronDown)
                    .renderingMode(.original)
                    .rotationEffect(Angle.degrees(collapsed ? 0 : 180))
                    .padding(.trailing , 8)
                }
                .background(Color.miamColor(.greySurface))
                .cornerRadius(15.0)
                .padding()
            }.padding(.trailing, Dimension.sharedInstance.lPadding)
    }
}


