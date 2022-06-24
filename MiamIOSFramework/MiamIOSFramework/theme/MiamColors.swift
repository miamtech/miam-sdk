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
    let musterd = Color(hex:"#FFC700")
    let greySurface = Color(hex: "#EDEDED")
    let bodyText = Color(hex:"#4B555D")
    let borderColor = Color(hex:"#DDDDDD")
    let primaryText = Color(hex:"#007e92")
    let borderBottom = Color(hex:"#e9e9e9")
    let lightPrimaryBg = Color(hex:"#F3F9FA")
    let black20 = Color(hex:"#202020")
    let primaryLight = Color(hex: "#BED5DC")
    let backgroundDark = Color(hex: "#005562")
}
