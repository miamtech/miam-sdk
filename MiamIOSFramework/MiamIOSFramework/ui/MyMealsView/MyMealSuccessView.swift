//
//  MyMealSuccessView.swift
//  MiamIOSFramework
//
//  Created by Miam on 21/07/2022.
//

import SwiftUI

@available(iOS 14, *)
struct MyMealSuccessView: View {
    
    @ObservedObject var myMealsViewModel: MyMealVM
    
    var body: some View {
        ScrollView {
            ForEach(myMealsViewModel.meals) { meal in
                MyMealRow(myMealViewModel: myMealsViewModel, meal: meal).animation(.default, value: 1).padding(.bottom, Dimension.sharedInstance.mPadding)
            }
        }
    }
}
