//
//  Dimension.swift
//  MiamIOSFramework
//
//  Created by Miam on 28/04/2022.
//

import Foundation
import SwiftUI

@available(iOS 14, *)
public class Dimension {
    
    public static let sharedInstance = Dimension()
    
    var borderWidth : CGFloat = 1
    var sPadding : CGFloat = 4 
    var mPadding : CGFloat = 8
    var lPadding : CGFloat = 16
    var xlPadding : CGFloat = 32
    var mlPadding : CGFloat = 10
    
    var sButtonHeight : CGFloat = 8
    var mButtonHeight : CGFloat = 16
    var lButtonHeight : CGFloat = 32
    var xlButtonHeight : CGFloat = 40
    
}
