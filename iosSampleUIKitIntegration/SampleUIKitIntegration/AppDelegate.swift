//
//  AppDelegate.swift
//  SampleUIKitIntegration
//
//  Created by Vincent Kergonna on 13/06/2022.
//

import UIKit
import MiamIOSFramework
import shared

@main
class AppDelegate: UIResponder, UIApplicationDelegate {

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        KoinKt.doInitKoin()
        LogHandler.companion.info("Are you ready ? \(ContextHandlerInstance.shared.instance.isReady())")
        // basketHandler = BasketHandlerInstance.shared.instance
        // basketHandler.setListenToRetailerBasket(func: initBasketListener)
        // basketHandler.setPushProductsToRetailerBasket(func: pushProductToBasket)
        // basketHandler.pushProductsToMiamBasket(retailerBasket: [])
        PointOfSaleHandler.shared.updateStoreId(storeId: "35290")
        PointOfSaleHandler.shared.setSupplierOrigin(origin: "www.coursesu.fr")
        PointOfSaleHandler.shared.setSupplier(supplierId: 7)
        // PointOfSaleHandler.shared.isAvailable = false
        UserHandler.shared.updateUserId(userId: "ed0a471a4bdc755664db84068119144b3a1772d8a6911057a0d6be6a3e075120")
        return true
    }

    // MARK: UISceneSession Lifecycle

    func application(_ application: UIApplication, configurationForConnecting connectingSceneSession: UISceneSession, options: UIScene.ConnectionOptions) -> UISceneConfiguration {
        // Called when a new scene session is being created.
        // Use this method to select a configuration to create the new scene with.
        return UISceneConfiguration(name: "Default Configuration", sessionRole: connectingSceneSession.role)
    }

    func application(_ application: UIApplication, didDiscardSceneSessions sceneSessions: Set<UISceneSession>) {
        // Called when the user discards a scene session.
        // If any sessions were discarded while the application was not running, this will be called shortly after application:didFinishLaunchingWithOptions.
        // Use this method to release any resources that were specific to the discarded scenes, as they will not return.
    }

}
