//
//  SponsorDetailSuccessView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 22/02/2023.
//  Copyright © 2023 Miam. All rights reserved.
//

import SwiftUI
import miamCore

@available(iOS 14, *)
struct SponsorDetailSuccessView: View {
    let sponsorBlocks: [SponsorBlock]
    var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 0.0) {
                ForEach(sponsorBlocks, id: \.id) { section in
                    switch section.blockType {
                    case .title, .smallTitle:
                        if let title = section.attributes?.content {
                            SponsorTitleSection(title: title)
                        }
                    case .picture, .smallPicture:
                        if let picture = section.attributes?.pictureUrl {
                            SponsorImageSection(pictureURL: picture)
                                .frame(height: 256)
                        }
                    case .text, .smallText:
                        if let text = section.attributes?.content {
                            SponsorTextSection(text: text)
                        }
                    case .imageAndText:
                        if let pictureURL = section.attributes?.pictureUrl, let text = section.attributes?.content {
                            SponsorImageAndTextSection(text: text, pictureURL: pictureURL)
                        }
                    case .textAndImage:
                        if let pictureURL = section.attributes?.pictureUrl, let text = section.attributes?.content {
                            SponsorTextAndImageSection(text: text, pictureURL: pictureURL)
                        }
                    case .imageWithText:
                        if let pictureURL = section.attributes?.pictureUrl, let text = section.attributes?.content {
                            SponsorImageWithTextSection(text: text, pictureURL: pictureURL)
                        }
                    case .video:
                        if let videoURL = section.attributes?.videoUrl {
                            SponsorVideoSection(videoURL: videoURL)
                        }
                    default:
                        EmptyView()
                    }
                }
            }
            .padding(Dimension.sharedInstance.mlPadding)
        }
    }
}

@available(iOS 14, *)
struct SponsorDetailSuccessView_Previews: PreviewProvider {
    static var previews: some View {
        SponsorDetailSuccessView(sponsorBlocks: [])
    }
}
