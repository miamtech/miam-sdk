//
//  MiamManager.swift
//  iosApp
//
//  Created by Miam on 19/04/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import miamCore
import MiamIOSFramework
import SwiftUI
import Combine

@available(iOS 14, *)
public class Categories : ObservableObject {
    @Published var categoriesList:[CatalogCategory]  = []
}

public class MiamManager {
    
    public static let sharedInstance = MiamManager()
    private let availableStoreIdLists = ["454", "652"]
    private let basketHandler: BasketHandler
    private var cancelable : AnyCancellable?
    public let categories = Categories()
    
    func isActiveOnStore() -> KotlinBoolean {
        return  KotlinBoolean(value: availableStoreIdLists.contains("35290"))
    }
    
    // need to be private
    private init() {
        KoinKt.doInitKoin()
        LogHandler.companion.info("Are you ready ? \(ContextHandlerInstance.shared.instance.isReady())")
        ContextHandlerInstance.shared.instance.onReadyEvent(callback: {isReady in print("Miam event recived \(isReady)")})
        ContextHandlerInstance.shared.instance.setContext(context: NSObject())
        UserPreferencesInstance.shared.instance.putInt(key: "testInt", value: 42)
        UserPreferencesInstance.shared.instance.putList(key: "testString", value: ["1","2","3"])
        print("IntPref success \( UserPreferencesInstance.shared.instance.getIntOrNull(key: "testInt"))")
        print("ListPref success \( UserPreferencesInstance.shared.instance.getListOrNull(key: "testString"))")
        basketHandler = BasketHandlerInstance.shared.instance
        basketHandler.setListenToRetailerBasket(func: initBasketListener)
        basketHandler.setPushProductsToRetailerBasket(func: pushProductToBasket)
        basketHandler.pushProductsToMiamBasket(retailerBasket: [])
        PointOfSaleHandler.shared.updateStoreId(storeId: "35290")
        PointOfSaleHandler.shared.setSupplierOrigin(origin:"app.coursesu.com")
        PointOfSaleHandler.shared.setSupplier(supplierId: 7)
        PointOfSaleHandler.shared.isAvailable = isActiveOnStore
        PointOfSaleHandler.shared.getCatalogCategories { categories in
            self.categories.categoriesList = categories
            
        }
        UserHandler.shared.updateUserId(userId: "ed0a471a4bdc755664db84068119144b3a1772d8a6911057a0d6be6a3e075120")
        UserHandler.shared.setProfilingAllowed(allowance: true)
        UserHandler.shared.setEnableLike(isEnable: true)
        //initTemplate()
    }

    
    private func yourProductsToRetailerProducts(products: Array<MyProduct>) -> Array<RetailerProduct> {
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
                MyBasket.shared.remove(removedProduct: $0)
            } else {
                MyBasket.shared.add(addedProduct: $0)
            }
        }
        )
    }
    
    private func retailerProductsToYourProducts(products: Array<RetailerProduct>) -> Array<MyProduct> {
        return products.map {
            return   MyProduct(
                id: $0.retailerId,
                name: "\($0.name)",
                quantity: Int($0.quantity)
            )
        }
    }
    
    private func initBasketListener() {
        cancelable = MyBasket.shared.$items.sink { receiveValue in
            self.basketHandler.pushProductsToMiamBasket(
                retailerBasket: self.yourProductsToRetailerProducts(products:receiveValue)
            )
        }
    }
    
    private func getTotalPayment() -> KotlinDouble {
        return 2.0
    }
    
    private func initCustomText() {
        MiamText.sharedInstance.alreadyInCart = "c'est dans la boite"
    }
    
    private func initTemplate(){
        Template.sharedInstance.ingredientNotInBasketRowTemplate =
        {(name: String,
          action: (() -> Void)?) -> AnyView in
            AnyView(
                HStack{
                    Button(action: {
                        if(action != nil){
                            action!()
                        }
                    }) {
                        Image(systemName: "minus.circle.fill").foregroundColor(.red)
                    }
                }
            )}
    }
}
