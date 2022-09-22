//
//  RecipeDetailsFooter.swift
//  MiamIOSFramework
//
//  Created by Miam on 01/06/2022.
//

import SwiftUI
import miamCore


@available(iOS 14, *)
struct RecipeDetailsFooter: View {
    
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
    
    init(recipeVM : RecipeCardVM){
        self.recipeVM = recipeVM
    }
    
    
    var body: some View {
        if #available(iOS 15.0, *) {
            let _ = Self._printChanges()
        }
        if(Template.sharedInstance.recipeDetailFooterTemplate != nil) {
            Template.sharedInstance.recipeDetailFooterTemplate!(
                recipeVM,
                {goToPreview()},
                {buy()}
            )
        } else {
            
            HStack {
                if(recipeVM.recipe != nil){
                    PriceView(recipeId: recipeVM.recipe!.id, guestNumber: Int(recipeVM.currentState.guest)).padding(.horizontal,16)
                }
                
                if(recipeVM.currentState.isInCart){
                    HStack {
                        Text(RecipeDetailsText.sharedInstance.alreadyInCart).foregroundColor(Color.miamColor(.white))
                            .padding(.horizontal, Dimension.sharedInstance.sPadding)
                            .font(.system(size: 16.0, weight: .bold, design: .default))
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
                            .font(.system(size: 16.0, weight: .bold, design: .default))
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
}
