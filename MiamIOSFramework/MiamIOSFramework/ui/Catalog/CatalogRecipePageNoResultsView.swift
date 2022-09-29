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
    let searchString: String
    let browseCatalogAction: () -> Void
    var showingFavorites = false

    let noRecipeFoundText = "Oups, aucune recette n'a été trouvée"
    let tryAnotherSearchText = "Essayez une nouvelle recherche"
    let browseRecipesText = "Parcourir les idées repas"
    let addRecipeText = "Ajouter une idée repas"
    let noFavoritRecipeYet = "Oups, vous n'avez pas encore d'idée repas"

    public var body: some View {
        VStack(spacing: 32.0) {
            Image.miamImage(icon: .noResults)
            
            if showingFavorites {
                Text(noFavoritRecipeYet)
                    .fontWeight(.bold)
                    .font(.system(size: 24.0))
                    .multilineTextAlignment(.center)
                    .foregroundColor(Color.miamColor(.white))
                Button {
                    browseCatalogAction()
                } label: {
                    Text("\(browseRecipesText)").foregroundColor(Color.miamColor(.primary)).fontWeight(.semibold)
                    Image.miamImage(icon: .rightArrow)
                }
                .padding(EdgeInsets(top: 10, leading: 20, bottom: 10, trailing: 20))
                .background(Capsule().foregroundColor(.white))
                .overlay(Capsule().stroke(.white, lineWidth: 1.0))
            } else {
                Text("\(noRecipeFoundText) \"\(searchString)\"")
                    .fontWeight(.bold)
                    .font(.system(size: 24.0))
                    .multilineTextAlignment(.center)
                    .foregroundColor(Color.miamColor(.white))
                    Text("\(tryAnotherSearchText)")
                        .font(.system(size: 16.0))
                        .foregroundColor(Color.miamColor(.white))
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
