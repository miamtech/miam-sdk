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
    
    public init( criteria: SuggestionsCriteria, showMealIdeaTag: Bool = true) {
        self.criteria = criteria
        self.showMealIdeaTag = showMealIdeaTag
    }
    
    public init(recipeId: String, showMealIdeaTag: Bool = true) {
        self.recipeId = recipeId
        self.showMealIdeaTag = showMealIdeaTag
    }
    
    public var body: some View {
        if(viewModel.state != nil ){
            ManagementResourceState<Recipe,RecipeCardSuccessView,RecipeCardLoadingView,RecipeCardEmptyView> (
                resourceState: viewModel.state!.recipeState,
                successView:  criteria != nil ? RecipeCardSuccessView(viewModel: viewModel, criteria: criteria!, showMealIdeaTag: showMealIdeaTag) :
                    RecipeCardSuccessView(viewModel: viewModel, recipeId: recipeId!, showMealIdeaTag: showMealIdeaTag) ,
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
    }
}

@available(iOS 14, *)
public struct RecipeCardEmptyView: View {
    public var body: some View {
        HStack{}
    }
}
 
