//
//  CatalogCategoriesSuccessView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 26/01/2023.
//  Copyright © 2023 Miam. All rights reserved.
//

import SwiftUI
import miamCore

@available(iOS 14, *)
struct CatalogCategoriesSuccessView: View {
    let packages: [CatalogPackage]
    let navigateToCategory: (CatalogPackage) -> Void
    let recipeCardHeight: Double
    
    var body: some View {
        ScrollView {
            VStack {
                ForEach(packages) { package in
                    CatalogPackageRow(package: package, recipeCardHeight: recipeCardHeight) { package in
                        navigateToCategory(package)
                    }
                }
            }.padding([.top], Dimension.sharedInstance.lPadding)
        }
    }
}

@available(iOS 14, *)
struct CatalogCategoriesSuccessView_Previews: PreviewProvider {
    static var previews: some View {
        CatalogCategoriesSuccessView(packages: [], navigateToCategory: {_ in }, recipeCardHeight: 400.0)
    }
}
