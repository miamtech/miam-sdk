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
    private var recipeVM : RecipeCardVM
    
    private func buy() {
        recipeVM.setEvent(event: RecipeContractEvent.OnAddRecipe())
        goToPreview()
        
    }
    
    private func goToPreview(){
        if(recipeVM.recipe != nil ){
            recipeVM.routerVM.setEvent( event:
                                            RouterOutletContractEvent.GoToPreview(
                                                recipeId : recipeVM.recipe!.id,
                                                vm : recipeVM
                                            )
            )
        }
    }
    
    public init(recipeVM : RecipeCardVM){
        self.recipeVM = recipeVM
    }
    
    public var body: some View {
        if let recipe = recipeVM.recipe {
            if let template = Template.sharedInstance.recipeDetailFooterTemplate {
                template(recipe, recipeVM.guest, recipeVM.isInCart, goToPreview, buy)
            } else {
                defaultRecipeDetailFooterView(recipe: recipe, guest: recipeVM.guest, isInCart: recipeVM.isInCart, goToPreview: goToPreview, buy: buy)
            }
        }
    }
}

@available(iOS 14, *)
public struct defaultRecipeDetailFooterView: View {
    
    let recipe : Recipe
    let guest : Int
    let isInCart: Bool
    let goToPreview: () -> Void
    let buy: () -> Void
    
    public var body: some View {
        HStack {
            PriceView(recipeId: recipe.id, guestNumber: guest).padding(.horizontal,16)
            if(isInCart){
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
