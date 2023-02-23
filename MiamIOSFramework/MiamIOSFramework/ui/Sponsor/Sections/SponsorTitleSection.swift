//
//  SponsorTitleSection.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 23/02/2023.
//  Copyright © 2023 Miam. All rights reserved.
//

import SwiftUI

@available(iOS 14, *)
struct SponsorTitleSection: View {
    let title: String
    var body: some View {
        Text(title)
            .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.titleBigStyle)
            .foregroundColor(Color.miamColor(.primary))
    }
}

@available(iOS 14, *)
struct SponsorTitleSection_Previews: PreviewProvider {
    static var previews: some View {
        SponsorTitleSection(title: "Test")
    }
}
