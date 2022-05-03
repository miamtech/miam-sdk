//
//  Template.swift
//  MiamIOSFramework
//
//  Created by Miam on 02/05/2022.
//

import Foundation
import SwiftUI

public class Template {
    
    public static let sharedInstance = Template()
    
    private init(){}
    
    
    
    public var recipeCardTemplate : ((_: RecipeCardVM,
                                      _ : @escaping () -> Void,
                                      _ : @escaping () -> Void)  -> AnyView)? = nil
    
    /**
      First parameter Int is the current value of counter
      Second parameter is decrese function
      Third parameter is increase function
     */
    public var counterTemplate : ((_ : Int,
                                   _ : @escaping () -> Void,
                                   _ : @escaping () -> Void)  -> AnyView)? = nil
    
    /**
      Second parameter Int is de interger part of the price
      First parameter Int is decimal part of the price
     */
    public var priceTemplate : ((_ : Int,_ : Int )  -> AnyView)? = nil
        
}
