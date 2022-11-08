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
                    
                    Text("\(mealsCount) repas ajouté").bold()
                    
                }.padding(EdgeInsets(top: 16.0, leading: 20.0, bottom: 8.0, trailing: 20.0))
                    .frame(maxWidth: .infinity)
                    .background(Color.miamColor(.primaryDark))
                    .foregroundColor(Color.miamColor(.white))
                // Little trick to have corner radius only on top corners
                    .padding(.bottom, 20.0)
                    .cornerRadius(20.0)
                    .padding(.bottom, -20.0)
            }
        }
    }
}
