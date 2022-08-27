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
            Circle().fill(Color.miamColor(.white))
                .frame(width: 40, height: 40)
            if(recipeVm.currentState.isLiked){
                Image.miamImage(icon: .likeFilled)
                    .renderingMode(.template)
                    .resizable()
                    .scaledToFill()
                    .frame(width: 26.0, height: 26.0, alignment: .center)
                    .foregroundColor(Color.miamColor(.primaryText))
                    .padding(.top,3)
            } else {
                Image.miamImage(icon: .like)
                    .renderingMode(.template)
                    .resizable()
                    .scaledToFill()
                    .frame(width: 26.0, height: 26.0, alignment: .center)
                    .foregroundColor(Color.miamColor(.primaryText))
                    .padding(.top,3)
            }
        }.onTapGesture {
            recipeVm.toggleLike()
        }
    }
}
