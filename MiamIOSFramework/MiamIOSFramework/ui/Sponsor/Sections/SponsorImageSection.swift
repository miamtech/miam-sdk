//
//  SponsorImageSection.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 23/02/2023.
//  Copyright © 2023 Miam. All rights reserved.
//

import SwiftUI

@available(iOS 14, *)
struct SponsorImageSection: View {
    let pictureURL: String
    var body: some View {
        if let url = URL(string: pictureURL) {
            AsyncImage(url: url, height: 246.0)
        }
    }
}

@available(iOS 14, *)
struct SponsorImageSection_Previews: PreviewProvider {
    static var previews: some View {
        SponsorImageSection(pictureURL: "")
    }
}
