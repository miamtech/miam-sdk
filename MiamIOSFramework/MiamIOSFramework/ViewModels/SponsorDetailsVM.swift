//
//  SponsorDetailVM.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 20/02/2023.
//  Copyright © 2023 Miam. All rights reserved.
//

import Foundation
import miamCore

@available(iOS 14, *)
class SponsorDetailsVM: SponsorDetailsViewModel, ObservableObject {
    @Published var state: SponsorDetailsContractState?
    @Published var sponsorBlocks: [SponsorBlock] = []
    override init() {
        super.init()
        collect(flow: self.uiState) { data in
            self.state = data as? SponsorDetailsContractState
            switch self.state?.sponsorBlocks {
            case let success as BasicUiStateSuccess<NSArray>:
                if let blocks = success.data as? [SponsorBlock] {
                    self.sponsorBlocks = blocks.sorted(by: {
                        guard let aPosition = $0.attributes?.position, let bPosition = $1.attributes?.position else {
                            return false
                        }

                        return aPosition < bPosition
                    })
                }
            default:
                ()
            }
        }
    }
}
