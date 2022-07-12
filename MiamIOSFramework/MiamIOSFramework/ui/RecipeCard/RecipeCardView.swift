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
    
    @ObservedObject var viewModel: RecipeCardVM = RecipeCardVM(routerVM: RouterOutletViewModel())
    
    public init( criteria: SuggestionsCriteria) {
        self.criteria = criteria
    }
    
    public init(recipeId: String) {
        self.recipeId = recipeId
    }
    
    public var body: some View {
        if(viewModel.state != nil ){
            ManagementResourceState<Recipe,RecipeCardSuccessView,RecipeCardLoadingView> (
                resourceState: viewModel.state!.recipeState,
                successView:  criteria != nil ? RecipeCardSuccessView(viewModel: viewModel, criteria: criteria!) :
                    RecipeCardSuccessView(viewModel: viewModel, recipeId: recipeId!) ,
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
 
