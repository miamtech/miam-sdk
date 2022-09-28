//
//  FavoritesView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 23/09/2022.
//

import SwiftUI
import miamCore

@available(iOS 14, *)
public struct FavoritesView: View {
    let favoritesViewModel = FavoritesVM()
    
    public init() {}
    
    public var body: some View {
        if let currentState = favoritesViewModel.state {
            ManagementResourceState<NSArray, FavoritesSuccessView, ProgressLoader, FavoritesEmptyView>(
                resourceState: currentState.favoritesRecipes,
                successView: FavoritesSuccessView(favorites: favoritesViewModel.favorites,
                                                  loadMoreRecipes: { favoritesViewModel.setEvent(event: FavoritePageContractEvent.LoadPage()) }),
                loadingView: ProgressLoader(color: .primary),
                emptyView: FavoritesEmptyView(browseCatalogAction: { }))
        }
    }
}
