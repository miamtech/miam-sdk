//
//  MyProduct.swift
//  iosApp
//
//  Created by Vincent Kergonna on 08/06/2022.
//  Copyright © 2022 Miam. All rights reserved.
//

import Foundation

public class MyProduct: Identifiable {
    public var id: String
    var name: String
    var quantity: Int
    var price: Double?
    var identifier: String?

    init (id: String, name: String, quantity: Int, price: Double, identifier: String) {
      self.id = id
      self.name = name
      self.quantity = quantity
      self.price = price
      self.identifier = identifier
  }

    init (id: String, name: String, quantity: Int) {
      self.id = id
      self.name = name
      self.quantity = quantity
      self.price = nil
      self.identifier = nil
  }
}
