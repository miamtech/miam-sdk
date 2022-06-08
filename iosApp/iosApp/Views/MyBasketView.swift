//
//  MyBasketView.swift
//  iosApp
//
//  Created by Vincent Kergonna on 08/06/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI

struct MyBasketView: View {
    @ObservedObject var basket: MyBasket = MyBasket.shared

    var body: some View {
        List{
            ForEach(basket.items) { product in
                BasketItemView(product: product)
            }
        }.frame(height: 150.0, alignment: .topLeading)
    }
}

struct BasketItemView: View {
    let product: MyProduct

    var body: some View {
        HStack {
            Text(product.name)
            Spacer()
            Text("x\(product.quantity)").bold()
        }
    }
}
