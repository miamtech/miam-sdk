//
//  Image.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 26/07/2022.
//

import Foundation
import SwiftUI

@available(iOS 14, *)
extension Image {
    static func miamImage(icon: MiamIcon) -> Image {
        guard let image = UIImage(named: icon.rawValue) else {
            return Image(icon.rawValue, bundle: Bundle.miamBundle)
        }

        return Image(uiImage: image)
    }
}
