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
    private let showMealIdeaTag: Bool
    @ObservedObject var viewModel: RecipeCardVM = RecipeCardVM(routerVM: RouterOutletViewModel())
    
    @SwiftUI.State private var initialDialogScreen = RouterContent.recipeDetail
    @SwiftUI.State var showingPopup = false
    
    public init( criteria: SuggestionsCriteria, showMealIdeaTag: Bool = true) {
        self.criteria = criteria
        self.showMealIdeaTag = showMealIdeaTag
    }
    
    public init(recipeId: String, showMealIdeaTag: Bool = true) {
        self.recipeId = recipeId
        self.showMealIdeaTag = showMealIdeaTag
    }
    
    public var body: some View {
        if #available(iOS 15.0, *) {
            let _ = Self._printChanges()
        } else {
            // Fallback on earlier versions
        }
        VStack {
            if(viewModel.state != nil ){
                ManagementResourceState<Recipe, RecipeCardSuccessView, RecipeCardLoadingView, RecipeCardEmptyView> (
                    resourceState: viewModel.state!.recipeState,
                    successView:  RecipeCardSuccessView(recipe: viewModel.recipe,
                                                        isRecipeInCart: viewModel.currentState.isInCart,
                                                        isLikeEnabled: viewModel.isLikeEnabled,
                                                        isLiked: viewModel.currentState.isLiked,
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
                                                        }, toggleLikeAction: {
                                                            viewModel.toggleLike()
                                                        }),
                    loadingView: RecipeCardLoadingView(),
                    emptyView: RecipeCardEmptyView()
                ).onAppear(perform: {
                    if(recipeId != nil){
                        viewModel.fetchRecipe(recipeId: self.recipeId!)
                    } else if (criteria != nil) {
                        viewModel.setRecipeFromSuggestion(criteria: self.criteria!)
                    }
                })
            }
        }.frame(height: 400)
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
 
