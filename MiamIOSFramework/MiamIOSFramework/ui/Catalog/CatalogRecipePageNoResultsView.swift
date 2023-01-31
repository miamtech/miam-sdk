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
    let searchString: String = FilterViewModelInstance.shared.instance.currentState.searchString ?? ""
    let browseCatalogAction: (() -> Void)?
    var showingFavorites = false

    public var body: some View {
        if let template = Template.sharedInstance.catalogRecipePageNoResultsViewTemplate {
            template(searchString, browseCatalogAction, showingFavorites)
        } else {
            VStack(spacing: 32.0) {
                Image.miamImage(icon: .noResults)
                
                if showingFavorites {
                    Text(MiamText.sharedInstance.noFavoritRecipeYet)
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleBigStyle)
                        .multilineTextAlignment(.center)
                        .foregroundColor(Color.miamColor(.white))
                    Button {
                        if let browseCatalogAction {
                            browseCatalogAction()
                        }
                    } label: {
                        Text("\(MiamText.sharedInstance.browseRecipesText)")
                            .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyBigBoldStyle)
                            .foregroundColor(Color.miamColor(.primary))
                        Image.miamImage(icon: .rightArrow)
                    }
                    .padding(EdgeInsets(top: 10, leading: 20, bottom: 10, trailing: 20))
                    .background(Capsule().foregroundColor(.white))
                    .overlay(Capsule().stroke(.white, lineWidth: 1.0))
                } else {
                    Text("\(MiamText.sharedInstance.noRecipeFoundText) \"\(searchString)\"")
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleBigStyle)
                        .multilineTextAlignment(.center)
                        .foregroundColor(Color.miamColor(.white))
                    Text("\(MiamText.sharedInstance.tryAnotherSearchText)")
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyBigStyle)
                        .foregroundColor(Color.miamColor(.white))
                }
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            .padding(10)
            .background(Color.miamColor(.primaryDark))
        }
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
