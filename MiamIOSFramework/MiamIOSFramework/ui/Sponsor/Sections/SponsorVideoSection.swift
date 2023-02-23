//
//  SponsorVideoSection.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 23/02/2023.
//  Copyright © 2023 Miam. All rights reserved.
//

import SwiftUI
import AVKit

@available(iOS 14, *)
struct SponsorVideoSection: View {
    let videoURL: String
    var body: some View {
        if let url = URL(string: videoURL) {
            VideoPlayer(player: AVPlayer(url: url))
        }
    }
}

@available(iOS 14, *)
struct SponsorVideoSection_Previews: PreviewProvider {
    static var previews: some View {
        SponsorVideoSection(videoURL: "")
    }
}
