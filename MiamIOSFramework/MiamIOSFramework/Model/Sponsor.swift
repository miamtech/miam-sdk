//
//  Sponsor.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 23/02/2023.
//  Copyright © 2023 Miam. All rights reserved.
//

import Foundation
import miamCore

extension Sponsor {
    public var name: String {
        return self.attributes?.name ?? ""
    }

    public var logoURL: URL? {
        guard let logoUrl = self.attributes?.logoUrl else {
            return nil
        }

        return URL(string: logoUrl)
    }
}
