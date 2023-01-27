//
//  CatalogCategoriesView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 26/01/2023.
//  Copyright © 2023 Miam. All rights reserved.
//

import SwiftUI
import miamCore
@available(iOS 14, *)
public struct CatalogCategoriesView: View {
    @StateObject var catalog: CatalogVM = CatalogVM()

    let recipeCardHeight: Double
    let goToCategory: (CatalogPackage) -> Void
    
    public init(recipeCardHeight: Double = 400.0, goToCategory: @escaping (CatalogPackage) -> Void) {
        self.recipeCardHeight = recipeCardHeight
        self.goToCategory = goToCategory
    }
    
    public var body: some View {
        if let catalogState = catalog.state {
            ManagementResourceState<NSArray, CatalogCategoriesSuccessView, CatalogLoadingView, CatalogEmptyView>(
                resourceState: catalogState.categories,
                successView: CatalogCategoriesSuccessView(packages: catalog.packages, navigateToCategory: { category in
                    goToCategory(category)
                }, recipeCardHeight: 400.0),
                
                loadingView: CatalogLoadingView(loadingText: MiamText.sharedInstance.simmering),
                emptyView: CatalogEmptyView())
            .frame(maxWidth: .infinity, maxHeight: .infinity)
        }
    }
}

@available(iOS 14, *)
struct CatalogCategoriesView_Previews: PreviewProvider {
    static var previews: some View {
        CatalogCategoriesView(goToCategory: {_ in })
    }
}
