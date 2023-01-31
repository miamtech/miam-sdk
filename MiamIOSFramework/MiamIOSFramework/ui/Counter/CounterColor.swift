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

    private init() {}

    public   var lessButtonBackgroundDisableColor: Color = Color.miamColor(.lightGrey)
    public   var lessButtonBackgroundColor: Color = Color.miamColor(.white)
    public   var lessIconColor: Color = Color.miamColor(.black)

    public   var plusButtonBackgroundDisableColor: Color = Color.miamColor(.lightGrey)
    public   var plusButtonBackgroundColor: Color = Color.miamColor(.white)
    public   var plusIconColor: Color = Color.miamColor(.black)
}
