//
//  MyMealsView.swift
//  MiamIOSFramework
//
//  Created by John on 16/05/2022.
//

import SwiftUI

@available(iOS 14, *)
public struct MyMealsView: View {
    @ObservedObject var myMealsViewModel: MyMealVM = MyMealVM()

    public init() {}

    public var body: some View {
        ScrollView {
            
            ForEach(myMealsViewModel.meals) { meal in
                MyMealRow(myMealViewModel: myMealsViewModel, meal: meal).animation(.default, value: 1).padding(.bottom, Dimension.sharedInstance.mPadding)
            }
        }

    }
}

@available(iOS 14, *)
struct MyMealsView_Previews: PreviewProvider {
    static var previews: some View {
        MyMealsView()
    }
}

