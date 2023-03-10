//
//  FavoritesEmptyView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 23/09/2022.
//

import SwiftUI

@available(iOS 14, *)
struct FavoritesEmptyView: View {
    let browseCatalogAction: () -> Void
    var body: some View {
        if let template = Template.sharedInstance.favoritesEmptyViewTemplate {
            template(browseCatalogAction)
        } else {
            EmptyView()
            Button {
                browseCatalogAction()
            } label: {
                Text("\(MiamText.sharedInstance.browseRecipesText)")
                    .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyBigBoldStyle)
                    .foregroundColor(Color.miamColor(.primary))
                Image.miamImage(icon: .rightArrow)
            }
            .padding(EdgeInsets(top: 10, leading: 20, bottom: 10, trailing: 20))
            .background(Capsule().foregroundColor(.white))
            .overlay(Capsule().stroke(.white, lineWidth: 1.0))
        }
    }
}
