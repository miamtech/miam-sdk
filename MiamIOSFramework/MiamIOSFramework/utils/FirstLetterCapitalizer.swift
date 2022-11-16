//
//  FirstLetterCapitalizer.swift
//  MiamIOSFramework
//
//  Created by Miam on 02/06/2022.
//

import Foundation


extension String {
    public func capitalizingFirstLetter() -> String {
        return prefix(1).capitalized + dropFirst()
    }

    public mutating func capitalizeFirstLetter() {
        self = self.capitalizingFirstLetter()
    }
}
