//
//  CounterStyle.swift
//  MiamIOSFramework
//
//  Created by Miam on 28/04/2022.
//

import Foundation
import SwiftUI

@available(iOS 14, *)
public struct MainRowContainer: ViewModifier {
    public func body(content: Content) -> some View {
        content.padding(Dimension.sharedInstance.sPadding)
    }
}

@available(iOS 14, *)
extension View {
    public func mainRowContainer () -> some View {
       return modifier(MainRowContainer())
    }
}
