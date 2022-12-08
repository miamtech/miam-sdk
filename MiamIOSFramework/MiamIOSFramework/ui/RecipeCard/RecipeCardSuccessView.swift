//
//  RecipeCardSucessView.swift
//  MiamIOSFramework
//
//  Created by Miam on 20/06/2022.
//

import SwiftUI
import miamCore


@available(iOS 14, *)
internal struct RecipeCardSuccessView: View {
    public let recipe: Recipe?
    public let isRecipeInCart: Bool
    public let isLikeEnabled: Bool
    public let showMealIdeaTag: Bool

    public let goToDetailsAction: () -> Void
    public let showOrAddRecipeAction: () -> Void
    
    var body: some View {
        if let template = Template.sharedInstance.recipeCardTemplate {
            template(recipe, isRecipeInCart, isLikeEnabled, showMealIdeaTag, goToDetailsAction,
                     showOrAddRecipeAction)
        } else {
            VStack() {
                ZStack(alignment: .topLeading) {
                    AsyncImage(
                        url: URL(
                            string: recipe?.attributes?.mediaUrl ?? ""
                        )! ,
                        height : 240
                    ).frame(height: 240)
                        .contentShape(Rectangle())
                        .onTapGesture {
                            goToDetailsAction()
                        }
                    HStack(alignment: .center) {
                        if (showMealIdeaTag) {
                            HStack(){
                                Image.miamImage(icon: .ideeRepas)
                                    .resizable()
                                    .scaledToFill()
                                    .frame(width: 24, height: 24)
                                Text(RecipeCardText.sharedInstance.recipeFlag)
                                    .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyMediumStyle)
                            }.padding(.horizontal,16)
                                .padding(.vertical,4)
                                .background(Color.miamColor(.musterd))
                                .cornerRadius(5).rotationEffect(Angle(degrees: -2.0))
                        }
                        Spacer()
                        if (isLikeEnabled) {
                            LikeButton(recipeId: recipe!.id)
                        }
                    }.padding([.leading,.trailing], 8).padding(.top, 16)
                }.frame(height: 240)
                Text(recipe?.attributes?.title ?? "")
                    .lineLimit(2)
                    .foregroundColor(Color.miamColor(.black))
                    .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleSmallStyle)
                    .padding(EdgeInsets(top: Dimension.sharedInstance.mlPadding,
                                        leading: Dimension.sharedInstance.lPadding,
                                        bottom: Dimension.sharedInstance.mlPadding,
                                        trailing: Dimension.sharedInstance.lPadding))
                
                HStack(alignment: .center, spacing: Dimension.sharedInstance.lPadding) {
                    IconWithText(icon: .clock, text: recipe?.totalTime ?? "")
                    Divider()
                    switch recipe?.attributes?.difficulty {
                    case 1: IconWithText(icon: .difficultyLow, text:  MiamText.sharedInstance.difficultyEasy)
                    case 2: IconWithText(icon: .difficultyMedium, text:  MiamText.sharedInstance.difficultyMid)
                    case 3: IconWithText(icon: .difficultyHigh, text:  MiamText.sharedInstance.difficultyHard)
                    default: IconWithText(icon: .difficultyLow, text: MiamText.sharedInstance.difficultyEasy)
                    }
                    
                }.padding(.bottom, Dimension.sharedInstance.mlPadding)
                
                Button {
                    showOrAddRecipeAction()
                } label: {
                    if !isRecipeInCart {
                        HStack(alignment: .center) {
                            Text(MiamText.sharedInstance.checkBasketPreview)
                            Image.miamImage(icon: .cart)
                        }
                    } else {
                        HStack(alignment: .center) {
                            Text(MiamText.sharedInstance.viewRecipeDetail)
                            Image.miamImage(icon: .check)
                        }
                    }
                }.foregroundColor(!isRecipeInCart ? Color.miamColor(.white) : Color.miamColor(.primary))
                    .frame(minHeight: 40.0, maxHeight: 40.0)
                    .padding(.horizontal, Dimension.sharedInstance.lPadding)
                    .background(!isRecipeInCart ? Color.miamColor(.primaryText) : Color.miamColor(.white))
                    .cornerRadius(25)
                    .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyBoldStyle)
                    .overlay(Capsule().stroke(Color.miamColor(.primary), lineWidth: 1.0))
                    .padding(.bottom, Dimension.sharedInstance.lPadding)
            }.cornerRadius(!showMealIdeaTag ? 15 : 0).clipped()
             .overlay(!showMealIdeaTag ? RoundedRectangle(cornerRadius: 15)
             .stroke(Color.miamColor(.border), lineWidth: 1) : nil)
             .overlay(showMealIdeaTag ?  Rectangle()
             .stroke(Color.miamColor(.border), lineWidth: 1) : nil)
        }
    }
}
