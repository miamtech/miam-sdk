//
//  MyBasket.swift
//  iosApp
//
//  Created by Vincent Kergonna on 08/06/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation

class MyBasket: ObservableObject {
    @Published var items = [MyProduct]()

    init(items: Array<MyProduct>) {
        self.items = items
    }

    func add(addedProduct: MyProduct) -> Void {
        if let existingProductIndex = items.firstIndex(where: { $0.id.isEqual(addedProduct.id) }) {
            let product = items[existingProductIndex]
            product.quantity += 1
        } else {
            items.append(addedProduct)
        }
    }

    func remove(removedProduct : MyProduct){
        guard let productIndex = items.firstIndex(where: { $0.id.isEqual(removedProduct.id) } ) else {
            return
        }
        items.remove(at: productIndex)
    }
}
