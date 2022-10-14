//
//  MyMealEmptyView.swift
//  MiamIOSFramework
//
//  Created by Miam on 21/07/2022.
//

import SwiftUI

@available(iOS 14, *)
struct MyMealEmptyView: View {
    var body: some View {
        if let template = Template.sharedInstance.myMealsEmptyViewTemplate {
            template()
        } else {
            GeometryReader { metrics in
                VStack{
                    HStack{
                        Spacer()
                        Text(MiamText.sharedInstance.noMealIdeaInBasket).frame(width: metrics.size.width / 2)
                            .fixedSize(horizontal: false, vertical: true)
                            .multilineTextAlignment(.center)
                        Spacer()
                    }
                }.frame(
                    minWidth: 0,
                    maxWidth: .infinity,
                    minHeight: 0,
                    maxHeight: .infinity,
                    alignment: .topLeading
                )
            }
        }
    }
}
