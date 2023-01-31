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

    private var recipe: Recipe

    @SwiftUI.State private var collapsed: Bool = true

    init(recipe: Recipe) {
        self.recipe = recipe
    }

    var body: some View {
        HStack {
            if recipe.preparationTimeIos != "0" {
                HStack {
                    Text(RecipeDetailsText.sharedInstance.preparationTime + " :")
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyMediumStyle)
                        .foregroundColor(Color.miamColor(.secondaryText))
                    Text(recipe.preparationTimeIos)
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleExtraSmallStyle)
                        .foregroundColor(Color.miamColor(.secondaryText))
                }
            }
            if recipe.cookingTimeIos != "0" {
                HStack {
                    Text(RecipeDetailsText.sharedInstance.cookingTime + " :")
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyMediumStyle)
                        .foregroundColor(Color.miamColor(.secondaryText))
                    Text(recipe.cookingTimeIos)
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleExtraSmallStyle)
                        .foregroundColor(Color.miamColor(.secondaryText))
                }
            }
            if recipe.restingTimeIos != "0" {
                HStack {
                    Text(RecipeDetailsText.sharedInstance.restingTime + " :")
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyMediumStyle)
                        .foregroundColor(Color.miamColor(.secondaryText))
                    Text(recipe.restingTimeIos)
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleExtraSmallStyle)
                        .foregroundColor(Color.miamColor(.secondaryText))
                }
            }
        }
        .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: collapsed ? 0 : .none)
        .clipped()
        .animation(.easeOut)
        .transition(.slide)
            Button {
                self.collapsed.toggle()
            } label: {
                HStack {
                    Text(MiamText.sharedInstance.recpeitDetailsInfo)
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleExtraSmallStyle)
                        .foregroundColor(Color.miamColor(.grey))
                        .padding(Dimension.sharedInstance.mPadding)
                        .padding(.leading, Dimension.sharedInstance.lPadding)
                    Image.miamImage(icon: .greyChevronDown)
                    .renderingMode(.original)
                    .rotationEffect(Angle.degrees(collapsed ? 0 : 180))
                    .padding(.trailing, 8)
                }
                .background(Color.miamColor(.greySurface))
                .cornerRadius(15.0)
                .padding()
            }.padding(.trailing, Dimension.sharedInstance.lPadding)
    }
}
