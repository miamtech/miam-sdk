//
//  File.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 23/09/2022.
//

import Foundation
import miamCore

@available(iOS 14, *)
class FavoritesVM: FavoritePageViewModel, ObservableObject {
    var favorites: [Recipe] = []
    @Published var state: FavoritePageContractState?
    override init() {
        super.init()
        collect(flow: uiState) { state in
            guard let state = state as? FavoritePageContractState else {
                return
            }
            self.state = state
            switch state.favoritesRecipes {
            case let success as BasicUiStateSuccess<NSArray>:
                if let results = success.data as? [Recipe] {
                    self.favorites = results
                }
                break
            default:
                break
            }
        }
    }
}
