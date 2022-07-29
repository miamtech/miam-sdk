//
//  CatalogRecipePageNoResults.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 04/07/2022.
//

import SwiftUI
import miamCore

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
            Image.miamImage(icon: .noResults)
            Text("\(noRecipeFoundText) \"\(catalogViewModel.searchString)\"")
                .fontWeight(.bold)
                .font(.system(size: 24.0))
                .multilineTextAlignment(.center)
                .foregroundColor(Color.miamColor(.white))

            if !showingFavorites {
                Text("\(tryAnotherSearchText)")
                    .font(.system(size: 16.0))
                    .foregroundColor(Color.miamColor(.white))
            }

            if showingFavorites {
                Button {
                    catalogViewModel.setEvent(event: CatalogContractEvent.GoToDefault())
                } label: {
                    Text("\(browseRecipesText)").foregroundColor(Color.miamColor(.primary)).fontWeight(.semibold)
                    Image.miamImage(icon: .rightArrow)
                }
                .padding(EdgeInsets(top: 10, leading: 20, bottom: 10, trailing: 20))
                .background(Capsule().foregroundColor(.white))
                .overlay(Capsule().stroke(.white, lineWidth: 1.0))

                Button {
                    // TODO: Create recipe
                } label: {
                    HStack {
                        Image.miamImage(icon: .plus)
                        Text("\(addRecipeText)").foregroundColor(.white).fontWeight(.semibold)
                    }
                    .padding(EdgeInsets(top: 10, leading: 25, bottom: 10, trailing: 25))
                    .overlay(Capsule().stroke(.white, lineWidth: 1.0))
                }
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .padding(10)
        .background(Color.miamColor(.primaryDark))
    }
}

@available(iOS 14, *)
public struct CatalogRecipePageNoFavoritesView: View {
    public var body: some View {
        VStack {
            Image.miamImage(icon: .noResults)
        }
    }
}
