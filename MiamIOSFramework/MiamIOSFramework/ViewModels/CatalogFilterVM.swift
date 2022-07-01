//
//  CatalogFilterVM.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 01/07/2022.
//

import Foundation
import shared

class CatalogFilterVM: ObservableObject {
    let model: CatalogFilterViewModel

    @Published var numberOfRecipes: Int = 0
    @Published var difficulty: Array<CatalogFilterOptions> = []
    @Published var cost: Array<CatalogFilterOptions> = []
    @Published var time: Array<CatalogFilterOptions> = []

    init(model: CatalogFilterViewModel) {
        self.model = model
        self.model.collect(flow: model.uiState) { data in
            let state = data as! CatalogFilterContractState
            self.numberOfRecipes = Int(state.numberOfResult)
            self.difficulty = state.difficulty
            self.cost = state.cost
            self.time = state.time
        }
    }
}
