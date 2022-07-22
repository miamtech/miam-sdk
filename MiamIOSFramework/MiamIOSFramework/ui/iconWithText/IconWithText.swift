//
//  IconWithText.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 07/07/2022.
//

import SwiftUI

@available (iOS 14, *)
struct IconWithText: View {
    let imageName: String
    let text: String
    var body: some View {
        VStack(spacing: 6.0) {
            Image(imageName, bundle: Bundle(for: RecipeCardVM.self))
            Text(text)
                .font(.system(size: 11.0))
                .foregroundColor(Color.miamColor(.neutralGrey))
        }
    }
}

@available (iOS 14, *)
struct IconWithText_Previews: PreviewProvider {
    static var previews: some View {
        IconWithText(imageName: "Clock", text: "15 min")
    }
}
