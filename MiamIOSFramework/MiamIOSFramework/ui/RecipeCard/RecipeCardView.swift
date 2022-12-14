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
        ManagementResourceState<Recipe, RecipeCardSuccessView, RecipeCardLoadingView, RecipeCardEmptyView> (
            resourceState: viewModel.state?.recipeState ,
            successView:  RecipeCardSuccessView(recipe: viewModel.recipe,
                                                isRecipeInCart: viewModel.currentState.isInCart,
                                                isLikeEnabled: viewModel.isLikeEnabled,
                                                showMealIdeaTag: showMealIdeaTag,
                                                goToDetailsAction: {
                                                    viewModel.goToDetail()
                                                    showingPopup = true
                                                }, showOrAddRecipeAction: {
                                                    if viewModel.isInCart {
                                                        viewModel.goToDetail()
                                                    } else {
                                                        addToCart()
                                                    }
                                                    showingPopup = true
                                                }),
            loadingView: RecipeCardLoadingView(),
            emptyView: RecipeCardEmptyView()
        ).onAppear(perform: {
            if(recipeId != nil){
                viewModel.fetchRecipe(recipeId: self.recipeId!)
            } else if (criteria != nil) {
                viewModel.setRecipeFromSuggestion(criteria: self.criteria!)
            } else if ( recipe != nil){
                if let  currentRecipe = recipe {
                    viewModel.setRecipe(recipe: currentRecipe)
                }
            }
        }).frame(height: recipeCardHeight)
            .sheet(isPresented: $showingPopup) {
                Dialog(
                    close: { showingPopup = false },
                    initialRoute : initialDialogScreen,
                    routerVm: viewModel.routerVM
                )
            }   
    }
    
    private func addToCart() {
        viewModel.setEvent(event: RecipeContractEvent.OnAddRecipe())
        viewModel.routerVM.setEvent(event: RouterOutletContractEvent.GoToPreview(recipeId: viewModel.recipe?.id ?? "", vm: viewModel))
    }
}

@available(iOS 14, *)
public struct RecipeCardEmptyView: View {
    public var body: some View {
        HStack{}
    }
}
