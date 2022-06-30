//
//  RecipePageListVM.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 24/06/2022.
//

import Foundation
import shared

class RecipeListPageVM: ObservableObject {
    @Published var recipes: [Recipe] = []
    @Published var title: String = ""

    let model: RecipeListPageViewModel

    init(model: RecipeListPageViewModel) {
        self.model = model
        self.model.collect(flow: model.uiState) { data in
            let state = data as! RecipeListPageContractState
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

    func loadPage() {
        self.model.setEvent(event: RecipeListPageContractEvent.LoadPage())
    }
}
