//
//  RecipeCardView.swift
//  MiamIOSFramework
//
//  Created by miam on 17/02/2022.
//

import SwiftUI
import shared

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
            ManagementResourceState<Recipe,RecipeCardSuccessView,RecipeCardLoadingView> (
                resourceState: viewModel.state!.recipeState,
                successView:  criteria != nil ? RecipeCardSuccessView(viewModel: viewModel, criteria: criteria!, showMealIdeaTag: showMealIdeaTag) :
                    RecipeCardSuccessView(viewModel: viewModel, recipeId: recipeId!, showMealIdeaTag: showMealIdeaTag) ,
                loadingView: RecipeCardLoadingView()
            ).onAppear(perform: {
                if(recipeId != nil){
                    viewModel.setEvent(
                        event: RecipeContractEvent.OnFetchRecipe(idRecipe: self.recipeId!))
                } else if (criteria != nil) {
                    viewModel.setEvent(
                        event: RecipeContractEvent.OnSetCriteria(crieria: self.criteria!))
                }
            }
            )
        }
    }
}
 
