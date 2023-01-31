//
//  MiamFontStyle.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 25/11/2022.
//  Copyright © 2022 Miam. All rights reserved.
//

import Foundation
import SwiftUI

@available(iOS 14, *)

protocol MiamFontStyle {
    var font: Font { get }
    var color: Color? { get }
}

@available(iOS 14, *)
struct MiamFontStyleModifier: ViewModifier {
    let fontStyle: MiamFontStyle

    func body(content: Content) -> some View {
        content.font(fontStyle.font)// .fontWeight(weight)
        if let color = fontStyle.color {
            content.foregroundColor(color)
        }
    }
}

@available(iOS 14, *)
extension View {
    func miamFontStyle(style: any MiamFontStyle) -> some View {
        modifier(MiamFontStyleModifier(fontStyle: style))
    }
}
