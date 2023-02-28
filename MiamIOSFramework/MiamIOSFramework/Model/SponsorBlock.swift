//
//  SponsorBlock.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 23/02/2023.
//  Copyright © 2023 Miam. All rights reserved.
//

import Foundation
import miamCore

enum SponsorBlockType: String {
    case unknow = "unknown"
    case title = "title"
    case smallTitle = "small_title"
    case text = "text"
    case smallText = "small_text"
    case picture = "picture"
    case smallPicture = "small_picture"
    case video = "video"
    case imageAndText = "image_and_text"
    case imageWithText = "image_with_text"
    case textAndImage = "text_and_image"
}

extension SponsorBlock {
    var blockType: SponsorBlockType {
        guard let typeString = self.relationships?.sponsorBlockType?.data.id, let type = SponsorBlockType(rawValue: typeString) else {
            return .unknow
        }

        return type
    }
}
