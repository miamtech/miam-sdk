//
//  RecipeDetailsFooter.swift
//  MiamIOSFramework
//
//  Created by Miam on 01/06/2022.
//

import SwiftUI
import miamCore

@available(iOS 14, *)
public struct RecipeDetailsFooter: View {
    @ObservedObject private var recipeVM: RecipeCardVM

    let buy: () -> Void
    let goToPreview: () -> Void

    public init(recipeVM: RecipeCardVM, buy: @escaping () -> Void, goToPreview: @escaping () -> Void) {
        self.recipeVM = recipeVM
        self.buy = buy
        self.goToPreview = goToPreview
    }

    public var body: some View {
        if let recipeId = recipeVM.recipe?.id {
            if let template = Template.sharedInstance.recipeDetailFooterTemplate {
                template(recipeVM.recipe!, recipeVM.guest, recipeVM.isInCart, goToPreview, buy)
            } else {
                DefaultRecipeDetailFooterView(recipeId: recipeId, guest: recipeVM.guest,
                                              isInCart: recipeVM.isInCart, goToPreview: goToPreview, buy: buy)
            }
        }
    }
}

@available(iOS 14, *)
public struct DefaultRecipeDetailFooterView: View {
    let recipeId: String
    let guest: Int
    let isInCart: Bool
    let goToPreview: () -> Void
    let buy: () -> Void

    public var body: some View {
        HStack {
            PriceView(recipeId: recipeId, guestNumber: guest).padding(.horizontal, 16)
            if isInCart {
                HStack {
                    Text(RecipeDetailsText.sharedInstance.alreadyInCart).foregroundColor(Color.miamColor(.white))
                        .padding(.horizontal, Dimension.sharedInstance.sPadding)
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyBigBoldStyle)
                }.frame(maxWidth: .infinity)
                    .frame(height: 64.0)
                    .background(Color.miamColor(.ternary)).onTapGesture {
                        goToPreview()
                    }
            } else {
                HStack {
                    Image.miamImage(icon: .cart)
                        .aspectRatio(contentMode: .fit)
                        .frame(width: 24, height: 24, alignment: .center)
                        .foregroundColor(Color.white)
                    Text(RecipeDetailsText.sharedInstance.checkBasketPreview).foregroundColor(Color.miamColor(.white))
                        .padding(.horizontal, Dimension.sharedInstance.sPadding)
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyBigBoldStyle)
                }.frame(maxWidth: .infinity)
                    .frame(height: 64.0)
                    .background(Color.miamColor(.primaryText)).onTapGesture {
                        buy()
                        goToPreview()
                    }
            }
        }
    }
}
