//
//  MiamManager.swift
//  iosApp
//
//  Created by Miam on 19/04/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared


public class TestProduct {
var id :String;
var name: String;
var quantity :Int;
var price: Double?;
var identifier: String?;


    init (id: String, name: String, quantity: Int, price: Double, identifier: String)  {
      self.id = id
      self.name = name
      self.quantity = quantity
      self.price = price
      self.identifier = identifier
  }
    
    init (id: String, name: String, quantity: Int)  {
      self.id = id
      self.name = name
      self.quantity = quantity
      self.price = nil
      self.identifier = nil
  }
}


public class MiamManager {
    
  public static let sharedInstance = MiamManager()
  private  let availableStoreIdLists = ["454", "652"]
  private let basketHandler: BasketHandler = BasketHandler()
  
    
    //TODO test call in kotlin
    func isActiveOnStore() -> KotlinBoolean {
        return  KotlinBoolean(value: availableStoreIdLists.contains("35290"))
    }
    
  // need to be private
  private init() {
      KoinKt.doInitKoin()
      PointOfSaleHandler.shared.updateStoreId(storeId: "35290")
      PointOfSaleHandler.shared.setSupplierOrigin(origin:"www.coursesu.fr")
      PointOfSaleHandler.shared.setSupplier(supplierId: 7)
      PointOfSaleHandler.shared.isAvailable = isActiveOnStore
      UserHandler.shared.updateUserId(userId: "ed0a471a4bdc755664db84068119144b3a1772d8a6911057a0d6be6a3e075120")
      basketHandler.listenToRetailerBasket = initBasketListener
      basketHandler.pushProductsToBasket = pushProductToBasket
      basketHandler.paymentTotal = getTotalPayment
     
  }
    
    private func yourProductsToRetailerProducts(products: Array<TestProduct>) -> Array<RetailerProduct> {
      return products.map {
       return RetailerProduct(
            retailerId: $0.id,
            quantity: Int32($0.quantity),
            name: $0.name,
            productIdentifier: nil
        )
      }
    }
    
    private func pushProductToBasket(products: Array<RetailerProduct>){
        retailerProductsToYourProducts(products: products).forEach( {
            if($0.quantity == 0){
                AppBasket.sharedInstance.basket.remove(removedProduct: $0)
            } else {
                AppBasket.sharedInstance.basket.add(addedProduct: $0)
            }
          }
        )
    }
    
    private func retailerProductsToYourProducts(products: Array<RetailerProduct>) -> Array<TestProduct> {
      return products.map {
        return   TestProduct(
            id: $0.retailerId,
            name: "\($0.name)",
            quantity: Int($0.quantity)
        )
      }
    }
    
    private func initBasketListener(
        callback: @escaping ([RetailerProduct]) -> KotlinUnit
    ) {
        AppBasket.sharedInstance.basket.$items.sink {
         print($0)
         // callback will be triggered on every basket change
             callback(
                self.yourProductsToRetailerProducts(products: AppBasket.sharedInstance.basket.items )
             )
       }
     }
    
    private func getTotalPayment() -> KotlinDouble {
        return 2.0
      }
}

