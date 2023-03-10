//
//  RecipeCardView.swift
//  MiamIOSFramework
//
//  Created by miam on 17/02/2022.
//

import SwiftUI
import miamCore

@available(iOS 14, *)
public struct RecipeCardView: View {

    public var recipeId: String?

    @ObservedObject var viewModel: RecipeCardVM = RecipeCardVM()

    @SwiftUI.State private var initialDialogScreen = RouterContent.recipeDetail

    public init( criteria: SuggestionsCriteria, showMealIdeaTag: Bool = true, recipeCardHeight: CGFloat = 400.0) {
        self.criteria = criteria
        self.showMealIdeaTag = showMealIdeaTag
        self.recipeCardHeight = recipeCardHeight
    }

    public init(recipeId: String, ) {
        self.recipeId = recipe
    }

    public var body: some View {
        ManagementResourceState<Recipe, RecipeCardSuccessView, RecipeCardLoadingView, RecipeCardEmptyView>(
            resourceState: viewModel.state?.recipeState,
            successView: ,
            loadingView: RecipeCardLoadingView(),
            emptyView: RecipeCardEmptyView()
        ).onAppear(perform: {
           //TODO
        })
    }

}

@available(iOS 14, *)
public struct RecipeCardEmptyView: View {
    public var body: some View {
        VStack {
            ZStack(alignment: .topLeading) {
                Rectangle()
                    .fill(Color.miamColor(.border))
                    .frame(height: 245)

            }.frame(height: 245)
            Text( "")
                .lineLimit(2)
                .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleMediumStyle)
                .foregroundColor(Color.miamColor(.black))
                .padding(Dimension.sharedInstance.lPadding)
            Text( "")
                .lineLimit(2)
                .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleMediumStyle)
                .foregroundColor(Color.miamColor(.black))
                .padding(Dimension.sharedInstance.sPadding)

            Rectangle()
                .fill(Color.miamColor(.border)).opacity(0.1)
                .frame(minHeight: 50.0, maxHeight: 50.0)
                .frame(width: 180)
                .padding(.horizontal, Dimension.sharedInstance.lPadding)
                .background(Color.miamColor(.border))
                .cornerRadius(25)
                .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleMediumStyle)
                .padding(.bottom, Dimension.sharedInstance.lPadding)

        }
    }
}
