//
//  TitleBarView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 13/07/2022.
//

import SwiftUI

@available(iOS 14, *)
struct TitleBarView: View {
    let barHeight = 55.0

    let showBackButton: Bool
    let backAction: (() -> Void)?
    let titleView: AnyView
    var body: some View {
        HStack {
            if showBackButton {
                Button {
                    if let backAction = backAction {
                        backAction()
                    }
                } label: {
                    Image("back", bundle: Bundle.miamBundle)
                        .renderingMode(.template)
                        .foregroundColor(Color.miamColor(.primary))
                        .padding([.leading, .trailing], Dimension.sharedInstance.lPadding)
                }

            }
            titleView
            Spacer()
        }
        .frame(maxWidth: .infinity)
        .frame(height: barHeight)
    }
}

@available(iOS 14, *)
struct TitleBarView_Previews: PreviewProvider {
    static var previews: some View {
        TitleBarView(showBackButton: true, backAction: nil, titleView: AnyView(Text("Sample view")))
    }
}
