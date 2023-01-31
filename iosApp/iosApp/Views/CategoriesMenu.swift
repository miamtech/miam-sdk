//
//  CategoriesMenu.swift
//  iosApp
//
//  Created by Miam on 27/09/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import miamCore

@available(iOS 15, *)
struct CategoriesMenu: View {

    @ObservedObject var categorie: Categories

    var body: some View {
        Menu("categories") {
            ForEach(categorie.categoriesList, id: \.self) { cat in
                Button(cat.title, action: {})
            }
        }
    }
}
