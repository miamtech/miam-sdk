//
//  File.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 22/07/2022.
//

import Foundation

fileprivate class MiamBundleHelper {}

extension Bundle {
    static let miamBundle = Bundle(for: MiamBundleHelper.self)
}
