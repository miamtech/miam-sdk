//
//  RecipeTimeView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 21/09/2022.
//

import SwiftUI

@available(iOS 14, *)
struct RecipeTimeView: View {
    let preparationTime: String
    let cookingTime: String
    let restingTime: String
    
    let noPreparationTime = "0s"
    let noCookingTime = "0m"
    let noRestingTime = "0m"
    
    var body: some View {
        HStack {
            if (preparationTime != noPreparationTime) {
                HStack {
                    Text(RecipeDetailsText.sharedInstance.preparationTime + " :")
                        .foregroundColor(Color.miamColor(.secondaryText))
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyMediumStyle)
                    Text(preparationTime).foregroundColor(Color.miamColor(.secondaryText))
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleExtraSmallStyle)
                }
            }
            
            if (cookingTime != noCookingTime) {
                HStack {
                    Text(RecipeDetailsText.sharedInstance.cookingTime + " :")
                        .foregroundColor(Color.miamColor(.secondaryText))
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyMediumStyle)
                    Text(cookingTime).foregroundColor(Color.miamColor(.secondaryText))
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleExtraSmallStyle)
                }
            }
            
            if (restingTime != noRestingTime) {
                HStack {
                    Text(RecipeDetailsText.sharedInstance.restingTime + " :")
                        .foregroundColor(Color.miamColor(.secondaryText))
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyMediumStyle)
                    Text(restingTime).foregroundColor(Color.miamColor(.secondaryText))
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleExtraSmallStyle)
                }
            }
            Spacer()
        }.padding(.horizontal, Dimension.sharedInstance.lPadding)
    }
}
