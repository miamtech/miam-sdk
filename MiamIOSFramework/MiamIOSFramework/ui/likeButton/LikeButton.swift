//
//  LikeButton.swift
//  MiamIOSFramework
//
//  Created by Miam on 05/07/2022.
//

import SwiftUI

@available(iOS 14, *)
struct LikeButton: View {
    
    @ObservedObject var recipeVm: RecipeCardVM
    
    init(recipeVm: RecipeCardVM){
        self.recipeVm = recipeVm
    }
    
    
    var body: some View {
        ZStack(){
            Circle().fill(MiamColor.sharedInstance.white)
                .frame(width: 34, height: 34)
            if(recipeVm.currentState.isLiked){
                Image("LikeFilled", bundle: Bundle(for: RecipeCardVM.self))
                    .renderingMode(.template)
                    .frame(width: 14.0, height: 14.0, alignment: .center)
                    .foregroundColor(MiamColor.sharedInstance.primaryText)
            } else {
                Image("Like", bundle: Bundle(for: RecipeCardVM.self))
                    .renderingMode(.template)
                    .frame(width: 14.0, height: 14.0, alignment: .center)
                    .foregroundColor(MiamColor.sharedInstance.primaryText)
            }
        }.onTapGesture {
            recipeVm.toggleLike()
        }
    }
}
