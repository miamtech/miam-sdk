//
//  IconWithText.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 07/07/2022.
//

import SwiftUI

@available (iOS 14, *)
struct IconWithText: View {
    let icon: MiamIcon
    let text: String
    var body: some View {
        VStack(spacing: 6.0) {
            Image.miamImage(icon: icon)
            Text(text).miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodySmallStyle)
                .foregroundColor(Color.miamColor(.neutralGrey))
        }
    }
}

@available (iOS 14, *)
struct IconWithText_Previews: PreviewProvider {
    static var previews: some View {
        IconWithText(icon: .clock, text: "15 min")
    }
}
