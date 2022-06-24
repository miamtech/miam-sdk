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

    init(model: RecipeListPageViewModel) {
        model.collect(flow: model.uiState) { data in
            switch(data) {
            case let success as BasicUiStateSuccess<NSArray>:
                self.recipes = success.data as? [Recipe] ?? []
            default:
                break
            }
        }
    }
}
