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
        VStack (spacing: 0) {
            if let myMealsState = myMealsViewModel.state {
                ManagementResourceState<NSArray,MyMealSuccessView,MyMealLoadingView,MyMealEmptyView> (
                    resourceState: myMealsState.lines,
                    successView:  MyMealSuccessView(myMealsViewModel:myMealsViewModel) ,
                    loadingView: MyMealLoadingView(loadingText:""),
                    emptyView: MyMealEmptyView()
                )
            }
        }
    }
}



