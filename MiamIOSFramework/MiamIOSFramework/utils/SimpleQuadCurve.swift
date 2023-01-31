//
//  CircularArc.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 09/11/2022.
//  Copyright © 2022 Miam. All rights reserved.
//

import Foundation
import SwiftUI

@available(iOS 14, *)
public struct SimpleQuadCurve: Shape {
    public func path(in rect: CGRect) -> Path {
        var path = Path()
        path.move(to: CGPoint(x: 0, y: rect.maxY))
        path.addQuadCurve(
          to: CGPoint(x: rect.width, y: rect.maxY),
          control: CGPoint(x: rect.midX, y: -rect.maxY))

        return path
    }
}
