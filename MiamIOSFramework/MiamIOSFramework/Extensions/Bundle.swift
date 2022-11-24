//
//  File.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 22/07/2022.
//

import Foundation

fileprivate class MiamBundleHelper {}

extension Bundle {
    public static let miamBundle: Bundle = {
                let candidates = [
                    // Bundle should be present here when the package is linked into an App.
                    Bundle.main.resourceURL,

                    // Bundle should be present here when the package is linked into a framework.
                    Bundle(for: MiamBundleHelper.self).resourceURL,
                ]

                let bundleName = "MiamIOSFramework_MiamIOSFramework"

                for candidate in candidates {
                    let bundlePath = candidate?.appendingPathComponent(bundleName + ".bundle")
                    if let bundle = bundlePath.flatMap(Bundle.init(url:)) {
                        return bundle
                    }
                }

                // Return whatever bundle this code is in as a last resort.
                return Bundle(for: MiamBundleHelper.self)
            }()
}
