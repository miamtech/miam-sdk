//
//  LikeButton.swift
//  MiamIOSFramework
//
//  Created by Miam on 05/07/2022.
//

import SwiftUI

@available(iOS 14, *)
struct LikeButton: View {
    @ObservedObject var viewModel: LikeButtonVM
    private let recipeId: String
    
    public init(recipeId : String) {
        self.recipeId = recipeId
        self.viewModel = LikeButtonVM()
        self.viewModel.setRecipe(recipeId: recipeId)
    }

    var body: some View {
        if let template = Template.sharedInstance.likeButtonTemplate {
            template(recipeId)
        } else {
            ZStack(){
                Circle().fill(Color.miamColor(.white))
                    .frame(width: 40, height: 40)
                if(self.viewModel.isLiked){
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
                self.viewModel.toggleLike()
            }.onAppear(perform: self.viewModel.listenRecipeLikeChanges).onDisappear(perform: self.viewModel.stopListenRecipeLikeChanges)
            
        }
    }
}
