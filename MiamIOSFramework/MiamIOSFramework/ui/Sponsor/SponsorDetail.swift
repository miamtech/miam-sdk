//
//  SponsorDetail.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 22/02/2023.
//  Copyright © 2023 Miam. All rights reserved.
//

import SwiftUI
import miamCore

@available(iOS 14, *)
public struct SponsorDetail: View {
    @StateObject var sponsorViewModel = SponsorDetailsVM()
    public let sponsor: Sponsor
    public var body: some View {
        ManagementResourceState(resourceState: sponsorViewModel.state?.sponsorBlocks,
                                successView: SponsorDetailSuccessView(sponsorBlocks: sponsorViewModel.sponsorBlocks),
                                loadingView: SponsorLoadingView(),
                                emptyView: EmptyView())
        .toolbar {
            ToolbarItem {
                if let logoURL = sponsor.attributes?.logoUrl, let url = URL(string: logoURL) {
                    AsyncImage(url: url, height: 40.0)
                }
            }
        }
        .onAppear {
            Task {
                sponsorViewModel.fetchSponsorBlockByIds(sponsor: sponsor)
            }
        }
    }
}
