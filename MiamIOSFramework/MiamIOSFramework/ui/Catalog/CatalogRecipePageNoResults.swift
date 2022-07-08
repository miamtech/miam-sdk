//
//  CatalogRecipePageNoResults.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 04/07/2022.
//

import SwiftUI
import shared

@available(iOS 14, *)
public struct CatalogRecipePageNoResultsView: View {
    let catalogViewModel: CatalogVM
    private var showingFavorites = false

    let noRecipeFoundText = "Oups, aucune recette n'a été trouvée pour"
    let tryAnotherSearchText = "Essayez une nouvelle recherche"
    let browseRecipesText = "Parcourir les idées repas"
    let addRecipeText = "Ajouter une idée repas"

    public init(catalogViewModel: CatalogVM, showingFavorites: Bool = false) {
        self.catalogViewModel = catalogViewModel
        self.showingFavorites = showingFavorites
    }

    public var body: some View {
        VStack(spacing: 32.0) {
            Image("no-results", bundle: Bundle(for: CatalogVM.self))
            Text("\(noRecipeFoundText) \"\(catalogViewModel.searchString)\"")
                .fontWeight(.bold)
                .font(.system(size: 24.0))
                .multilineTextAlignment(.center)
                .foregroundColor(MiamColor.sharedInstance.white)

            if !showingFavorites {
                Text("\(tryAnotherSearchText)")
                    .font(.system(size: 16.0))
                    .foregroundColor(MiamColor.sharedInstance.white)
            }

            if showingFavorites {
                Button {
                    catalogViewModel.setEvent(event: CatalogContractEvent.GoToDefault())
                } label: {
                    Text("\(browseRecipesText)").foregroundColor(MiamColor.sharedInstance.primary).fontWeight(.semibold)
                    Image("right_arrow", bundle: Bundle(for: CatalogVM.self))
                }
                .padding(EdgeInsets(top: 10, leading: 20, bottom: 10, trailing: 20))
                .background(Capsule().foregroundColor(.white))
                .overlay(Capsule().stroke(.white, lineWidth: 1.0))

                Button {
                    // TODO: Create recipe
                } label: {
                    HStack {
                        Image("plus", bundle: Bundle(for: CatalogVM.self))
                        Text("\(addRecipeText)").foregroundColor(.white).fontWeight(.semibold)
                    }
                    .padding(EdgeInsets(top: 10, leading: 25, bottom: 10, trailing: 25))
                    .overlay(Capsule().stroke(.white, lineWidth: 1.0))
                }
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .padding(10)
        .background(MiamColor.sharedInstance.backgroundDark)
    }
}

@available(iOS 14, *)
public struct CatalogRecipePageNoFavoritesView: View {
    public var body: some View {
        VStack {
            Image("no-results", bundle: Bundle(for: CatalogVM.self))

        }
    }
}
