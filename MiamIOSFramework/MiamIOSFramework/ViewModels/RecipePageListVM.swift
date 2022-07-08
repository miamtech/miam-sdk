//
//  RecipePageListVM.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 24/06/2022.
//

import Foundation
import shared

@available(iOS 14, *)
class RecipeListPageVM: ObservableObject {
    @Published var recipes: [Recipe] = []
    @Published var title: String = ""
    @Published var state: RecipeListPageContractState?

    let model: RecipeListPageViewModel

    init(model: RecipeListPageViewModel) {
        self.model = model
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

    var hasNoResults: Bool {
        return self.model.currentState.noMoreData && recipes.isEmpty
    }

    func loadMoreContent(currentRecipe: Recipe) {
        guard !self.model.currentState.isFetchingNewPage, !self.model.currentState.noMoreData else {
            return
        }

        let thresholdIndex = recipes.index(recipes.endIndex, offsetBy: -3)
        if recipes.firstIndex(where: {$0.id == currentRecipe.id}) == thresholdIndex {
            loadPage()
        }
    }

    private func loadPage() {
        self.model.setEvent(event: RecipeListPageContractEvent.LoadPage())
    }
}
