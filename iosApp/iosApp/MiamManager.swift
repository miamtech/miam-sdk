//
//  MiamManager.swift
//  iosApp
//
//  Created by Miam on 19/04/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared
import MiamIOSFramework
import SwiftUI


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
  private let availableStoreIdLists = ["454", "652"]
  private let basketHandler: BasketHandler
  
  func isActiveOnStore() -> KotlinBoolean {
    return  KotlinBoolean(value: availableStoreIdLists.contains("35290"))
  }
    
  // need to be private
  private init() {
      KoinKt.doInitKoin()
      basketHandler = BasketHandlerInstance.shared.instance
      basketHandler.setListenToRetailerBasket(func: initBasketListener)
      basketHandler.setPushProductsToRetailerBasket(func: pushProductToBasket)
      basketHandler.pushProductsToMiamBasket(retailerBasket: [])
      PointOfSaleHandler.shared.updateStoreId(storeId: "35290")
      PointOfSaleHandler.shared.setSupplierOrigin(origin:"www.coursesu.fr")
      PointOfSaleHandler.shared.setSupplier(supplierId: 7)
      PointOfSaleHandler.shared.isAvailable = isActiveOnStore
      UserHandler.shared.updateUserId(userId: "ed0a471a4bdc755664db84068119144b3a1772d8a6911057a0d6be6a3e075120")
      //initCustomText()
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
       
    ) {
        AppBasket.sharedInstance.basket.$items.sink {
         print($0)
        //TODO push product to basket basketHandler.pushProductsToMiamBasket(retailerBasket: [])
             
       }
     }
    
    private func getTotalPayment() -> KotlinDouble {
        return 2.0
      }
    
    private func initCustomText() {
        MiamText.sharedInstance.alreadyInCart = "c'est dans la boite"
    }
    
    private func initTemplate(){
        Template.sharedInstance.counterTemplate =
        {(count: Int,
          increase: @escaping () -> Void,
          decrease: @escaping () -> Void ) -> AnyView in
            AnyView(
               HStack{
                   Button(action: {
                       decrease()
                   }) {
                       Image(systemName: "minus.circle.fill").foregroundColor(.red)
                   }
                   Text(String(count))
                   Button(action: {
                       increase()
                   }) {
                       Image(systemName: "plus.circle").foregroundColor(.blue)
                   }
                }
                )}
    }
}

