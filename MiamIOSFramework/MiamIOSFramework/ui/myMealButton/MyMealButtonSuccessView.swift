//
//  MyMealButtonSuccessView.swift
//  MiamIOSFramework
//
//  Created by Vincent Kergonna on 03/11/2022.
//  Copyright © 2022 Miam. All rights reserved.
//

import SwiftUI

@available(iOS 14, *)
struct MyMealButtonSuccessView: View {
    let mealsCount: Int
    let onButtonTapped: () -> Void
    var body: some View {
        if let template = Template.sharedInstance.myMealButtonSuccessViewTemplate {
           template(mealsCount, onButtonTapped)
        } else {
            Button {
                onButtonTapped()
            } label: {
                VStack {
                    Image.miamImage(icon: .greyChevronDown)
                        .rotationEffect(Angle(degrees: 180.0))
                    let mealsAdded = Localization.myMeals.mealsAdded(numberOfMeals: Int32(mealsCount)).localised
                    Text(mealsAdded)
                        .miamFontStyle(style: MiamFontStyleProvider.sharedInstance.bodyBigBoldStyle)
                }
                .frame(maxWidth: .infinity)
                .padding(EdgeInsets(top: 16.0, leading: 20.0, bottom: 8.0, trailing: 20.0))

                // Little trick to have corner radius only on top corners
            }
            .background(Color.clear)
            .foregroundColor(Color.miamColor(.white))
            .background(SimpleQuadCurve().foregroundColor(Color.miamColor(.primaryDark)))
            .clipShape(SimpleQuadCurve())
        }
    }
}
