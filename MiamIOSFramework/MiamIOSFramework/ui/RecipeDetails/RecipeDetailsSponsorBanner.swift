//
//  RecipeDetailsSponsorBanner.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 20/02/2023.
//  Copyright © 2023 Miam. All rights reserved.
//

import SwiftUI
import miamCore

@available(iOS 14, *)
struct RecipeDetailsSponsorBanner: View {
    let sponsor: Sponsor
    let sponsorDetailsTapped: (Sponsor) -> Void
    var body: some View {
        MiamRecipeDetailsSponsorBanner(sponsor: sponsor, sponsorDetailsTapped: sponsorDetailsTapped)
    }
}

@available(iOS 14, *)
struct MiamRecipeDetailsSponsorBanner: View {
    let sponsor: Sponsor
    let sponsorDetailsTapped: (Sponsor) -> Void
    var body: some View {
        HStack(spacing: 0.0) {
            VStack(alignment: .leading, spacing: 8.0) {
                Text(Localization.sponsorBanner.sponsorBannerSpeach.localised)
                    .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyStyle)
                Button {
                    sponsorDetailsTapped(sponsor)
                } label: {
                    Text(Localization.sponsorBanner.sponsorBannerMoreInfo.localised)
                }
            }
            Spacer(minLength: 16.0)
            if let sponsorAttributes = sponsor.attributes, let logoURL = URL(string: sponsorAttributes.logoUrl) {
                AsyncImage(url: logoURL, height: 48.0)

                    .frame(width: 75.0)
            }
        }
        .padding(.horizontal, Dimension.sharedInstance.mPadding)
        .padding(.vertical, Dimension.sharedInstance.lPadding)
    }
}
