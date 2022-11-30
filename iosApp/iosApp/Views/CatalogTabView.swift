//
//  catalogTabView.swift
//  iosApp
//
//  Created by Miam on 27/07/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import MiamIOSFramework

struct CatalogTabView: View {
    var body: some View {
        VStack{
            ZStack(alignment: .bottom) {
                CatalogView(usesPreferences: true,recipesListColumns: 2)
                MyMealButtonView()
            }
        }
    }
}
