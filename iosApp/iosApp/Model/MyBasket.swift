//
//  MyBasket.swift
//  iosApp
//
//  Created by Vincent Kergonna on 08/06/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation

class MyBasket: ObservableObject {
    static let shared = MyBasket(items: [ MyProduct(id:"970417",name:"Beurre doux U, 125",quantity:1, price:2.12, identifier: "id_970417"),
                                          MyProduct(id:"42851844",name:"Curry tradition en poudre DUCROS, 53g",quantity:1, price:3.40, identifier: "id_6511680")])
    @Published var items = [MyProduct]()

    private init(items: Array<MyProduct>) {
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
