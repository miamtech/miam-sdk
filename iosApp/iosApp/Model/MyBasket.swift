//
//  MyBasket.swift
//  iosApp
//
//  Created by Vincent Kergonna on 08/06/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation

class MyBasket {
    @Published var items = [MyProduct]()

    init(items: Array<MyProduct>) {
        self.items = items
    }

    func add(addedProduct: MyProduct) -> Void {

        let results = items.firstIndex(where: { $0.id.isEqual(addedProduct.id) }  )

        if(results != nil ){
            // TODO update quantity
        } else {
            items.append(addedProduct)
            self.items = items
        }
    }

    func remove(removedProduct : MyProduct){
        let results = items.firstIndex(where: { $0.id.isEqual(removedProduct.id) } )
        if(results == nil ){ return }
        items.remove(at: results!)
    }
}
