//
//  MealsView.swift
//  iosApp
//
//  Created by Vincent Kergonna on 19/07/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import MiamIOSFramework

struct MealsView: View {
    @State var groceriesList: Groceries = Groceries()
    var body: some View {

        NavigationView {
            MyMealsView()
                .navigationTitle("Mes repas (\(groceriesList.numberOfRecipes))").navigationBarTitleDisplayMode(.inline)
                .toolbar(content: {
                    Button {
                        groceriesList.reset()
                    } label: {
                        Label("Vider", systemImage: "trash.slash.fill").foregroundColor(Color.red)
                    }
                })
        }.onAppear {
            groceriesList.refreshRecipeCount()
        }
    }
}

struct MealsView_Previews: PreviewProvider {
    static var previews: some View {
        MealsView()
    }
}
