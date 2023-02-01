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
    public var criteria: SuggestionsCriteria?
    public var recipeId: String?
    public var recipe: Recipe?
    let recipeCardHeight: CGFloat
    private let showMealIdeaTag: Bool
    @ObservedObject var viewModel: RecipeCardVM = RecipeCardVM(routerVM: RouterOutletViewModel())

    @SwiftUI.State private var initialDialogScreen = RouterContent.recipeDetail
    @SwiftUI.State var showingPopup = false
    @SwiftUI.State private var showBasketPreview = false

    public init( criteria: SuggestionsCriteria, showMealIdeaTag: Bool = true, recipeCardHeight: CGFloat = 400.0) {
        self.criteria = criteria
        self.showMealIdeaTag = showMealIdeaTag
        self.recipeCardHeight = recipeCardHeight
    }

    public init(recipeId: String, showMealIdeaTag: Bool = true, recipeCardHeight: CGFloat = 400.0) {
        self.recipeId = recipeId
        self.showMealIdeaTag = showMealIdeaTag
        self.recipeCardHeight = recipeCardHeight
    }

    public init(recipe: Recipe, showMealIdeaTag: Bool = true, recipeCardHeight: CGFloat = 400.0) {
        self.recipe = recipe
        self.showMealIdeaTag = showMealIdeaTag
        self.recipeCardHeight = recipeCardHeight
    }

    public var body: some View {
        ManagementResourceState<Recipe, RecipeCardSuccessView, RecipeCardLoadingView, RecipeCardEmptyView>(
            resourceState: viewModel.state?.recipeState,
            successView: RecipeCardSuccessView(recipe: viewModel.recipe,
                                                isRecipeInCart: viewModel.currentState.isInCart,
                                                isLikeEnabled: viewModel.isLikeEnabled,
                                                showMealIdeaTag: showMealIdeaTag,
                                                goToDetailsAction: {
                                                    viewModel.goToDetail()
                                                    showingPopup = true
                                                }, showOrAddRecipeAction: {
                                                    if viewModel.isInCart {
                                                        viewModel.goToDetail()
                                                        showBasketPreview = false
                                                    } else {
                                                        addToCart()
                                                        showBasketPreview = true
                                                    }
                                                    showingPopup = true

                                                }),
            loadingView: RecipeCardLoadingView(),
            emptyView: RecipeCardEmptyView()
        ).onAppear(perform: {
            if let recipeId = self.recipeId {
                viewModel.fetchRecipe(recipeId: recipeId)
            } else if let criteria = self.criteria {
                viewModel.setRecipeFromSuggestion(criteria: criteria)
            } else if let recipe = self.recipe {
                viewModel.setRecipe(recipe: recipe)
            }
        }).frame(height: recipeCardHeight)
            .sheet(isPresented: $showingPopup) {
                if let recipeId = viewModel.recipeId {
                    RecipeModal(recipeId: recipeId, showBasketPreview: showBasketPreview) {
                        showingPopup = false
                        showBasketPreview = false
                    }
                }
            }
    }

    private func addToCart() {
        viewModel.setEvent(event: RecipeContractEvent.OnAddRecipe())
//        viewModel.routerVM.setEvent(event: RouterOutletContractEvent.GoToPreview(recipeId: viewModel.recipe?.id ?? "", vm: viewModel))
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
