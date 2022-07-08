//
//  ItemSelectorText.swift
//  MiamIOSFramework
//
//  Created by Miam on 07/06/2022.
//

import Foundation

@available(iOS 14, *)
public class ItemSelectorText {
    
    public static let sharedInstance = ItemSelectorText()
    
    public var swapProduct = MiamText.sharedInstance.swapProduct
    public var alreadyInCart = MiamText.sharedInstance.alreadyInCart
    public var select = MiamText.sharedInstance.select

    private init() {}
    
}
