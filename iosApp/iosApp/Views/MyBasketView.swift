//
//  MyBasketView.swift
//  iosApp
//
//  Created by Vincent Kergonna on 08/06/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI

import miamCore
import MiamIOSFramework

struct MyBasketView: View {
    private let productsRepository = MyProductsRepository()

    @ObservedObject var basket: MyBasket = MyBasket.shared
    var body: some View {
        NavigationView {
            VStack {
                HStack {
                    RecipeCarouselView(productId: "7345", numberOfResults: 4)
                }
                List {
                    ForEach(basket.items) { product in
                        BasketItemView(product: product)
                    }
                }.frame(alignment: .topLeading)

                VStack {
                    HStack(spacing: 32.0) {
                        Button {
                            addRandomProduct()
                        } label: {
                            Label("Ajouter", systemImage: "plus.square.fill")
                        }
                        Button {
                            removeRandomProduct()
                        } label: {
                            Label("Retirer", systemImage: "minus.square.fill")
                        }
                    }
                }
                .padding(16.0)
                .navigationTitle("Mon panier").navigationBarTitleDisplayMode(.inline)
            }
        }
    }

    private func addRandomProduct() {
        if let product = MyProductsRepository.sharedInstance.getRandomProduct() {
            basket.add(addedProduct: product)
        }
    }

    private func removeRandomProduct() {
        guard let product = basket.items.randomElement() else {
            return
        }
        basket.remove(removedProduct: product)
    }
}

struct BasketItemView: View {
    let product: MyProduct

    var body: some View {
        VStack {
            HStack(spacing: 16.0) {
                Button {
                    MyBasket.shared.remove(removedProduct: product)
                } label: {
                    Image(systemName: "trash.slash.fill").foregroundColor(Color.red)
                }
                Text(product.name)
                Spacer()
                Text("x\(product.quantity)").bold()
            }
            HStack {
                BasketTagView(itemId: product.id)
            }
        }
    }
}
