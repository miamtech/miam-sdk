//
//  MiamManager.swift
//  iosApp
//
//  Created by Miam on 19/04/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

public class MiamManager {
  public static let sharedInstance = MiamManager()
    

  private init() {
      KoinKt.doInitKoin()
      PointOfSaleHandler.shared.updateStoreId(storeId: "35290")
      PointOfSaleHandler.shared.setSupplier(supplierId: 7)
      UserHandler.shared.updateUserId(userId: "ed0a471a4bdc755664db84068119144b3a1772d8a6911057a0d6be6a3e075120") // need to be private
  }
}
