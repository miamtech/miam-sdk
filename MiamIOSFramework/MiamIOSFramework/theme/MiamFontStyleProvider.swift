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
public struct TitleBigFontStyle: MiamFontStyle {
    var font: Font = Font.system(size: 24, weight: .bold, design: .default)
    var color: Color?
}

@available(iOS 14, *)
public struct TitleFontStyle: MiamFontStyle {
    var font: Font = Font.system(size: 20, weight: .semibold, design: .default)
    var color: Color?
}

@available(iOS 14, *)
public struct TitleMediumFontStyle: MiamFontStyle {
    var font: Font = Font.system(size: 16, weight: .semibold, design: .default)
    var color: Color?
}

@available(iOS 14, *)
public struct TitleSmallFontStyle: MiamFontStyle {
    var font: Font = Font.system(size: 14, weight: .semibold, design: .default)
    var color: Color?
}

@available(iOS 14, *)
public struct TitleExtraSmallFontStyle: MiamFontStyle {
    var font: Font = Font.system(size: 13, weight: .semibold, design: .default)
    var color: Color?
}

@available(iOS 14, *)
public struct TitleExtraSmallMediumFontStyle: MiamFontStyle {
    var font: Font = Font.system(size: 13, weight: .medium, design: .default)
    var color: Color?
}

@available(iOS 14, *)
public struct SubtitleFontStyle: MiamFontStyle {
    var font: Font = Font.system(size: 15, weight: .bold, design: .default)
    var color: Color?
}

@available(iOS 14, *)
public struct BodyBigFontStyle: MiamFontStyle {
    var font: Font = Font.system(size: 16, design: .default)
    var color: Color?
}

@available(iOS 14, *)
public struct BodyBigBoldFontStyle: MiamFontStyle {
    var font: Font = Font.system(size: 16, weight: .bold, design: .default)
    var color: Color?
}

@available(iOS 14, *)
public struct BodyBigLightFontStyle: MiamFontStyle {
    var font: Font = Font.system(size: 16, weight: .light, design: .default)
    var color: Color?
}

@available(iOS 14, *)
public struct BodyFontStyle: MiamFontStyle {
    var font: Font = Font.system(size: 15, design: .default)
    var color: Color?
}

@available(iOS 14, *)
public struct BodyBoldFontStyle: MiamFontStyle {
    var font: Font = Font.system(size: 15, weight: .bold, design: .default)
    var color: Color?
}

@available(iOS 14, *)
public struct BodyMediumFontStyle: MiamFontStyle {
    var font: Font = Font.system(size: 14, design: .default)
    var color: Color?
}

@available(iOS 14, *)
public struct BodyMediumBoldFontStyle: MiamFontStyle {
    var font: Font = Font.system(size: 14, weight: .bold, design: .default)
    var color: Color?
}

@available(iOS 14, *)
public struct BodySmallFontStyle: MiamFontStyle {
    var font: Font = Font.system(size: 12, design: .default)
    var color: Color?
}

@available(iOS 14, *)
public struct BodySmallBoldFontStyle: MiamFontStyle {
    var font: Font = Font.system(size: 12, weight: .bold, design: .default)
    var color: Color?
}

@available(iOS 14, *)
public struct BodyExtraSmallFontStyle: MiamFontStyle {
    var font: Font = Font.system(size: 11, design: .default)
    var color: Color?
}

@available(iOS 14, *)
public struct MiamFontStyleProvider {
    static let sharedInstance = MiamFontStyleProvider()

    public var titleStyle = TitleFontStyle()
    public var titleBigStyle = TitleBigFontStyle()
    public var titleMediumStyle = TitleMediumFontStyle()
    public var titleSmallStyle = TitleSmallFontStyle()
    public var titleExtraSmallStyle = TitleExtraSmallFontStyle()
    public var titleExtraSmallMediumStyle = TitleExtraSmallMediumFontStyle()
    public var subtitleStyle = SubtitleFontStyle()
    public var bodyStyle = BodyFontStyle()
    public var bodyBigStyle = BodyBigFontStyle()
    public var bodyBigLightStyle = BodyBigLightFontStyle()
    public var bodyBigBoldStyle = BodyBigBoldFontStyle()
    public var bodyBoldStyle = BodyBoldFontStyle()
    public var bodyMediumStyle = BodyMediumFontStyle()
    public var bodyMediumBoldStyle = BodyMediumBoldFontStyle()
    public var bodySmallStyle = BodySmallFontStyle()
    public var bodySmallBoldStyle = BodySmallBoldFontStyle()
    public var bodyExtraSmallStyle = BodyExtraSmallFontStyle()
}
