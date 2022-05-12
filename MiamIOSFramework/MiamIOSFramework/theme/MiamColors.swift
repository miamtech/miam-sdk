//
//  MiamColors.swift
//  MiamIOSFramework
//
//  Created by Miam on 27/04/2022.
//

import Foundation
import SwiftUI


public class MiamColor {
    
    public static let sharedInstance = MiamColor()
    
    private init(){}
    
    var primary = Color(hex:"#037E92")
    var secondary = Color(hex:"#E61845")
    var ternary = Color(hex:"#209B8F")
    var success = Color(hex:"#44D6B3")
    var info = Color(hex:"#44D6B3")
    var warning = Color(hex:"#FFDAA3")
    var danger = Color(hex:"#F47F7A")
    var grey = Color(hex:"#676767")
    var lightgrey = Color(hex: "#9F9F9F")
    var white = Color(hex:"#FAFCFE")
    var unpureWhite = Color(hex:"#fefefe")
    var black = Color(hex:"#252525")
    let musterd = Color(hex:"#FFAB08")
    let greySurface = Color(hex: "#EDEDED")
    let bodyText = Color(hex:"#4B555D")

}
