//
//  PreferencesLoadingView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 03/11/2022.
//  Copyright © 2022 Miam. All rights reserved.
//

import SwiftUI

@available(iOS 14, *)
struct PreferencesLoadingView: View {
    var body: some View {
        if let template = Template.sharedInstance.preferencesLoadingViewTemplate {
            template()
        } else {
            ProgressLoader(color: Color.miamColor(.black))
        }
    }
}
