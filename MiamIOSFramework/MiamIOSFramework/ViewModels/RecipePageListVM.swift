//
//  RecipePageListVM.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 24/06/2022.
//

import Foundation
import miamCore

@available(iOS 14, *)
public class RecipeListPageVM: ObservableObject {
    @Published public var recipes: [Recipe] = []
    @Published public var title: String = ""
    @Published public var state: RecipeListPageContractState?

    let model: RecipeListPageViewModel
    let filterVM = FilterViewModelInstance.shared.instance

    public init() {
        self.model = RecipeListPageViewModel()
        self.model.setEvent(event: RecipeListPageContractEvent.InitPage(title: ""))
        initStateManagment()
    }
    
    public init(categoriesId :String, title: String){
        self.model = RecipeListPageViewModel()
        filterVM.setCat(catId: categoriesId)
        self.model.setEvent(event: RecipeListPageContractEvent.InitPage(title: ""))
        initStateManagment()
    }
    
    private func initStateManagment(){
        self.model.collect(flow: model.uiState) { data in
            let state = data as! RecipeListPageContractState
            self.state = state
            self.title = state.title
            switch(state.recipes) {
            case let success as BasicUiStateSuccess<NSArray>:
                if let recipes = success.data as? [Recipe] {
                    self.recipes = recipes
                }
            default:
                ()
            }
        }
    }

    public var hasNoResults: Bool {
        return self.model.currentState.noMoreData && recipes.isEmpty
    }

    public func loadMoreContent(currentRecipe: Recipe) {
        guard !self.model.currentState.isFetchingNewPage, !self.model.currentState.noMoreData else {
            return
        }

        let thresholdIndex = recipes.index(recipes.endIndex, offsetBy: -3)
        if recipes.firstIndex(where: {$0.id == currentRecipe.id}) == thresholdIndex {
            loadPage()
        }
    }

    public func loadPage() {
        self.model.setEvent(event: RecipeListPageContractEvent.LoadPage())
    }
}
