//
//  TabbedView.swift
//  iosApp
//
//  Created by Vincent Kergonna on 19/07/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import MiamIOSFramework

struct TabbedView: View {
    let applicationBasket: MyBasket = MyBasket.shared
    let foregroundTabItemColor = UIColor(red: 0.00, green: 0.49, blue: 0.57, alpha: 1.00)

    init() {
        UITabBar.appearance().tintColor = foregroundTabItemColor
    }

    var body: some View {
        TabView {
            if #available(iOS 15, *) {
                MealIdeas()
                    .tabItem {
                        Label("Idées repas", systemImage: "questionmark.square")
                    }
            } 
            MealsView()
                .tabItem {
                    Label("Mes repas", systemImage: "fork.knife.circle")
                }
            MyBasketView()
                .tabItem {
                    Label("Mon panier", systemImage: "cart.fill")
            }
            CatalogTabView().tabItem {
                Label("Catalog", systemImage: "book.fill")
            }
            MyFavoritesView()
                .tabItem {
                    Label("Favorites", systemImage: "heart.fill")
            }

        }
    }
}

struct TabbedView_Previews: PreviewProvider {
    static var previews: some View {
        TabbedView()
    }
}
