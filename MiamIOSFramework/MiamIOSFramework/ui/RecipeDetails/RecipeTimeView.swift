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
    let noCookingTime = "0s"
    let noRestingTime = "0s"
    
    var body: some View {
        if let template = Template.sharedInstance.recipeTimeViewTemplate {
            template(preparationTime, cookingTime, restingTime)
        } else {
            HStack {
                if (preparationTime != noPreparationTime) {
                    HStack {
                        Text(RecipeDetailsText.sharedInstance.preparationTime + " :")
                            .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyMediumStyle)
                            .foregroundColor(Color.miamColor(.secondaryText))
                        Text(preparationTime).foregroundColor(Color.miamColor(.secondaryText))
                            .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleExtraSmallStyle)
                    }
                }
                
                if (cookingTime != noCookingTime) {
                    HStack {
                        Text(RecipeDetailsText.sharedInstance.cookingTime + " :")
                            .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyMediumStyle)
                            .foregroundColor(Color.miamColor(.secondaryText))
                        Text(cookingTime).foregroundColor(Color.miamColor(.secondaryText))
                            .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleExtraSmallStyle)
                    }
                }
                
                if (restingTime != noRestingTime) {
                    HStack {
                        Text(RecipeDetailsText.sharedInstance.restingTime + " :")
                            .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyMediumStyle)
                            .foregroundColor(Color.miamColor(.secondaryText))
                        Text(restingTime).foregroundColor(Color.miamColor(.secondaryText))
                            .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleExtraSmallStyle)
                    }
                }
                Spacer()
            }.padding(.horizontal, Dimension.sharedInstance.lPadding)
        }
        
    }
}
