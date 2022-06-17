//
//  CounterStyle.swift
//  MiamIOSFramework
//
//  Created by Miam on 28/04/2022.
//

import Foundation
import SwiftUI

public struct MainRowContainer : ViewModifier {
    public func body(content: Content) -> some View {
        content.padding(Dimension.sharedInstance.sPadding)
    }
}

extension View {
    public func mainRowContainer () -> some View {
       return modifier(MainRowContainer())
    }
}


