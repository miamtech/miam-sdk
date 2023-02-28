//
//  SponsorLoadingView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 22/02/2023.
//  Copyright © 2023 Miam. All rights reserved.
//

import SwiftUI

@available(iOS 14, *)
struct SponsorLoadingView: View {
    var body: some View {
        ProgressLoader(color: .primary)
    }
}

@available(iOS 14, *)
struct SponsorLoadingView_Previews: PreviewProvider {
    static var previews: some View {
        SponsorLoadingView()
    }
}
