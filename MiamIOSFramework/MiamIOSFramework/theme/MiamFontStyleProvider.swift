//
//  MiamFontStyleProvider.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 25/11/2022.
//  Copyright © 2022 Miam. All rights reserved.
//

import Foundation
import SwiftUI

@available(iOS 14, *)
public struct ButtonFontStyle: MiamFontStyle {
    var font: Font = Font.system(size: 15, weight: .bold, design: .default)
    var color: Color? = nil
}

@available(iOS 14, *)
public struct TitleFontStyle: MiamFontStyle {
    var font: Font = Font.system(size: 15, weight: .bold, design: .default)
    var color: Color? = nil
}

@available(iOS 14, *)
public struct SubtitleFontStyle: MiamFontStyle {
    var font: Font = Font.system(size: 15, weight: .bold, design: .default)
    var color: Color? = nil
}

@available(iOS 14, *)
public struct BodyFontStyle: MiamFontStyle {
    var font: Font = Font.system(size: 15, weight: .bold, design: .default)
    var color: Color? = nil
}
 
 
@available(iOS 14, *)
public struct MiamFontStyleProvider {
    static let sharedInstance = MiamFontStyleProvider()
    
    public var titleStyle = TitleFontStyle()
    public var subtitleStyle = SubtitleFontStyle()
    public var buttonStyle = ButtonFontStyle()
    public var bodyStyle = BodyFontStyle()
}
