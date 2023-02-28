//
//  SponsorImageAndTextSection.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 23/02/2023.
//  Copyright © 2023 Miam. All rights reserved.
//

import SwiftUI

@available(iOS 14, *)
struct SponsorImageAndTextSection: View {
    let text: String
    let pictureURL: String
    var body: some View {
        VStack {
            SponsorImageSection(pictureURL: pictureURL)
            SponsorTextSection(text: text)
        }
    }
}
