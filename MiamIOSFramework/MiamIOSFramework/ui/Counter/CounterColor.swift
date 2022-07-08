//
//  CounterColor.swift
//  MiamIOSFramework
//
//  Created by Miam on 27/04/2022.
//

import Foundation
import SwiftUI

@available(iOS 14, *)
public class CounterColor {
    
    public static let sharedInstance = CounterColor()
    
    private init(){}
    
      public   var lessButtonBackgroundDisableColor : Color = MiamColor.sharedInstance.lightgrey
      public   var lessButtonBackgroundColor : Color = MiamColor.sharedInstance.white
      public   var lessIconColor : Color =  MiamColor.sharedInstance.black

      public   var plusButtonBackgroundDisableColor : Color = MiamColor.sharedInstance.lightgrey
      public   var plusButtonBackgroundColor : Color = MiamColor.sharedInstance.white
      public   var plusIconColor  : Color =  MiamColor.sharedInstance.black
}


